package src;

/**
 * Interface for a node in a decision tree.
 */
public interface ITreeNode {

    /**
     * Traverses the decision tree to get the predicted value of the tree's target attribute for a datum.
     * @param datum the datum for which to predict the value of the target attribute
     * @return the predicted value of the target attribute (the attribute the tree was trained to predict)
     */
    public Object lookupDecision(IAttributeDatum datum);

    /**
     * Prints the tree rooted at this node, prepending a given string of lead space.
     * @param leadSpace the lead space to print before printing the tree (this is to help form
     *                  an indented tree structure)
     */
    public void printNode(String leadSpace);
}
