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
    /**
     * List of Strings of the attributes that the T datums have in the DataTable
     */
     List<String> attributes;
    /**
     * List of T (datums) that the DataTable contains
     */
    List<T> datumList;

    /**
     * Constructor for DataTable
     * @param attributes - List of Strings of the attributes that the T datums have in the DataTable
     * @param datumList - List of T (datums) that the DataTable contains
     */

    public DataTable(List<String> attributes, List<T> datumList) {

        this.attributes = attributes;

        this.datumList = datumList;
    }

    @Override
    public List<String> getAttributes() {
        return this.attributes;
    }

    @Override
    public List<T> getDataObjects() {
        return this.datumList;
    }

    @Override
    public int size() {
        return this.datumList.size();
    }

    @Override
    public boolean allSameValue(String ofAttribute) {
        int x = this.size();
        Object attributeValue = this.datumList.get(0).getValueOf(ofAttribute);

        for (int i = 1; i < x; i++) {
            if (!this.datumList.get(i).getValueOf(ofAttribute).equals(attributeValue)) {

                return false;
            }
        }

        return true;
    }


    @Override
    public Object getSharedValue(String ofAttribute) {
        if (allSameValue(ofAttribute)) {

            return this.datumList.get(0).getValueOf(ofAttribute);
        }
        else {
            throw new RuntimeException("attribute value is not the same in all objects");
        }
    }

    @Override
    public Object mostCommonValue(String ofAttribute) {
        int datumListSize = this.datumList.size();
        LinkedList<Object> attributeValues = new LinkedList<>();
        LinkedList<Integer> attributeValueCounts = new LinkedList<>();
        for (int i = 0; i < datumListSize; i++) {
            Object currentAttributeValue = this.datumList.get(i).getValueOf(ofAttribute);
            if (attributeValues.contains(currentAttributeValue)) {
                Integer currentCount = attributeValueCounts.get(attributeValues.indexOf(currentAttributeValue));
                attributeValueCounts.set(attributeValues.indexOf(currentAttributeValue), currentCount + 1);
            } else {
                attributeValues.addLast(this.datumList.get(i).getValueOf(ofAttribute));
                attributeValueCounts.addLast(0);
            }
        }

        int distinctValueSize = attributeValues.size();
        Integer mostCommonCount = attributeValueCounts.get(0);
        Object mostCommon = attributeValues.get(0);

        for (int j = 1; j < distinctValueSize; j++) {
            if (attributeValueCounts.get(j) > mostCommonCount) {
                mostCommonCount = attributeValueCounts.get(j);
                mostCommon = attributeValues.get(j);

            }
        }

        return mostCommon;
    }

    @Override
    public List<IAttributeDataset<T>> partition(String onAttribute) {

        int x = this.datumList.size();
        LinkedList<IAttributeDataset<T>> outputList = new LinkedList<>();

        for (int i = 0; i < x; i++) {
            T currentDatum = this.datumList.get(i);
            int outputSize = outputList.size();
            boolean alreadyAdded = false;
            for (int j = 0; j < outputSize; j++) {
                Object currentSetValue = outputList.get(j).getDataObjects().get(0).getValueOf(onAttribute);
                if (currentSetValue.equals(currentDatum.getValueOf(onAttribute))) {
                    outputList.get(j).getDataObjects().add(currentDatum);
                    alreadyAdded = true;
                    break;
                }
            }
            if (!alreadyAdded) {
                LinkedList<T> newObjectList = new LinkedList<T>();
                newObjectList.addLast(this.datumList.get(i));
                List<String> attributeCopy = new LinkedList<>(this.attributes);
                attributeCopy.remove(onAttribute);
                DataTable<T> newSet = new DataTable<>(attributeCopy, newObjectList);
                outputList.addLast(newSet);
            }
        }

        return outputList;
    }

}


