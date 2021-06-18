package sol;

import src.IAttributeDataset;
import src.IAttributeDatum;
import src.ITreeGenerator;
import src.ITreeNode;

import java.util.LinkedList;
import java.util.Random;

public class TreeGenerator<T extends IAttributeDatum> implements ITreeGenerator {

    public IAttributeDataset<T> trainingData;

    public ITreeNode root;

    /**
     * Constructor for a tree generator.
     * @param trainingData the training data on which to train the
     *                     decision tree
     */
    public TreeGenerator(IAttributeDataset<T> trainingData) {

        this.trainingData = trainingData;

        this.root = null;

    }

    @Override
    public ITreeNode buildClassifier(String targetAttribute) {
        if (this.trainingData.getAttributes().contains(targetAttribute)) {
            this.trainingData.getAttributes().remove(targetAttribute);
        }

        Random random = new Random();
        int upperBound = this.trainingData.getAttributes().size();

        int randomNum = 0;

        if (upperBound != 0) {
            randomNum = random.nextInt(upperBound);
        } else {
            return null;
        }

        LinkedList<Edge> listOfEdges = new LinkedList<>();
        String currentAttribute = trainingData.getAttributes().get(randomNum);
        Node newNode = new Node(currentAttribute, this.trainingData.mostCommonValue(targetAttribute),
       listOfEdges);
        LinkedList<IAttributeDataset<T>> partList =  (LinkedList<IAttributeDataset<T>>)
                this.trainingData.partition(currentAttribute);
        int x = partList.size();

        for (int i = 0; i < x; i++) {
            if (partList.get(i).allSameValue(targetAttribute)) {
                listOfEdges.add(new Edge(partList.get(i).getSharedValue(currentAttribute),
                        new Leaf(partList.get(i).getSharedValue(targetAttribute))));
            // } else if (partList.get(i).getAttributes().size() == 0) {
             } else if (this.trainingData.getAttributes().size() == 0) {
                listOfEdges.add(new Edge(partList.get(i).getSharedValue(currentAttribute),
                        new Leaf(partList.get(i).mostCommonValue(targetAttribute))));
            } else {
                this.trainingData.getAttributes().remove(currentAttribute);
                listOfEdges.add(new Edge(partList.get(i).getSharedValue(currentAttribute),
                        buildClassifier(targetAttribute)));
            }
        }
        this.root = newNode;
        return newNode;
    }

    @Override
    public Object lookupRecommendation(IAttributeDatum datum) {
        return this.root.lookupDecision(datum);
    }

    @Override
    public void printTree() {
        // TODO: implement
    }
}
