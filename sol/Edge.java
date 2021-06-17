package sol;

import src.ITreeNode;

public class Edge {
    public Object edgeVal;
    public ITreeNode nextNode;

    public Edge (Object e, ITreeNode n) {
        this.edgeVal = e;
        this.nextNode = n;
    }

    public ITreeNode getNext() {
        return this.nextNode;
    }

    public Object getEdgeVal() {
        return this.edgeVal;
    }
}
