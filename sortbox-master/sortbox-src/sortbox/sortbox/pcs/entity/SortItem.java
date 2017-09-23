package sortbox.pcs.entity;

import sortbox.common.utils.Util;

import java.util.Collection;

/**
 * SortItem collects a list of {@link SortKey}s and keeps an origin of generic type T.
 * This class plays an important role when sort up a list by it.
 * <p>
 * Created by Clever.Su on 9/13/2017.
 */
public class SortItem<T> {

    /**
     * a list of {@link SortKey}.
     */
    private Collection<SortKey> sortKeyList;

    /**
     * the origin data.
     * it depends on the type of actual T type.
     */
    private T origin;


    public SortItem() {
        /* nothing... */
    }

    public Collection<SortKey> getSortKeyList() {
        return sortKeyList;
    }

    public void setSortKeyList(Collection<SortKey> sortKeys) {
        this.sortKeyList = sortKeys;
    }

    public T getOrigin() {
        return origin;
    }

    public void setOrigin(T origin) {
        this.origin = origin;
    }

    /**
     * Checked whether the sort item can be put into sortable list.
     *
     * @return true it can be sorted otherwise false.
     */
    public boolean isSortSupport() {
        if (Util.isEmptyCollection(sortKeyList))
            return false;

        boolean isSortSupport = true;
        for (SortKey sk : this.sortKeyList) {
            if (sk.sortableKey()) {
                break;
            }

            isSortSupport = false;
        }

        return isSortSupport;
    }

    @Override
    public String toString() {
        return "SortItem{" +
                "sortKeyList=" + sortKeyList +
                ", origin=" + origin +
                '}';
    }
}
