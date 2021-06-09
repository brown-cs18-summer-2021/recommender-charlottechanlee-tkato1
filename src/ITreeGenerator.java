package src;

/**
 * Interface for classes that can train and apply a decision tre
 */
public interface ITreeGenerator {

    /**
     * Trains a decision tree to predict the value of a target attribute.
     * @param targetAttribute the attribute that the decision tree will predict the value of
     * @return a trained decision tree that will predict the value of the target attribute for a given datum
     */
    public ITreeNode buildClassifier(String targetAttribute);

    /**
     * Uses the decision tree trained by buildClassifier to get the predicted value of the target attribute for
     * a given datum.
     * @param datum the datum to classify
     * @return the predicted value (recommendation) of the target attribute for the given datum
     */
    public Object lookupRecommendation(IAttributeDatum datum);

    /**
     * Prints the decision tree trained by buildClassifier.
     */
    public void printTree();
}
