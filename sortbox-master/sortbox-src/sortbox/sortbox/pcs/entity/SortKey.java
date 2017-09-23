package sortbox.pcs.entity;

import java.util.Objects;

/**
 * Please see the code below:
 * <code>
 * class Student {
 * String name;
 * int age;
 * int id;
 * float height;
 * Score[] scores;
 * }
 * </code>
 * <p>
 * There is a dummy class named Student for presentation.
 * <p>
 * What is SortKey?
 * For example, if we want to sort a list of student objects by their properties of id, name, then age without specific order, converting should be finished.
 * Do what?
 * - priority means that the order
 * - keyName means that the property name such as 'name'/'id'...
 * - keyVal means that the value of a property such as name = "Clever" / id = 1
 * <p>
 * All in all, SortKey describes the pcs sorting information of a property which is ready to be sorted .
 * <p>
 * Created by Clever.Su on 9/13/2017.
 */
public class SortKey implements Comparable<SortKey> {
    /**
     * Plain priority indicates that sort by properties respectively without no priority.
     */
    public static final int PLAIN_PRIORITY = 0;

    /**
     * Sorting priority, that is, the orders.
     * its range from 0 - n means from high priority to low priority
     * <p>
     * 0 is a little special by reading {@link SortKey#PLAIN_PRIORITY} for more details.
     */
    private int priority = PLAIN_PRIORITY;

    /**
     * Name of a property of a specific object.
     * <p>
     * NOTE THAT:
     * keyName CANNOT be null or empty, if any, an Exception will be thrown.
     */
    private String keyName = "";

    /**
     * kayVal is the value of property.
     * It can be null.
     * But please note that, if keyVal is null, it will be sorted at the end of specific sorted list for no value of it, it can't be sorted.
     */
    private Object keyVal;


    /**
     * Def constructor.
     *
     * @param priority see {@link SortKey#priority}
     * @param keyName  see {@link SortKey#keyName}
     */
    public SortKey(int priority, String keyName) {

        // priority arg checking
        if (priority < 0)
            throw new IllegalArgumentException("No negative priorities so that you can pass a negative number in");

        // key name checking
        Objects.requireNonNull(keyName, "KeyName cannot be null");

        this.priority = priority;
        this.keyName = keyName;
    }

    public Object getKeyVal() {
        return keyVal;
    }

    public void setKeyVal(Object keyVal) {
        this.keyVal = keyVal;
    }

    public int getPriority() {
        return priority;
    }

    public String getKeyName() {
        return keyName;
    }

    public boolean sortableKey() {
        return this.keyVal != null;
    }

    @Override
    public String toString() {
        return "SortKey{" +
                "priority=" + priority +
                ", keyName='" + keyName + '\'' +
                ", keyVal=" + keyVal +
                '}';
    }

    @Override
    public int compareTo(SortKey o) {
        return this.priority - o.priority;
    }
}
