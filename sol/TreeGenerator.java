package sol;

import src.IAttributeDataset;
import src.IAttributeDatum;
import src.ITreeGenerator;
import src.ITreeNode;

import java.util.LinkedList;
import java.util.Random;

public class TreeGenerator<T extends IAttributeDatum> implements ITreeGenerator {

    public IAttributeDataset<T> trainingData;

    /**
     * Constructor for a tree generator.
     * @param trainingData the training data on which to train the
     *                     decision tree
     */
    public TreeGenerator(IAttributeDataset<T> trainingData) {
        this.trainingData = trainingData;
    }

    @Override
    public ITreeNode buildClassifier(String targetAttribute) {
        this.trainingData.getAttributes().remove(targetAttribute);
        Random random = new Random();
        int upperBound = trainingData.getAttributes().size() - 1;
        int randomNum = random.nextInt(upperBound);
        LinkedList<Edge> listOfEdges = new LinkedList<Edge>();
        Node root = new Node(trainingData.getAttributes().get(randomNum), this.trainingData.mostCommonValue(targetAttribute),
       listOfEdges);
        LinkedList<IAttributeDataset<T>> partList = (LinkedList<IAttributeDataset<T>>)
                this.trainingData.partition(this.trainingData.getAttributes().get(randomNum));
        int x = partList.size();

        for (int i = 0; i <= x; i++) {
            listOfEdges.add(partList.get(i).get(0));
        }

        // adjust upperbound to -1 because random is 0 inclusive when upperBound = # attributes
        for (int i = 0; i <= upperBound; i++) {

        }

    }

    @Override
    public Object lookupRecommendation(IAttributeDatum datum) {
        // TODO: implement
        return null;
    }

    @Override
    public void printTree() {
        // TODO: implement
    }
}
