package sortbox.sort.cmp;

import sortbox.pcs.entity.SortItem;
import sortbox.pcs.entity.SortKey;

/**
 * Comparator based on Number.
 * <p>
 * Created by Clever.Su on 9/20/2017.
 */
public class NumberOrderComparator<T> extends AbstractComparator<T> {

    private boolean mAsc;

    public NumberOrderComparator(boolean asc) {
        super();
        this.mAsc = asc;
    }

    @Override
    public int compare(SortItem<T> o1, SortItem<T> o2) {

        Number n1 = 0, n2 = 0;

        for (SortKey sk : o1.getSortKeyList()) {
            if (super.getKeyName().equals(sk.getKeyName())) {
                n1 = (Number) sk.getKeyVal();
                break;
            }
        }

        for (SortKey sk : o2.getSortKeyList()) {
            if (super.getKeyName().equals(sk.getKeyName())) {
                n2 = (Number) sk.getKeyVal();
                break;
            }
        }

        return (int) (mAsc ? (n1.doubleValue() - n2.doubleValue()) : (n2.doubleValue() - n1.doubleValue()));
    }
}
