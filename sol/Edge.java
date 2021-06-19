package sol;

import src.ITreeNode;

/**
 * class representing an Edge
 */
public class Edge {
    /**
     * Object representing the attribute value of the edge
     */
    public Object edgeVal;
    /**
     * ITreeNode representing the Node or Lead that the edge leads to
     */
    public ITreeNode nextNode;

    /**
     * Constructor for Edge
     * @param edgeVal
     * @param nextNode
     */
    public Edge (Object edgeVal, ITreeNode nextNode) {

        this.edgeVal = edgeVal;

        this.nextNode = nextNode;
    }

}
