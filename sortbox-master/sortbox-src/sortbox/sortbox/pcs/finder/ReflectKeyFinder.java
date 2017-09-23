package sortbox.pcs.finder;


import sortbox.common.utils.Util;
import sortbox.pcs.CloneablePriorityQueue;
import sortbox.pcs.entity.SortKey;
import sortbox.pcs.entity.TwoTuple;
import sortbox.common.process.Process;

import java.lang.reflect.Field;
import java.util.*;

/**
 * ReflectKeyFinder implements {@link KeyFinder}, as its name, it is used to implement mechanism of reflection.
 * Reflection is a kind of RTTI. I regard it as a way of sorting a collection.
 * <p>
 * Created by Clever.Su on 9/13/2017.
 */
public class ReflectKeyFinder<T> implements KeyFinder<T, String, Object>, Process<List<TwoTuple<T, Queue<SortKey>>>> {

    /**
     * the source data list as input.
     */
    private List<T> input;

    /**
     * sort keys which are used to be the tag of sorting a list and hold them in a queue.
     */
    private Queue<SortKey> sortKeyQueue;

    /**
     * this map holds some key-value pairs.
     * The key is the type of element in List<T>.
     * The value is the Queue which contains a set of {@link SortKey}s.
     * <p>
     * It is useful to record and maintain the relation between T and Queue.
     * Follow the design, T and Queue<SortKey> will be encapsulated into a {@link sortbox.pcs.entity.SortItem} finally.
     * Thus, keep all of them in a map is important obviously.
     */
    private Map<T, Queue<SortKey>> tKeyMap;

    /**
     * FindMode indicates the ReflectKeyFinder how to find sort keys.
     * Default is FindMode.ALL.
     */
    private KeyFinder.FindMode findMode = FindMode.ALL;

    /**
     * The constructor limits a list of T and a queue of SortKey must be passed in.
     *
     * @param input        a list of T
     * @param sortKeyQueue a queue of sort key
     */
    public ReflectKeyFinder(List<T> input, Queue<SortKey> sortKeyQueue) {
        this.input = Util.isEmptyCollection(input) ? Collections.emptyList() : input;
        this.sortKeyQueue = sortKeyQueue;

        try {
            prepareContainer();
        } catch (CloneNotSupportedException e) {
            // because I want to Queue can be cloneable, thus, this exception will be thrown.
            e.printStackTrace();
        }
    }

    /**
     * Create a map to hold element of T and a queue of SortKey.
     *
     * @throws CloneNotSupportedException the exception throws from {@link CloneablePriorityQueue}.
     */
    private void prepareContainer() throws CloneNotSupportedException {
        // create a container.
        tKeyMap = new HashMap<>();

        // create a clone helper to clone queue
        CloneablePriorityQueue<SortKey> cloneableQ = new CloneablePriorityQueue<>(this.sortKeyQueue);

        // create each entry in map
        for (T t : input) {
            tKeyMap.put(t, cloneableQ.clone());
        }
    }

    @Override
    public Object find(T target, String key) {
        // null-checking
        if (target == null)
            return null;

        /*
            use reflect to match keys.
         */
        Field[] dlFields = target.getClass().getDeclaredFields();
        Object val = null;

        for (Field f : dlFields) {
            if (key.equals(f.getName())) {
                try {
                    if (!f.isAccessible())
                        f.setAccessible(true);

                    val = f.get(target);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return val;
    }

    @Override
    public List<TwoTuple<T, Queue<SortKey>>> findKeys() {
        return findKeysForAll();
    }

    /**
     * Find keys from T by iterating sort keys pointed out and the result is an instance of TwoTuple.
     *
     * @param t            target object that will be searched
     * @param sortKeyQueue a queue of sort keys
     * @return a two tuple instance that holds the objects of T and queue.
     */
    private TwoTuple<T, Queue<SortKey>> findKeys(T t, Queue<SortKey> sortKeyQueue) {
        if (Util.isEmptyCollection(input))
            return new TwoTuple<>(null, null);

        if (tKeyMap == null) tKeyMap = new HashMap<>();

        for (SortKey next : sortKeyQueue) {
            next.setKeyVal(find(t, next.getKeyName()));
        }

        return new TwoTuple<>(t, sortKeyQueue);
    }

    /**
     * The function executes an action of finding all keys and returning a list of {@link sortbox.pcs.entity.SortItem}.
     *
     * @return a list of SortItem.
     */
    private List<TwoTuple<T, Queue<SortKey>>> findKeysForAll() {
        List<TwoTuple<T, Queue<SortKey>>> output = new ArrayList<>();

        for (Map.Entry<T, Queue<SortKey>> entry : tKeyMap.entrySet()) {
            TwoTuple<T, Queue<SortKey>> tuple = findKeys(entry.getKey(), entry.getValue());
            output.add(tuple);
        }

        return output;
    }

    /**
     * filter the tuple list. The function is aimed at removing null objects.
     *
     * @param tuple tuple list wraps the a T and a queue of SortKeys.
     */
    private void filter(TwoTuple<T, Queue<SortKey>> tuple) {

        T element = tuple.extractFirst();
        Queue<SortKey> sortKeys = tuple.extractSecond();

        switch (this.findMode) {
            case SINGLE:
                SortKey highestPrioritySortKey = sortKeyQueue.peek();
                sortKeys = new PriorityQueue<>();
                sortKeys.add(highestPrioritySortKey);
                break;

            case ALL:
                if (element != null) {
                    sortKeys = new PriorityQueue<>(sortKeys.size());
                    try {
                        Field[] fields = element.getClass().getDeclaredFields();
                        for (int i = 0; i < fields.length; i++) {
                            @SuppressWarnings("UnnecessaryLocalVariable")
                            int idx = i;
                            int priority = idx + 1;
                            String keyName = fields[i].getName();
                            Object keyVal = fields[i].get(element);

                            SortKey sk = new SortKey(priority, keyName);
                            sk.setKeyVal(keyVal);

                            sortKeys.add(sk);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                } else {
                    sortKeys = new PriorityQueue<>();
                }
                break;

            case RANDOM_PRIORITY:
            default:
                // nothing to do
                break;
        }

        /*
            update tuple
         */
        tuple.updateF(element);
        tuple.updateS(sortKeys);
    }

    @Override
    public void process(List<TwoTuple<T, Queue<SortKey>>> target, Object... args) {
        if (Util.isEmptyCollection(target))
            return;

        for (TwoTuple<T, Queue<SortKey>> tuple : target) {
            filter(tuple);
        }
    }
}
