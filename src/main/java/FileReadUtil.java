import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件读取工具类
 * @author cursor
 * @since 2024/6/11
 */
public class FileReadUtil {

    /**
     * 读取指定目录下所有文件的文件名
     * @param directoryPath 目录路径
     * @return 文件名列表
     * @author cursor
     * @since 2024/6/11
     */
    public static List<String> readFileNames(String directoryPath) {
        List<String> fileNames = new ArrayList<>();
        
        // 创建File对象
        File directory = new File(directoryPath);
        
        // 检查目录是否存在且是目录
        if (!directory.exists()) {
            throw new IllegalArgumentException("目录不存在: " + directoryPath);
        }
        
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("指定路径不是目录: " + directoryPath);
        }
        
        // 获取目录下的所有文件和子目录
        File[] files = directory.listFiles();
        
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileNames.add(file.getName());
                }
            }
        }
        
        return fileNames;
    }

    /**
     * 读取指定目录下所有文件的文件名（包括子目录中的文件）
     * @param directoryPath 目录路径
     * @return 文件名列表
     * @author cursor
     * @since 2024/6/11
     */
    public static List<String> readFileNamesRecursive(String directoryPath) {
        List<String> fileNames = new ArrayList<>();
        readFileNamesRecursive(new File(directoryPath), fileNames);
        return fileNames;
    }

    /**
     * 递归读取文件名的私有方法
     * @param directory 目录对象
     * @param fileNames 文件名列表
     * @author cursor
     * @since 2024/6/11
     */
    private static void readFileNamesRecursive(File directory, List<String> fileNames) {
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        fileNames.add(file.getName());
                    } else if (file.isDirectory()) {
                        // 递归处理子目录
                        readFileNamesRecursive(file, fileNames);
                    }
                }
            }
        }
    }

    /**
     * 读取指定目录下所有文件的完整路径
     * @param directoryPath 目录路径
     * @return 文件完整路径列表
     * @author cursor
     * @since 2024/6/11
     */
    public static List<String> readFileFullPaths(String directoryPath) {
        List<String> filePaths = new ArrayList<>();
        
        File directory = new File(directoryPath);
        
        if (!directory.exists()) {
            throw new IllegalArgumentException("目录不存在: " + directoryPath);
        }
        
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("指定路径不是目录: " + directoryPath);
        }
        
        File[] files = directory.listFiles();
        
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    filePaths.add(file.getAbsolutePath());
                }
            }
        }
        
        return filePaths;
    }

    /**
     * 测试方法，用于验证工具类功能
     * @param args 命令行参数
     * @author cursor
     * @since 2024/6/11
     */
    public static void main(String[] args) {
        // 测试示例
        String testPath = "D:\\Working\\VTM\\REQ[VTM]034_网银流水配置需求 + REQ[VTM]029_手工上传流水标准化处理器\\生产验证\\20250709\\新建文件夹 (3)";
        List<String> accountCodes = new ArrayList<>();
        try {
            System.out.println("读取目录: " + testPath);
            List<String> fileNames = readFileNames(testPath);
            System.out.println("找到文件数量: " + fileNames.size());
            
            for (String fileName : fileNames) {
                System.out.println("文件名: " + fileName);
                accountCodes.add("'" + fileName.substring(0, fileName.indexOf(".")) + "'");
            }

            System.out.println("账户代码列表: " + String.join(",", accountCodes));
        } catch (Exception e) {
            System.err.println("读取失败: " + e.getMessage());
        }
    }
} 