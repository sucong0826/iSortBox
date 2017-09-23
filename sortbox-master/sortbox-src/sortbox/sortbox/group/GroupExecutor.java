package sortbox.group;

import sortbox.common.utils.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * GroupExecutor is a class to execute group conditions.l
 * <p>
 * Created by Clever.Su on 9/20/2017.
 */
public final class GroupExecutor {

    /**
     * Create a new instance of GroupExecutor.
     *
     * @return an instance of GroupExecutor.
     */
    public static GroupExecutor newInstance() {
        return new GroupExecutor();
    }

    private GroupExecutor() {
        // nothing ...
    }

    /**
     * Execute a list of group condition.
     *
     * @param groupConditionList a list of group condition.
     * @param <T>                object type in list
     * @return a list of {@link Group}
     */
    public <T> List<Group<T>> execute(List<GroupCondition<T>> groupConditionList) {
        if (Util.isEmptyCollection(groupConditionList))
            return Collections.emptyList();

        List<Group<T>> groups = new ArrayList<>();

        for (GroupCondition<T> gc : groupConditionList) {
            Group<T> group = gc.execute();
            groups.add(group);
        }

        return groups;
    }

    /**
     * Execute a single group condition.
     *
     * @param condition a group condition.
     * @param <T>       object type in list
     * @return a  {@link Group}
     */
    public <T> Group<T> executeSingle(GroupCondition<T> condition) {
        if (condition == null)
            return null;

        return condition.execute();
    }
}
