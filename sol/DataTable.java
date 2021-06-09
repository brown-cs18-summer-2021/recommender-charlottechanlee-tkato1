package sol;

import src.IAttributeDataset;
import src.IAttributeDatum;

import java.util.List;

/**
 * Class for a table (collection of rows) of data.
 * @param <T> the type of the data objects
 */
public class DataTable<T extends IAttributeDatum> implements IAttributeDataset<T> {

    // TODO: add fields

    public DataTable(List<String> attributes, List<T> dataObjects) {
        // TODO: implement
    }

    @Override
    public List<String> getAttributes() {
        // TODO: implement
        return null;
    }

    @Override
    public List<T> getDataObjects() {
        // TODO: implement
        return null;
    }

    @Override
    public int size() {
        // TODO: implement
        return 0;
    }

    @Override
    public boolean allSameValue(String ofAttribute) {
        // TODO: implement
        return false;
    }

    @Override
    public Object getSharedValue(String ofAttribute) {
        // TODO: implement
        return null;
    }

    @Override
    public Object mostCommonValue(String ofAttribute) {
        // TODO: implement
        return null;
    }

    @Override
    public List<IAttributeDataset<T>> partition(String onAttribute) {
        // TODO: implement
        return null;
    }
}
