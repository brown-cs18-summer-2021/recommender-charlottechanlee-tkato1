package sol;

import com.sun.source.tree.Tree;
import src.IAttributeDatum;
import src.ITreeNode;
import src.TestAccuracy;

import java.util.LinkedList;

/**
 * class representing a Node which implements ITreeNode
 */

public class Node implements ITreeNode{
    /**
     * String representing the attribute the Edges of the Node is being partitioned based on.
     */
    public String attribute;
    /**
     * Object representing the mostCommon value of the target Attribute the Node is based on
     */
    public Object mostCommon;
    /**
     * A list of Edges which the Node splits into. (aka the children of the Node).
     */
    public LinkedList<Edge> edgeList;

    /**
     * Constructor for Node
     * @param attribute - String representing the attribute the Edges of the Node is being partitioned based on
     * @param mostCommon - most common value of the target Attribute Node is based on
     * @param edgeList - list of Edges (children) of the Node
     */
    public Node(String attribute, Object mostCommon, LinkedList<Edge> edgeList) {

        this.attribute = attribute;

        this.mostCommon = mostCommon;

        this.edgeList = edgeList;
    }


    @Override
    public Object lookupDecision(IAttributeDatum datum) {
        int x = this.edgeList.size();
        boolean edgeMatched = false;
        Object decision = null;

        for (int i = 0; i < x; i++) {
            if (datum.getValueOf(this.attribute).equals(this.edgeList.get(i).edgeVal)) {
                edgeMatched = true;
                decision = this.edgeList.get(i).nextNode.lookupDecision(datum);
            }
        }
        if (!edgeMatched) {
            decision = this.mostCommon;
        }

        return decision;
    }

    @Override
    public void printNode(String leadSpace) {
        System.out.println("Attribute: " + this.attribute);
        int x = this.edgeList.size();

        for (int i = 0; i < x; i++) {
            System.out.println(leadSpace + "Edge: " + this.edgeList.get(i).edgeVal);
            System.out.print(leadSpace);
            this.edgeList.get(i).nextNode.printNode(leadSpace + leadSpace);
        }
    }

}

