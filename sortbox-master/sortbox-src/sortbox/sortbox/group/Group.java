package sortbox.group;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Each instance of Group contains a list of object those having a specific range.
 * <p>
 * Created by Clever.Su on 9/20/2017.
 */
public class Group<T> {

    /**
     * the key as group identifier;
     */
    private Object groupKey;


    /**
     * a list of object that belongs to some range;
     */
    private List<T> groupList;

    public Group() {
        groupList = new ArrayList<>();
    }

    public void setGroupKey(Object groupKey) {
        Objects.requireNonNull(groupKey);
        this.groupKey = groupKey;
    }

    public List<T> getGroupList() {
        return groupList;
    }

    public Object getGroupKey() {
        return this.groupKey;
    }

    /**
     * Add one which shots the range.
     *
     * @param t shot in range
     */
    public void addShot(T t) {
        if (t == null)
            return;

        groupList.add(t);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupKey=" + groupKey +
                ", groupList=" + groupList +
                '}';
    }
}
