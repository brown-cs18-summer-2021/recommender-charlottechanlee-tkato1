package sol;

import src.IAttributeDatum;
import src.ITreeNode;

/**
 * class representing a leaf which implements ITreeNode
 */

public class Leaf implements ITreeNode {
    /**
     * Object representing the value of the target attribute
     */
    public Object attributeValue;

    /**
     * Constructor for Leaf
     * @param attributeValue - Object which is the value of the target attribute
     */
    public Leaf(Object attributeValue) {

        this.attributeValue = attributeValue;

    }

    @Override
    public Object lookupDecision(IAttributeDatum datum) {
        return this.attributeValue;
    }

    @Override
    public void printNode(String leadSpace) {
        System.out.println("Decision: " + this.attributeValue);
    }

}
