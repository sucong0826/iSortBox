package sortbox.sort.cmp;

import sortbox.pcs.entity.SortItem;
import sortbox.pcs.entity.SortKey;

import java.util.Arrays;

/**
 * Comparator based on name. Compare two names by alphabet.
 * <p>
 * Created by Clever.Su on 9/18/2017.
 */
public class NameComparator<T> extends AbstractComparator<T> {

    public NameComparator() {
        super();
    }

    @Override
    public int compare(SortItem<T> o1, SortItem<T> o2) {

        String nf = "";
        String nl = "";

        for (SortKey sk1 : o1.getSortKeyList()) {
            if (super.getKeyName().equals(sk1.getKeyName())) {
                if (sk1.getKeyVal() instanceof String)
                    nf = (String) sk1.getKeyVal();
            }
        }

        for (SortKey sk2 : o2.getSortKeyList()) {
            if (super.getKeyName().equals(sk2.getKeyName())) {
                if (sk2.getKeyVal() instanceof String)
                    nl = (String) sk2.getKeyVal();
            }
        }


        char[] nfArray = nf.toCharArray();
        char[] nlArray = nl.toCharArray();

        if (nfArray.length != nlArray.length) {
            int endIdx = Math.min(nfArray.length, nlArray.length);
            nfArray = Arrays.copyOfRange(nfArray, 0, endIdx);
            nlArray = Arrays.copyOfRange(nlArray, 0, endIdx);
        }

        int result = 0;
        for (int i = 0; i < nfArray.length; i++) {
            result = nfArray[i] - nlArray[i];

            if (result != 0)
                break;
        }
        return result;
    }
}
