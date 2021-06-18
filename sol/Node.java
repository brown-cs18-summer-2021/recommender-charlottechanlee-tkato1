package sol;

import com.sun.source.tree.Tree;
import src.IAttributeDatum;
import src.ITreeNode;
import src.TestAccuracy;

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
        for (int i = 0; i < x; i++) {
            if (datum.getValueOf(this.attribute).equals(this.edgeList.get(i).edgeVal)) {
                this.edgeList.get(i).nextNode.lookupDecision(datum);
            }
        }
        return this.mostCommon;
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

    // uncomment below to use printNode on root node

    public static void main(String[] args) {
        Vegetable spinach = new Vegetable("green", true, true, false);
        Vegetable kale = new Vegetable("green", true, true, true);
        Vegetable peas = new Vegetable("green", false, true, true);
        Vegetable carrot = new Vegetable("orange", false, false, false);
        Vegetable lettuce = new Vegetable("green", true, false, true);
        LinkedList<String> heading = new LinkedList<String>();
        heading.addLast("color");
        heading.addLast("lowCarb");
        heading.addLast("highFiber");
        heading.addLast("likeToEat");
        LinkedList<Object> objects = new LinkedList<Object>();
        objects.addLast(spinach);
        objects.addLast(kale);
        objects.addLast(peas);
        objects.addLast(carrot);
        objects.addLast(lettuce);
        DataTable testingDataTableLarge = new DataTable(heading, objects);
        TreeGenerator<Vegetable> tree = new TreeGenerator<>(testingDataTableLarge);
        tree.buildClassifier("likeToEat");
        // tree.printTree();

        Vegetable cabbage = new Vegetable("yellow", true, false, true);
        tree.lookupRecommendation(cabbage);
    }
}

