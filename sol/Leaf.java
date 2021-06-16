package sol;

import src.IAttributeDatum;
import src.ITreeNode;

public class Leaf implements ITreeNode {
    public Object attributeValuePrevious;

    public Object attributeValue;

    public Leaf(Object attributeValuePrevious, Object attributeValue) {
        this.attributeValuePrevious = attributeValuePrevious;

        this.attributeValue = attributeValue;

    }

    @Override
    public Object lookupDecision(IAttributeDatum datum) {
        return this.attributeValue;
    }

    @Override
    public void printNode(String leadSpace) {

    }
}
