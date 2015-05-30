package u1171639.java.agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import u1171639.java.game.Game;
import u1171639.java.game.Level;
import weka.classifiers.Classifier;
import weka.classifiers.functions.LinearRegression;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class RegressionAgent implements Agent {
	private Game game;
	
	private List<Instance> trainingExamples = new ArrayList<Instance>();
	private Instances trainingSet;
	private FastVector featureVector; // Feature vector for our instances
	private Classifier trainedClassifier;
	
	private boolean classifierSuggestionFailed; // Did the classifiers last suggestion fail?
	
	private Random random = new Random(System.currentTimeMillis());
	
	// Keep statistics for performance analysis
	private int totalShotsTaken = 0;
	private int shotsTakenOnCurrentLevel = 0;
	private int levelsCompleted = 0;
	
	
	public RegressionAgent() {
		this.initWeka();
	}
	
	@Override
	public String getName() {
		return "Regression Agent";
	}

	@Override
	public String getDescription() {
		return "Learns using regression";
	}
	
	@Override
	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public void runContinuously() {
		// Keep going until we can't get anymore level info
		while(!game.isCompleted()) {
			this.runOneLevel();
		}
	}

	@Override
	public void runOneLevel() {
		// Reset level statistics
		this.shotsTakenOnCurrentLevel = 0;
		
		// Get the level environment
		Level currentLevel = this.game.getNextLevelInfo();
		
		// While there are still pigs to kill
		while(!currentLevel.isCompleted()) {
			
			double releaseAngle = 0;
			
			// Get the attribute values - may need them later to create a training instance
			double meanPigXPosition = currentLevel.meanPigPosition().getX();
			double meanPigYPosition = currentLevel.meanPigPosition().getY();
			
			double pigXStandardDev = currentLevel.pigStandardDeviation().getX();
			double pigYStandardDev = currentLevel.pigStandardDeviation().getY();
			
			// If we have a trained classifier then use it
			if(this.trainedClassifier != null && !this.classifierSuggestionFailed) {
				// Create an instance to classify
				releaseAngle = this.classifyInstance(meanPigXPosition, meanPigYPosition, pigXStandardDev, pigYStandardDev);
			} else {
				// No classifier trained yet so use random shot
				releaseAngle = this.random.nextDouble() * 90;
				
			}
			
			boolean successfulShot = currentLevel.takeShot(releaseAngle);
			
			// If the shot was successful use it to train the classifier
			if(successfulShot) {
				Instance instance = new Instance(5);
				instance.setValue((Attribute) this.featureVector.elementAt(0), meanPigXPosition);
				instance.setValue((Attribute) this.featureVector.elementAt(1), meanPigYPosition);
				instance.setValue((Attribute) this.featureVector.elementAt(2), pigXStandardDev);
				instance.setValue((Attribute) this.featureVector.elementAt(3), pigYStandardDev);
				instance.setValue((Attribute) this.featureVector.elementAt(4), releaseAngle);
				this.trainingExamples.add(instance);
				
				this.trainedClassifier = this.trainClassifier();
				this.classifierSuggestionFailed = false;
			} else {
				this.classifierSuggestionFailed = true;
			}

			// Increment statistics
			this.totalShotsTaken++;
			this.shotsTakenOnCurrentLevel++;
		}
		
		this.levelsCompleted++;
	}

	@Override
	public int shotsTakenForCurrentLevel() {
		return this.shotsTakenOnCurrentLevel;
	}

	@Override
	public int totalShotsTaken() {
		return this.totalShotsTaken;
	}

	@Override
	public int levelsCompleted() {
		return this.levelsCompleted;
	}

	@Override
	public double averageShotsPerLevel() {
		if(this.levelsCompleted > 0) {
			return this.totalShotsTaken / this.levelsCompleted;
		} else {
			return Integer.MAX_VALUE;
		}
	}
	
	private void initWeka() {
		// Declare attributes
		Attribute meanPigXPosition = new Attribute("meanPigXPosition");
		Attribute meanPigYPosition = new Attribute("meanPigYPosition");
		Attribute pigXStandardDev = new Attribute("pigXStandardDev");
		Attribute pigYStandardDev = new Attribute("pigYStandardDev");
		
		// Declare class attribute
		Attribute releaseAngle = new Attribute("releaseAngle");
		
		// Declare instance in vector form
		this.featureVector = new FastVector(5);
		this.featureVector.addElement(meanPigXPosition);
		this.featureVector.addElement(meanPigYPosition);
		this.featureVector.addElement(pigXStandardDev);
		this.featureVector.addElement(pigYStandardDev);
		this.featureVector.addElement(releaseAngle);
		
		// Create training set
		this.trainingSet = new Instances("Killing Shots", this.featureVector, 0);
		this.trainingSet.setClassIndex(4);
	}
	
	private Classifier trainClassifier() {
		// Create training set
		this.trainingSet = new Instances("Killing Shots", this.featureVector, this.trainingExamples.size());
		this.trainingSet.setClassIndex(4);
				 
		// Add all training examples to the training set
		for(Instance instance : this.trainingExamples) {
			this.trainingSet.add(instance);
		}
				 
		// Create the classifier
		Classifier classifier = (Classifier) new LinearRegression();
		try {
			// Train the classifier using the training set
			classifier.buildClassifier(trainingSet);
		} catch (Exception e) {
			System.err.println("Error training classifier: " + e);
		}
		
		return classifier;
	}
	
	private double classifyInstance(double meanPigXPosition, double meanPigYPosition, double pigXStandardDev, double pigYStandardDev) {
		Instance instance = new Instance(5);
		instance.setValue((Attribute) this.featureVector.elementAt(0), meanPigXPosition);
		instance.setValue((Attribute) this.featureVector.elementAt(1), meanPigYPosition);
		instance.setValue((Attribute) this.featureVector.elementAt(2), pigXStandardDev);
		instance.setValue((Attribute) this.featureVector.elementAt(3), pigYStandardDev);
		
		instance.setDataset(this.trainingSet);
		
		try {
			return this.trainedClassifier.classifyInstance(instance);
		} catch (Exception e) {
			System.err.println("Error classifying instance: " + e);
			return 0.0;
		}
	}
}
