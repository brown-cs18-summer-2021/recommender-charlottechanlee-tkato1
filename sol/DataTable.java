package sol;

import src.IAttributeDataset;
import src.IAttributeDatum;

import java.util.LinkedList;
import java.util.List;

/**
 * Class for a table (collection of rows) of data.
 * @param <T> the type of the data objects
 */

public class DataTable<T extends IAttributeDatum> implements IAttributeDataset<T> {

     LinkedList<String> attributes;
     LinkedList<T> dataObjects;


    public DataTable(LinkedList<String> attributes, LinkedList<T> dataObjects) {
        this.attributes = attributes;

        this.dataObjects = dataObjects;
    }

    @Override
    public List<String> getAttributes() {
        return this.attributes;
    }

    @Override
    public List<T> getDataObjects() {
        return this.dataObjects;
    }

    @Override
    public int size() {
        return this.dataObjects.size();
    }

    @Override
    public boolean allSameValue(String ofAttribute) {
        int x = this.size();
        Object attributeValue = this.dataObjects.get(0).getValueOf(ofAttribute);
        for (int i = 1; i <= x; i++) {
            if (this.dataObjects.get(i).getValueOf(ofAttribute).equals(attributeValue)) {
                i = i + 1;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object getSharedValue(String ofAttribute) {
        if (allSameValue(ofAttribute)) {
            return this.dataObjects.get(0).getValueOf(ofAttribute);
        }
        else {
            throw new RuntimeException("attribute value is not the same in all objects");
        }
    }

    @Override
    public Object mostCommonValue(String ofAttribute) {
        int x = this.dataObjects.size();
        LinkedList<Object> attributeValues = new LinkedList<>();
        LinkedList<Integer> attributeValueCounts = new LinkedList<>();
        for (int i = 0; i <= x; i++) {
            if (attributeValues.contains(this.dataObjects.get(i).getValueOf(ofAttribute))) {
                Integer currentCount = attributeValueCounts.get(attributeValues.indexOf(ofAttribute));
                attributeValueCounts.set(attributeValues.indexOf(ofAttribute), currentCount + 1);
            } else {
                attributeValues.addLast(this.dataObjects.get(i).getValueOf(ofAttribute));
                attributeValueCounts.addLast(0);
            }
        }
        int y = attributeValues.size();
        Integer mostCommonCount = attributeValueCounts.get(0);
        Object mostCommon = attributeValues.get(0);
        for (int j = 1; j <= y; j++) {
            if (attributeValueCounts.get(j) > mostCommonCount) {
                mostCommonCount = attributeValueCounts.get(j);
                mostCommon = attributeValues.get(j);
            }
        }
        return mostCommon;

    }


    @Override
    public List<IAttributeDataset<T>> partition(String onAttribute) {

        int x = this.dataObjects.size();
        LinkedList<IAttributeDataset<T>> outputList = new LinkedList<>();

        for (int i = 0; i <= x; i++) {
            T currentDatum = this.dataObjects.get(i);
            int outputSize = outputList.size();
            boolean alreadyAdded = false;
            for (int j = 0; j <= outputSize; j++) {
                Object currentSetValue = outputList.get(j).getDataObjects().get(0).getValueOf(onAttribute);
                if (currentSetValue.equals(currentDatum.getValueOf(onAttribute))) {
                    outputList.get(j).getDataObjects().add(currentDatum);
                    alreadyAdded = true;
                    break;
                }
            }
            if (!alreadyAdded) {
                LinkedList<T> newObjectList = new LinkedList<T>();
                newObjectList.addLast(this.dataObjects.get(i));
                DataTable<T> newSet = new DataTable<>(this.attributes, newObjectList);
                outputList.addLast(newSet);
            }
        }
        return outputList;
    }

}


