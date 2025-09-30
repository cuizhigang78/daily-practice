package _37_weka;

import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
 
import java.util.Random;
 
public class ModelEvaluation {
    public static void main(String[] args) throws Exception {
        // 加载数据集
        DataSource source = new DataSource("path/to/iris.arff");
        Instances data = source.getDataSet();
        data.setClassIndex(data.numAttributes() - 1);
 
        // 构建分类模型
        Classifier classifier = new J48();
        classifier.buildClassifier(data);
 
        // 交叉验证评估
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(classifier, data, 10, new Random(1));
 
        // 打印评估结果
        System.out.println(eval.toSummaryString("\nResults\n======\n", false));
        System.out.println(eval.toClassDetailsString());
        System.out.println(eval.toMatrixString());
    }
}