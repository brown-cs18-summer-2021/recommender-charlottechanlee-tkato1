package sol;

import src.ITreeNode;

public class Edge {
    public Object edgeVal;
    public ITreeNode nextNode;

    public Edge (Object edgeVal, ITreeNode nextNode) {
        this.edgeVal = edgeVal;
        this.nextNode = nextNode;
    }

    public ITreeNode getNext() {
        return this.nextNode;
    }

    public Object getEdgeVal() {
        return this.edgeVal;
    }
}
