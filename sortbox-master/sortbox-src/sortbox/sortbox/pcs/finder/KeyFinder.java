package sortbox.pcs.finder;


import sortbox.pcs.entity.SortKey;
import sortbox.pcs.entity.TwoTuple;

import java.util.List;
import java.util.Queue;

/**
 * KeyFinder is the pcs interface for you to customized randomly according to demands.
 * Here provides one function named find, the giving arguments of target and key, use them to get a result of type V.
 * <p>
 * Created by Clever.Su on 9/13/2017.
 */
public interface KeyFinder<T, K, V> {

    /**
     * Find V in target of T by key of K.
     *
     * @param target which is going to be searched
     * @param key    how to search the target, depends on key
     * @return a result with type of V
     */
    V find(T target, K key);

    /**
     * find all keys.
     *
     * @return a list of {@link TwoTuple} object which holds a T of target and a queue of {@link SortKey}.
     */
    List<TwoTuple<T, Queue<SortKey>>> findKeys();

    /**
     * FindMode decides that the classes which implement the {@link KeyFinder} how to find out sort keys.
     */
    enum FindMode {
        /**
         * No matter how many sort keys you specify, KeyFinder framework only find out one or nothing whose priority is the highest.
         */
        SINGLE,

        /**
         * Default.
         * If you don't give FindMode or specify ALL, KeyFinder framework will iterate all fields in target class.
         * and the priorities follow the natural code order, that is, those properties flow from top to down.
         */
        ALL,

        /**
         * Follow the priorities that you give.
         */
        RANDOM_PRIORITY
    }
}
