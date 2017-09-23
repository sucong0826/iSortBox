package sortbox.common.utils;

import java.util.Collection;

/**
 * Some common.utils to help programmers save time.
 * <p>
 * Created by Clever.Su on 9/12/2017.
 */
public final class Util {

    /**
     * Check a collection is empty or not.
     *
     * @param collection an object of collection
     * @return true an empty collection otherwise false.
     */
    public static boolean isEmptyCollection(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean requireNonNull(Object o) {
        return o != null;
    }

    public static boolean isEmptyText(CharSequence charSequence) {
        return charSequence == null || charSequence.toString().isEmpty();
    }
}
