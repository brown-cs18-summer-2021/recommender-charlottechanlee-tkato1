package sol;

import src.IAttributeDataset;
import src.IAttributeDatum;
import tester.Tester;

import javax.xml.crypto.Data;
import java.util.LinkedList;
import java.util.List;

public class RecommenderTestSuite<T extends IAttributeDatum> {

    public DataTable dtSmall() {
        Vegetable spinach = new Vegetable("green", true, true, false);
        Vegetable kale = new Vegetable("green", true, true, true);
        Vegetable peas = new Vegetable("green", false, true, true);
        LinkedList<String> heading = new LinkedList<String>();
        heading.addLast("color");
        heading.addLast("low carb");
        heading.addLast("high fiber");
        heading.addLast("like to eat");
        LinkedList<Object> objects = new LinkedList<Object>();
        objects.addLast(spinach);
        objects.addLast(kale);
        objects.addLast(peas);
        DataTable testingDataTableSmall = new DataTable(heading, objects);
        return testingDataTableSmall;
    }
    public DataTable dtLarge() {
        Vegetable spinach = new Vegetable("green", true, true, false);
        Vegetable kale = new Vegetable("green", true, true, true);
        Vegetable peas = new Vegetable("green", false, true, true);
        Vegetable carrot = new Vegetable("orange", false, false, false);
        Vegetable lettuce = new Vegetable("green", true, false, true);
        LinkedList<String> heading = new LinkedList<String>();
        heading.addLast("color");
        heading.addLast("low carb");
        heading.addLast("high fiber");
        heading.addLast("like to eat");
        LinkedList<Object> objects = new LinkedList<Object>();
        objects.addLast(spinach);
        objects.addLast(kale);
        objects.addLast(peas);
        objects.addLast(carrot);
        objects.addLast(lettuce);
        DataTable testingDataTableLarge = new DataTable(heading, objects);
        return testingDataTableLarge;
    }

    public DataTable subset1(){
        Vegetable spinach = new Vegetable("green", true, true, false);
        Vegetable kale = new Vegetable("green", true, true, true);
        Vegetable peas = new Vegetable("green", false, true, true);
        Vegetable lettuce = new Vegetable("green", true, false, true);
        LinkedList<String> heading = new LinkedList<String>();
        heading.addLast("color");
        heading.addLast("low carb");
        heading.addLast("high fiber");
        heading.addLast("like to eat");
        LinkedList<Object> objects = new LinkedList<Object>();
        objects.addLast(spinach);
        objects.addLast(kale);
        objects.addLast(peas);
        objects.addLast(lettuce);
        DataTable subset1 = new DataTable(heading, objects);
        return subset1;
    }

    public DataTable subset2() {
        Vegetable carrot = new Vegetable("orange", false, false, false);
        LinkedList<String> heading = new LinkedList<String>();
        heading.addLast("color");
        heading.addLast("low carb");
        heading.addLast("high fiber");
        heading.addLast("like to eat");
        LinkedList<Object> objects = new LinkedList<Object>();
        objects.addLast(carrot);
        DataTable subset2 = new DataTable(heading, objects);
        return subset2;
    }


    public void dataTableConstructorTest (Tester t) {
    }
    public void sizeTest (Tester t) {
        t.checkExpect(dtLarge().size(), 5);
        t.checkExpect(dtSmall().size(), 3);
    };
    public void allSameValueTest(Tester t){
        t.checkExpect(dtLarge().allSameValue("color"), false);
        t.checkExpect(dtSmall().allSameValue("color"), true);
        t.checkExpect(dtLarge().allSameValue("low carb"), false);
        t.checkExpect(dtLarge().allSameValue("high fiber"), false);
        t.checkExpect(dtLarge().allSameValue("like to eat"), false);
    };

    public void getSharedValue(Tester t) {
        t.checkExpect(dtSmall().getSharedValue("color"), "green");
        t.checkExpect(dtSmall().getSharedValue("high fiber"), true);
        t.checkException(new RuntimeException("value is not common"), dtLarge(), "getShareValue");
    };

    public void mostCommonValueTest (Tester t) {
        t.checkExpect(dtLarge().mostCommonValue("color"), "green");
        t.checkExpect(dtSmall().mostCommonValue("color"), "green");
        t.checkExpect(dtSmall().mostCommonValue("high fiber"), true);
    }

    public void partitionTest (Tester t) {
        LinkedList<IAttributeDataset<T>> test1 = new LinkedList<IAttributeDataset<T>>();
        test1.addFirst(dtSmall());
        t.checkExpect(dtSmall().partition("color"), test1);
        LinkedList<IAttributeDataset<T>> test2 = new LinkedList<IAttributeDataset<T>>();
        test2.addFirst(subset1());
        test2.addLast(subset2());
        t.checkExpect(dtSmall().partition("color"), test2);
    }
    public void getAttributesTest (Tester t) {
        LinkedList<String> headingsTest = new LinkedList<>();
        headingsTest.addLast("color");
        headingsTest.addLast("low carb");
        headingsTest.addLast("high fiber");
        headingsTest.addLast("like to eat");
        t.checkExpect(dtLarge().getAttributes(), headingsTest);
    }

    public static void main(String[] args) {
        Tester.run(new RecommenderTestSuite());
    }
}

