package sol;

import src.IAttributeDataset;
import src.IAttributeDatum;
import src.ITreeGenerator;
import src.ITreeNode;

import java.util.Random;

public class TreeGenerator<T extends IAttributeDatum> implements ITreeGenerator {

    public IAttributeDataset<T> trainingData;

    /**
     * Constructor for a tree generator.
     * @param trainingData the training data on which to train the
     *                     decision tree
     */
    public TreeGenerator(IAttributeDataset<T> trainingData) {
        this.trainingData = trainingData;
    }

    @Override
    public ITreeNode buildClassifier(String targetAttribute) {
        this.trainingData.attributes.remove(targetAttribute);
        Random random = new Random();
        int upperBound = trainingData.attributes.size() - 1;
        this.trainingData.mostCommonValue;
        Node root = new Node(null; trainingData.attributes.get(randomNum), this.trainingData.mostCommonValue;
        int randomNum = random.nextInt(upperBound);
        // adjust upperbound to -1 because random is 0 inclusive when upperBound = # attributes
        for (int i = 0; i <= upperBound; i++) {

        }

    }

    @Override
    public Object lookupRecommendation(IAttributeDatum datum) {
        // TODO: implement
        return null;
    }

    @Override
    public void printTree() {
        // TODO: implement
    }
}
