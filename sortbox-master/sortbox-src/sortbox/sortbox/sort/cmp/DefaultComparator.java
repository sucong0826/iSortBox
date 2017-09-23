package sortbox.sort.cmp;


import sortbox.pcs.entity.SortItem;

import java.util.Objects;

/**
 * When you attach a null comparator to compare two SortKeys, We may use this default comparator to compare two values.
 * <p>
 * Created by Clever.Su on 9/18/2017.
 */
public class DefaultComparator<V> extends AbstractComparator<V> {

    public DefaultComparator() {
        super();
    }

    @Override
    public int compare(SortItem<V> o1, SortItem<V> o2) {
         /*
            default comparator just cmp the hash code of the two objects.
         */
        return Objects.hashCode(o1) - Objects.hashCode(o2);
    }
}
