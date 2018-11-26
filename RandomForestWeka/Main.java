import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Prediction;
import weka.classifiers.trees.RandomForest;
import weka.core.Instance;
import weka.core.Instances;

public class Main {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = null;
        int numFolds = 10;
        br = new BufferedReader(new FileReader("D:\\ShaansDrawer\\Semester6\\AI\\Image datset 2\\ImgSetFive.arff"));
 
        Instances trainData = new Instances(br);
        trainData.setClassIndex(trainData.numAttributes() - 1);
        br.close();
        RandomForest rf = new RandomForest();
        rf.setNumIterations(150);
        //rf.set
         
     //   rf.buildClassifier(trainData);
        Evaluation evaluation = new Evaluation(trainData);
        evaluation.crossValidateModel(rf, trainData, numFolds, new Random(1));
          
         
        System.out.println(evaluation.toSummaryString("\nResults\n======\n", true));
        System.out.println(evaluation.toClassDetailsString());
        System.out.println(evaluation.toMatrixString());
        System.out.println("Results For Class -1- ");
        System.out.println("Precision=  " + evaluation.precision(0));
        System.out.println("Recall=  " + evaluation.recall(0));
        System.out.println("F-measure=  " + evaluation.fMeasure(0));
        System.out.println("Results For Class -2- ");
        System.out.println("Precision=  " + evaluation.precision(1));
        System.out.println("Recall=  " + evaluation.recall(1));
        System.out.println("F-measure=  " + evaluation.fMeasure(1));
 
        AttributeSelection attrb = new AttributeSelection();
        
        InfoGainAttributeEval info = new InfoGainAttributeEval();
        
        Ranker rank = new Ranker();
        
        attrb.setEvaluator(info);
        attrb.setSearch(rank);
        attrb.SelectAttributes(trainData);
        
        System.out.println(attrb.toResultsString());
        
        ArrayList<Prediction> predictions = evaluation.predictions();
        for (int i = 0, trainDataSize = trainData.size(); i < trainDataSize; i++) {
                Instance instance = trainData.get(i);
                //int ind = instance.index(trainDataSize-1);
                Prediction prediction = predictions.get(i);

                if (prediction.actual() != prediction.predicted()) {
                	
                    System.out.println("Faulty : "+instance);
                    

                }
                else {
                	System.out.println("Matched : "+instance);
                	
                }
                System.out.println("Actual: "+prediction.actual()+" ");
            	System.out.println("Predict: "+prediction.predicted());
            	System.out.println();
            }
        
 
    }
}
