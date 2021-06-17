package sol;

import src.IAttributeDatum;
import src.ITreeNode;

import java.util.LinkedList;

public class Node implements ITreeNode{
    public Object attributeValuePrevious;

    public String attribute;

    public Object mostCommon;

    //make list of class edge
    public LinkedList<ITreeNode> children;

    public Node(Object attributeValuePrevious, String attribute, Object mostCommon, LinkedList<ITreeNode> children) {
        this.attributeValuePrevious = attributeValuePrevious;

        this.attribute = attribute;

        this.mostCommon = mostCommon;

        this.children = children;
    }


    @Override
    public Object lookupDecision(IAttributeDatum datum) {
        int x = this.children.size();
        //could edit interface to include getAttributeValuePrevious
        //cant edit interface, create class that stores attribute value and node, edge class
        //
        for (int i = 0; i <= x; i++) {
            if (datum.getValueOf(this.attribute).equals(this.children.get(i).attributeValuePrevious)) {
                this.children.get(i).lookupDecision(datum);
            }
        }

        return this.mostCommon;
    }

    @Override
    public void printNode(String leadSpace)

    }
}
