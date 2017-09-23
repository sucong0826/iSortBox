package sortbox.sort.cmp;

import sortbox.pcs.entity.SortItem;

import java.util.Comparator;

/**
 * AbstractComparator undertakes a task which limits you cannot attach a random comparator implementing {@link Comparator} interface.
 * Framework want you to compare the {@link sortbox.pcs.entity.SortKey} which has a generic type of V, so you just give the actual type of V such
 * as a String type.
 * <p>
 * Created by Clever.Su on 9/18/2017.
 */
public abstract class AbstractComparator<T> implements Comparator<SortItem<T>> {
    // nothing to be composed here
    private String cmpKeyName;

    public AbstractComparator() {
    }

    public void setCmpKeyName(String propertyName) {
        this.cmpKeyName = propertyName;
    }

    public String getKeyName() {
        return this.cmpKeyName;
    }
}
