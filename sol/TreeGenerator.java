package sol;

import src.IAttributeDataset;
import src.IAttributeDatum;
import src.ITreeGenerator;
import src.ITreeNode;

import java.util.LinkedList;
import java.util.List;
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
        if (trainingData.size() == 0) {
            throw new IllegalArgumentException("trainingData is empty");
        }

        this.trainingData = trainingData;

        this.root = null;

    }


    public ITreeNode buildClassifierHelper(String targetAttribute, IAttributeDataset<T> subset) {
        if (subset.getAttributes().contains(targetAttribute)) {
            subset.getAttributes().remove(targetAttribute);
        }
        List<String> attributeList = new LinkedList<String>(subset.getAttributes());

        if (subset.allSameValue(targetAttribute)) {
            return new Leaf(subset.getSharedValue(targetAttribute));
        }
        // base case: if there are no more unused attributes, return the most common value of the target attribute
        else if (attributeList.size() == 0) {
            return new Leaf(subset.mostCommonValue(targetAttribute));

            // there are still unused attributes
        } else {
            LinkedList<Edge> edgeList = new LinkedList<Edge>();
            // construct a new node for the randomly chosen attribute (edge list initialized to zero)
            Random random = new Random();
            int upperBound = attributeList.size();
            int randomNum = random.nextInt(upperBound);
            String currentAttribute = attributeList.get(randomNum);
            Node newNode = new Node(currentAttribute, subset.mostCommonValue(targetAttribute), edgeList);

            // loop through the list of partitioned data sets to fill the list of edges
            List<IAttributeDataset<T>> partList = subset.partition(currentAttribute);

            // must create a new edge for every value that falls under the attribute
            for (int i = 0; i < partList.size(); i++) {
                Edge newEdge = new Edge(partList.get(i).getSharedValue(currentAttribute),
                        buildClassifierHelper(targetAttribute, partList.get(i)));
                edgeList.add(newEdge);
            }
            return newNode;
        }

    }

    @Override
    public ITreeNode buildClassifier(String targetAttribute) {
        this.root = buildClassifierHelper(targetAttribute, this.trainingData);

        return this.root;
    }


    @Override
    public Object lookupRecommendation(IAttributeDatum datum) {
        return this.root.lookupDecision(datum);
    }

    @Override
    public void printTree() {
        this.root.printNode("   ");
    }
}
