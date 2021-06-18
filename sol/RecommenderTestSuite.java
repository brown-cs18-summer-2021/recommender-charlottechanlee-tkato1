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
        heading.addLast("lowCarb");
        heading.addLast("highFiber");
        heading.addLast("likeToEat");
        LinkedList<Object> objects = new LinkedList<Object>();
        objects.addLast(spinach);
        objects.addLast(kale);
        objects.addLast(peas);
        DataTable dataTableSmall = new DataTable(heading, objects);
        return dataTableSmall;
    }
    public DataTable dtLarge() {
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
        return testingDataTableLarge;
    }

    public DataTable subset1() {
        Vegetable spinach = new Vegetable("green", true, true, false);
        Vegetable kale = new Vegetable("green", true, true, true);
        Vegetable peas = new Vegetable("green", false, true, true);
        LinkedList<String> heading = new LinkedList<String>();
        heading.addLast("lowCarb");
        heading.addLast("highFiber");
        heading.addLast("likeToEat");
        LinkedList<Object> objects = new LinkedList<Object>();
        objects.addLast(spinach);
        objects.addLast(kale);
        objects.addLast(peas);
        DataTable subset1 = new DataTable(heading, objects);
        return subset1;
    }
    public DataTable subset2() {
        Vegetable spinach = new Vegetable("green", true, true, false);
        Vegetable kale = new Vegetable("green", true, true, true);
        Vegetable peas = new Vegetable("green", false, true, true);
        Vegetable lettuce = new Vegetable("green", true, false, true);
        LinkedList<String> heading = new LinkedList<String>();
        heading.addLast("lowCarb");
        heading.addLast("highFiber");
        heading.addLast("likeToEat");
        LinkedList<Object> objects = new LinkedList<Object>();
        objects.addLast(spinach);
        objects.addLast(kale);
        objects.addLast(peas);
        objects.addLast(lettuce);
        DataTable subset2 = new DataTable(heading, objects);
        return subset2;
    }

    public DataTable subset3() {
        Vegetable carrot = new Vegetable("orange", false, false, false);
        LinkedList<String> heading = new LinkedList<String>();
        heading.addLast("lowCarb");
        heading.addLast("highFiber");
        heading.addLast("likeToEat");
        LinkedList<Vegetable> objects = new LinkedList<>();
        objects.addLast(carrot);
        DataTable subset3 = new DataTable<>(heading, objects);
        return subset3;
    }


    public void testDataTableConstructorTest (Tester t) {
    }
    public void testSize (Tester t) {
        DataTable<Vegetable> testSmallDataTable = dtSmall();
        DataTable<Vegetable> testBigDataTable = dtLarge();
        t.checkExpect(testBigDataTable.size(), 5);
        t.checkExpect(testSmallDataTable.size(), 3);
    }

    public void testAllSameValue(Tester t){
        DataTable<Vegetable> testSmallDataTable = dtSmall();
        DataTable<Vegetable> testBigDataTable = dtLarge();
        t.checkExpect(testBigDataTable.allSameValue("color"), false);
        t.checkExpect(testSmallDataTable.allSameValue("color"), true);
        t.checkExpect(testBigDataTable.allSameValue("lowCarb"), false);
        t.checkExpect(testBigDataTable.allSameValue("highFiber"), false);
        t.checkExpect(testBigDataTable.allSameValue("likeToEat"), false);
    }

    public void testGetSharedValue(Tester t) {
        DataTable<Vegetable> testSmallDataTable = dtSmall();
        DataTable<Vegetable> testBigDataTable = dtLarge();
        t.checkExpect(testSmallDataTable.getSharedValue("color"), "green");
        t.checkExpect(testSmallDataTable.getSharedValue("highFiber"), true);
        t.checkException(new RuntimeException("attribute value is not the same in all objects"),
                testBigDataTable, "getSharedValue", "color");
    }

    public void testMostCommonValue (Tester t) {
        DataTable<Vegetable> testSmallDataTable = dtSmall();
        DataTable<Vegetable> testBigDataTable = dtLarge();
        t.checkExpect(testBigDataTable.mostCommonValue("color"), "green");
        t.checkExpect(testSmallDataTable.mostCommonValue("color"), "green");
        t.checkExpect(testSmallDataTable.mostCommonValue("highFiber"), true);
    }

    public void testPartition (Tester t) {
        IAttributeDataset<T> testSmallDataTable = dtSmall();
        IAttributeDataset<T> testBigDataTable = dtLarge();
        IAttributeDataset<T> testSubset1 = subset1();
        IAttributeDataset<T> testSubset2 = subset2();
        IAttributeDataset<T> testSubset3 = subset3();

        LinkedList<IAttributeDataset<T>> test1 = new LinkedList<>();

        test1.addFirst(testSubset1);
        t.checkExpect(testSmallDataTable.partition("color"), test1);
        LinkedList<IAttributeDataset<T>> test2 = new LinkedList<IAttributeDataset<T>>();
        test2.addFirst(testSubset2);
        test2.addLast(testSubset3);
        t.checkExpect(testBigDataTable.partition("color"), test2);
    }

    public void testGetAttributes (Tester t) {
        IAttributeDataset<T> testSmallDataTable = dtSmall();
        IAttributeDataset<T> testBigDataTable = dtLarge();

        LinkedList<String> headingsTest = new LinkedList<>();
        headingsTest.addLast("color");
        headingsTest.addLast("lowCarb");
        headingsTest.addLast("highFiber");
        headingsTest.addLast("likeToEat");
        t.checkExpect(testBigDataTable.getAttributes(), headingsTest);
    }

    public static void main(String[] args) {
        Tester.run(new RecommenderTestSuite());
    }
}
