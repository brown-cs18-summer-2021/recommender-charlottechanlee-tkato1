package src;

import java.util.List;

/**
 * Interface for a dataset (a collection of data) of a certain type.
 * @param <T> the type of data
 */
public interface IAttributeDataset<T extends IAttributeDatum> {

    /**
     * Gets the attributes of the dataset.
     * @return the list of attributes
     */
    public List<String> getAttributes();

    /**
     * Gets the data of the dataset.
     * @return the list of data objects
     */
    public List<T> getDataObjects();

    /**
     * Gets the size of the dataset.
     * @return the number of data/rows in the dataset
     */
    public int size();

    /**
     * Determines whether every datum in the dataset has the same value of a
     * given attribute.
     * @param ofAttribute the given attribute
     * @return true if all data in the dataset have the same value for
     * ofAttribute,false otherwise
     */
    public boolean allSameValue(String ofAttribute);

    /**
     * Gets the shared value of a given attribute, assuming all data in the
     * dataset have the same value
     * of that attribute.
     * @param ofAttribute the given attribute
     * @return the common value for the given attribute
     */
    public Object getSharedValue(String ofAttribute);

    /**
     * Gets the most common value of a given attribute across the dataset.
     * @param ofAttribute the given attribute
     * @return the most common value for an attribute across every datum
     * in the dataset
     */
    public Object mostCommonValue(String ofAttribute);

    /**
     * Partitions the dataset into subsets such that each subset contains
     * only data/rows with the same values
     * of the given attribute.
     * @param onAttribute the attribute on which to split the dataset
     * @return new datasets, where each dataset contains only data with the
     * same value of onAttribute
     */
    public List<IAttributeDataset<T>> partition(String onAttribute);
}
