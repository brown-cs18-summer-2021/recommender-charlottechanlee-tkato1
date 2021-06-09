package src;

import sol.DataTable;
import sol.TreeGenerator;
import test.Candidate;
import test.Mushroom;
import test.RecommenderTester;
import test.RestaurantPreferences;

/**
 * Class to help measure the accuracy of your decision trees. If you try to
 * run this file when you first download the stencil code, you will
 * get a NullPointerException. This is because you have not implemented your
 * TreeGenerator or your DataTable classes.
 *
 * DO NOT CHANGE ANYTHING IN THIS FILE EXCEPT FOR NUM_ITERATIONS.
 */
public class TestAccuracy {

    // the number of times to train and test the tree... the higher this number
    // is, the longer the test will take but the more precise it will be
    private static final int NUM_ITERATIONS = 100;

    private static final String MUSHROOMS_TRAINING = "data/mushrooms/training.csv";
    private static final String MUSHROOMS_TESTING = "data/mushrooms/testing.csv";

    private static final String VILLAINS_TRAINING = "data/villains/training.csv";
    private static final String VILLAINS_TESTING = "data/villains/testing.csv";

    private static final String CANDIDATES_TRAINING = "data/candidates/training-gender-equal.csv";
    private static final String CANDIDATES_TESTING = "data/candidates/testing.csv";

    /**
     * Tests your decision tree on several large datasets.
     */
    public static void runTests() throws Exception {
        TestAccuracy.testAccuracy(Mushroom.class,
                MUSHROOMS_TRAINING,MUSHROOMS_TESTING, "isPoisonous");
        TestAccuracy.testAccuracy(RestaurantPreferences.class,
                VILLAINS_TRAINING, VILLAINS_TESTING, "isVillain");
        TestAccuracy.testAccuracy(Candidate.class,
                CANDIDATES_TRAINING, CANDIDATES_TESTING, "hired");
        TestAccuracy.testAccuracy(Mushroom.class,
                MUSHROOMS_TRAINING, MUSHROOMS_TRAINING, "isPoisonous");
    }

    /**
     * Trains your decision tree on a training dataset and tests on testing dataset
     * many times and averages the classification accuracy.
     * @param dataClass the class of datum
     * @param trainingDataPath the path to the training csv file
     * @param testingDataPath the path to the testing csv file
     * @param targetAttribute the attribute to predict
     * @param <D> the type of the datum
     */
    public static <D extends IAttributeDatum> void testAccuracy(
            Class<D> dataClass, String trainingDataPath, String testingDataPath,
            String targetAttribute) throws Exception {

        RecommenderTester<D, DataTable<D>, TreeGenerator<D>> tester =
                new RecommenderTester(dataClass, DataTable.class, TreeGenerator.class);

        double accuracy = tester.getDecisionTreeAccuracy(trainingDataPath,
                testingDataPath, targetAttribute, NUM_ITERATIONS);
        System.out.println("Trained on " + trainingDataPath);
        System.out.println("Tested on " + testingDataPath);

        // remember that testing your tree on the same data that you trained it
        // on is 'cheating', so accuracy in that case should be near 100%
        if (trainingDataPath.equals(testingDataPath)) {
            System.out.println("Training data and testing data are the same, "
                    + "so accuracy should be near 1.0");
        }

        System.out.println("Accuracy: " + accuracy + "\n");
    }

    public static void main(String[] args) throws Exception {
        TestAccuracy.runTests();
    }
}
