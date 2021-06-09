package sol;

import src.IAttributeDataset;
import src.IAttributeDatum;
import src.ITreeGenerator;
import src.ITreeNode;

public class TreeGenerator<T extends IAttributeDatum> implements ITreeGenerator {

    /**
     * Constructor for a tree generator.
     * @param trainingData the training data on which to train the
     *                     decision tree
     */
    public TreeGenerator(IAttributeDataset<T> trainingData) {
        // TODO: implement
    }

    @Override
    public ITreeNode buildClassifier(String targetAttribute) {
        // TODO: implement
        return null;
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
