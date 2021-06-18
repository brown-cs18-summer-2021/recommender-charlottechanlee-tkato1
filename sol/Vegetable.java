package sol;

import src.IAttributeDatum;

/**
 * class representing a Vegetable which implements IAttributeDatum
 */
public class Vegetable implements IAttributeDatum {
    /**
     * string representing the color of the vegetable
     */
    String color;
    /**
     * boolean of whether the vegetable is low carbohydrate
     */
    boolean lowCarb;
    /**
     * boolean of whether the vegetable is high in fiber
     */
    boolean highFiber;
    /**
     * boolean of weather the vegetable is liked
     */
    boolean likeToEat;

    /**
     *
     * @param color - color of vegetable as a string
     * @param lowCarb - whether the vegetable is low carbohydrate in boolean
     * @param highFiber - whether the vegetable is high in fiber in boolean
     * @param likeToEat - whether the vegetable is liked in boolean
     */
    public Vegetable(String color, boolean lowCarb, boolean highFiber, boolean likeToEat) {

        this.color = color;

        this.lowCarb = lowCarb;

        this.highFiber = highFiber;

        this.likeToEat = likeToEat;
    }

    @Override
    public Object getValueOf(String attributeName) {
        if(attributeName.equals("color")) {
            return this.color;
        } else if (attributeName.equals("lowCarb")) {
            return this.lowCarb;
        } else if (attributeName.equals("highFiber")) {
            return this.highFiber;
        } else if (attributeName.equals("likeToEat")) {
            return this.likeToEat;
        } else {
            throw new RuntimeException("vegetable object does not have " + attributeName + " attribute");
        }
    }

}
