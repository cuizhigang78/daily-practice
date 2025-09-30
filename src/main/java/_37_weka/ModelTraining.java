package _37_weka;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import static _37_weka.Constants.TRAINING_DATA_FILE_PATH;

public class ModelTraining {
    public static void main(String[] args) throws Exception {
        // 加载数据集
        DataSource source = new DataSource(TRAINING_DATA_FILE_PATH);
        Instances data = source.getDataSet();
        data.setClassIndex(data.numAttributes() - 1);
 
        // 构建分类模型
        Classifier classifier = new J48();
        classifier.buildClassifier(data);
 
        // 打印模型信息
        System.out.println(classifier);
    }
}