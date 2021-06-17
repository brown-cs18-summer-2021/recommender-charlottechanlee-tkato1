package sol;

import src.IAttributeDatum;
import src.ITreeNode;

public class Leaf implements ITreeNode {

    public Object attributeValue;

    public Leaf(Object attributeValue) {

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
