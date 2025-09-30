package _37_weka;

import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.util.Random;

public class WekaExample {
    public static void main(String[] args) throws Exception {
        // Load dataset
        DataSource source = new DataSource("src/main/java/_037_weka/trainingData.arff");
        Instances data = source.getDataSet();
        data.setClassIndex(data.numAttributes() - 1);

        // Build classifier
        Classifier classifier = new J48();
        classifier.buildClassifier(data);

        // Evaluate model with cross-validation
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(classifier, data, 10, new Random(1));

        // Print evaluation results
        System.out.println(eval.toSummaryString("\nResults\n======\n", false));
        System.out.println(eval.toClassDetailsString());
        System.out.println(eval.toMatrixString());
    }
}