package sol;

import tester.Tester;

import java.util.LinkedList;

public class RecommenderTestSuite {
    public Vegetable setupBasicList() {
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
        DataTable testingDataTableSmall = new DataTable(heading, objects);
        objects.addLast(carrot);
        objects.addLast(lettuce);
        DataTable testingDataTable = new DataTable(heading, objects);
    }

        public void testDataTableConstructorTest(Tester t) {
        }
        public void testSizeTest(Tester t) {
            t.checkExpect(testingDataTable.size(), 5);
        };
        public void testAllSameValueTest(Tester t){
            t.checkExpect(testingDataTable.allSameValue("color"), false);
            t.checkExpect(testingDataTable.allSameValue("low carb"), false);
            t.checkExpect(testingDataTable.allSameValue("high fiber"), false);
            t.checkExpect(testingDataTable.allSameValue("like to eat"), false);
            t.checkExpect(testingDataTableSmall.allSameValue("color"), true);
        };
        public void testGetSharedValue(Tester t) {
            t.checkExpect(testingDataTableSmall.getSharedValue("color"), "green");
            t.checkException();
            // test the throwing error if not all the colors are the same for testingDataTable
        };
        public void testMostCommonValueTest(Tester t) {
            t.checkExpect(testingDataTable.mostCommonValue("color"), "green");
            t.checkExpect(testingDataTableSmall.mostCommonValue("color"), "green");
        }
        public void testGetAttributesTest(Tester t) {
            LinkedList<String> headingsTest = new LinkedList<>();
            headingsTest.addLast("color");
            headingsTest.addLast("low carb");
            headingsTest.addLast("high fiber");
            headingsTest.addLast("like to eat");
            t.checkExpect(testingDataTable.getAttributes(), headingsTest);
        }

        public static void main(String[] args) {
            Tester.run(new RecommenderTestSuite());
        }
    }

}
