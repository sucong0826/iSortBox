package sortbox.common.config;

import sortbox.pcs.entity.CombineMode;
import sortbox.pcs.entity.SortKey;
import sortbox.pcs.entity.TwoTuple;
import sortbox.pcs.finder.KeyFinder;
import sortbox.sort.cmp.AbstractComparator;
import sortbox.sort.cmp.DefaultComparator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Sort Configuration which will provide configurations to be used.
 * <p>
 * Created by Clever.Su on 9/20/2017.
 */
public class SortConfig<T> {

    /**
     * The combine mode.
     * for more details, see {@link sortbox.pcs.entity.CombineMode}.
     * Default is MULTIPLE.
     */
    private CombineMode mCombineMode = CombineMode.MULTIPLE;

    /**
     * it records the relationship between priority(int), propertyName(String), comparator.
     */
    private Map<TwoTuple<Integer, String>, AbstractComparator<T>> sortConfigMap;

    /**
     * The key finder interface.
     */
    private KeyFinder<T, String, Object> keyFinder;

    /**
     * Whether use default comparator.
     * true use otherwise false.
     */
    private boolean defCmp;

    public SortConfig() {
        // nothing...
    }

    private SortConfig(Builder builder) {
        mCombineMode = builder.combineMode;
        this.sortConfigMap = builder.sortConfigMap;
        this.keyFinder = builder.keyFinder;
        this.defCmp = builder.useDefaultComparator;
    }

    public KeyFinder<T, String, Object> getKeyFinder() {
        return this.keyFinder;
    }

    // Builder pattern
    public class Builder {
        private CombineMode combineMode;
        private KeyFinder<T, String, Object> keyFinder;
        private Map<TwoTuple<Integer, String>, AbstractComparator<T>> sortConfigMap;
        private boolean useDefaultComparator;

        public Builder set(int priority, String propertyName, AbstractComparator<T> comparator) {

            TwoTuple<Integer, String> ppTuple = new TwoTuple<>(priority, propertyName);

            if (comparator == null) {
                comparator = new DefaultComparator<>();
            }

            comparator.setCmpKeyName(propertyName);

            if (sortConfigMap == null)
                sortConfigMap = new TreeMap<>();

            if (sortConfigMap.containsKey(ppTuple)) {
                sortConfigMap.replace(ppTuple, comparator);
            } else {
                sortConfigMap.put(ppTuple, comparator);
            }

            return this;
        }

        public Builder combine(CombineMode mode) {
            combineMode = mode;
            return this;
        }

        @SuppressWarnings("UnusedDeclaration")
        public Builder keyFinder(KeyFinder<T, String, Object> keyFinder) {
            this.keyFinder = keyFinder;
            return this;
        }

        public Builder useDefaultComparator(boolean use) {
            useDefaultComparator = use;
            return this;
        }

        public SortConfig<T> build() {

            // before building up a SortConfig, if combine mode is SINGLE, just keep the highest priority property.
            if (CombineMode.SINGLE == combineMode) {
                sortConfigMap = Collections.singletonMap(sortConfigMap.keySet().iterator().next(), sortConfigMap.values().iterator().next());
            }

            return new SortConfig<>(this);
        }
    }

    public Queue<SortKey> getSortKeyQueue() {
        if (sortConfigMap == null || sortConfigMap.isEmpty())
            return new PriorityQueue<>(Collections.emptyList());

        Queue<SortKey> pQueue = new PriorityQueue<>(sortConfigMap.keySet().size());
        pQueue.addAll(sortConfigMap.keySet().stream().map(tuple -> new SortKey(tuple.extractFirst(), tuple.extractSecond())).collect(Collectors.toList()));
        return pQueue;
    }

    public Map<TwoTuple<Integer, String>, AbstractComparator<T>> getSortConfigMap() {
        return this.sortConfigMap;
    }

    public CombineMode getCombineMode() {
        return this.mCombineMode;
    }

    public boolean isDefCmp() {
        return defCmp;
    }
}
