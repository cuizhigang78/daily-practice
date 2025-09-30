package _101_stock;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class RecordParser {

    public static void main(String[] args) throws IOException {
        // 读取文件内容
        List<String> lines = Files.readAllLines(Paths.get("src/main/java/_101_stock/record.md"));

        // 跳过表头并解析数据
        List<TransactionRecord> records = lines.stream()
                .skip(2) // 跳过表头
                .map(RecordParser::parseLine)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // 按名称分组
        Map<String, List<TransactionRecord>> groupedByName = records.stream()
                .collect(Collectors.groupingBy(TransactionRecord::getName));

        // 找到每组最后一次交易
        Map<String, TransactionRecord> lastTransactions = groupedByName.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .max(Comparator.comparing(TransactionRecord::getDate))
                                .orElse(null)
                ));

        // 找到距今最远的那一组
        // 先排序，然后取第一个
        List<Map.Entry<String, TransactionRecord>> sortedEntries = lastTransactions.entrySet().stream()
                .sorted(Comparator.comparing(entry -> entry.getValue().getDate()))
                .collect(Collectors.toList());
        Map.Entry<String, TransactionRecord> farthestEntry = sortedEntries.isEmpty() ? null : sortedEntries.get(3);
        TransactionRecord farthestTransaction = farthestEntry != null ? farthestEntry.getValue() : null;

        if (farthestTransaction != null) {
            System.out.println("距今最远的交易组名称: " + farthestTransaction.getName());
            System.out.println("最后一次交易日期: " + farthestTransaction.getDate());
        } else {
            System.out.println("未找到交易记录");
        }
    }

    // 解析每一行数据
    private static TransactionRecord parseLine(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length < 4) return null;

            LocalDate date = LocalDate.parse(parts[1].trim(), DateTimeFormatter.ofPattern("yyyy/M/d"));
            String name = parts[2].trim();
            String operation = parts[3].trim();
            double price = Double.parseDouble(parts[4].trim());

            return new TransactionRecord(date, name, operation, price);
        } catch (Exception e) {
            return null; // 忽略解析失败的行
        }
    }
}

