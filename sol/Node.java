package sol;

import src.IAttributeDatum;
import src.ITreeNode;

import java.util.LinkedList;

public class Node implements ITreeNode{

    public String attribute;

    public Object mostCommon;

    public LinkedList<Edge> edgeList;

    public Node(String attribute, Object mostCommon, LinkedList<Edge> edgeList) {

        this.attribute = attribute;

        this.mostCommon = mostCommon;

        this.edgeList = edgeList;
    }


    @Override
    public Object lookupDecision(IAttributeDatum datum) {
        int x = this.edgeList.size();
        //could edit interface to include getAttributeValuePrevious
        //cant edit interface, create class that stores attribute value and node, edge class
        //
        for (int i = 0; i <= x; i++) {
            if (datum.getValueOf(this.attribute).equals(this.edgeList.get(i).edgeVal)) {
                this.edgeList.get(i).nextNode.lookupDecision(datum);
            }
        }
        return this.mostCommon;
    }

    @Override
    public void printNode(String leadSpace) {

    }
}
