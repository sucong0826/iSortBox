package sortbox.group;

import java.util.List;
import java.util.function.Predicate;

/**
 * GroupCondition implements {@link Predicate} which means that an instance of GroupCondition is a condition to separate a list to different {@link Group}s.
 * You cannot use it directly, if you want, extends it.
 * <p>
 * NOTE THAT:
 * please note that {@link Predicate} has the limitation of Java platform version which must be higher that 1.8 which supports Lambda expression and functions.
 * <p>
 * Created by Clever.Su on 9/20/2017.
 */
public abstract class GroupCondition<T> implements Predicate<T> {

    /**
     * There must be a list of T as input to be extracted into different groups.
     */
    private List<T> input;

    /**
     * A condition is executed and a group must be generated.
     * This field just holds the information after being grouped.
     */
    private Group<T> mGroup;

    /**
     * Default Constructor but can not call it to get new instance.
     * Pass in a list of T as target list to be grouped and initialized the Group object.
     *
     * @param input a list of T to be grouped
     */
    public GroupCondition(List<T> input, Object key) {
        this.input = input;
        mGroup = new Group<>();
        mGroup.setGroupKey(key == null ? "Unknown" : key);
    }

    @Override
    public String toString() {
        return "GroupCondition{" +
                "input=" + input +
                ", mGroup=" + mGroup +
                '}';
    }

    /**
     * If shot one, how to handle it.
     *
     * @param t shot one
     */
    public void shot(T t) {
        mGroup.addShot(t);
    }

    protected Group<T> getGroup() {
        return this.mGroup;
    }

    /**
     * Execute the condition
     *
     * @return a group
     */
    public abstract Group<T> execute();
}
