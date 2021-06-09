package src;

/**
 * Interface for a datum (a row in a data table), which is a collection of values for a set of attributes.
 */
public interface IAttributeDatum {
    /**
     * Looks up the value of a named attribute.
     * @param attributeName the name of the attribute
     * @return the value of the attribute with the given name
     */
    public Object getValueOf(String attributeName);
}
