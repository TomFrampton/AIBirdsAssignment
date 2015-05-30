package u1171639.java.learning;

import java.io.BufferedReader;
import java.io.FileReader;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.classifiers.Classifier;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.pmml.consumer.Regression;

public class Weka {
	
	public static void main(String args[]) throws Exception {
		// Declare attributes
		Attribute meanPigPosition = new Attribute("meanPigPosition");
		Attribute meanBlockPosition = new Attribute("meanBlockPosition");
		
		// Declare class attribute
		Attribute releaseAngle = new Attribute("releaseAngle");
		
		// Declare instance in vector form
		FastVector vector = new FastVector(3);
		vector.addElement(meanPigPosition);
		vector.addElement(meanBlockPosition);
		vector.addElement(releaseAngle);
		
		// Create training set
		 Instances trainingSet = new Instances("Shots", vector, 10);
		 trainingSet.setClassIndex(2);
		 
		 // Create instance
		 Instance instance = new Instance(3);
		 instance.setValue((Attribute) vector.elementAt(0), 1.0);
		 instance.setValue((Attribute) vector.elementAt(1), 1.0);
		 instance.setValue((Attribute) vector.elementAt(2), 1.0);
		 
		 // Add the instance
		 trainingSet.add(instance);
		 
		 // Create the classifier
		 Classifier classifier = (Classifier) new LinearRegression();
		 classifier.buildClassifier(trainingSet);
		 
		 // Test the classifier
		 
		
	}
}