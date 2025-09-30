package _37_weka;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
 
public class DataPreprocessing {
    public static void main(String[] args) throws Exception {
        // 加载数据集
        DataSource source = new DataSource("src/main/java/_037_weka/inputData.arff");
        Instances data = source.getDataSet();
        
        // 设置类标签索引
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }
        
        // 打印数据集概要
        System.out.println(data.toSummaryString());
    }
}