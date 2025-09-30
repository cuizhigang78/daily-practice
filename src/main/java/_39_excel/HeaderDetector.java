package _39_excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.data.ReadCellData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HeaderDetector {
    // 预设关键词（根据业务场景调整）
    private static final List<String> KEYWORDS = Arrays.asList("流水号","收","支","借","贷","额","日期","时间","对方","摘要","备注","用途");
    // 阈值：连续非空单元格占比超过70%则认为是表头
    private static final double NON_EMPTY_RATIO_THRESHOLD = 0.7;

    public static void main(String[] args) {
//        String filePath = "D:\\202410AI竞赛\\各银行\\北屯民村镇银行\\7000-北屯国民村镇银行保证金户01515.xls";
//        EasyExcel.read(filePath, new HeaderListener())
//                .sheet()
//                .headRowNumber(0) // 从第0行开始读取（默认会跳过前0行，即全量读取）
//                .doRead();
    }

    public static class HeaderListener extends AnalysisEventListener<Map<Integer, ReadCellData<?>>> {
        private int headerRow = -1;
        private int headerCol = -1;

        @Override
        public void invoke(Map<Integer, ReadCellData<?>> rowData, AnalysisContext context) {
            if (headerRow != -1) return; // 已找到表头，跳过后续行

            int rowIndex = context.readRowHolder().getRowIndex();
            List<ReadCellData<?>> cells = new ArrayList<>(rowData.values());
            int totalCells = cells.size();

            // 方法1：连续非空单元格占比判断
            int nonEmptyCount = (int) cells.stream().filter(cell -> cell.getStringValue() != null && !cell.getStringValue().trim().isEmpty()).count();
            double nonEmptyRatio = (double) nonEmptyCount / totalCells;

            // 方法2：关键词匹配（统计关键词数量）
            int keywordMatchCount = 0;
            for (ReadCellData<?> cell : cells) {
                String value = cell.getStringValue();
                if (value != null && KEYWORDS.stream().anyMatch(keyword -> value.contains(keyword))) {
                    keywordMatchCount++;
                }
            }

            // 方法3：样式检测（检查是否加粗）
            boolean hasBoldStyle = false;



            // 综合判断：满足任意两个条件则认为是表头
            boolean isHeader = (nonEmptyRatio >= NON_EMPTY_RATIO_THRESHOLD)
                    || (keywordMatchCount >= 2)
                    || hasBoldStyle;

            if (isHeader) {
                headerRow = rowIndex;
                // 找表头起始列（第一个非空单元格）
                for (int i = 0; i < cells.size(); i++) {
                    if (cells.get(i).getStringValue() != null && !cells.get(i).getStringValue().trim().isEmpty()) {
                        headerCol = i;
                        break;
                    }
                }
            }
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            if (headerRow != -1) {
                System.out.printf("表头定位成功！起始行：%d，起始列：%d%n", headerRow, headerCol);
            } else {
                System.out.println("未检测到符合条件的表头行");
            }
        }
    }
}