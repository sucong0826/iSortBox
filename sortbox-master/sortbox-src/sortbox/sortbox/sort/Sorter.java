package sortbox.sort;

import sortbox.common.config.SortConfig;
import sortbox.pcs.entity.SortItem;
import sortbox.pcs.entity.TwoTuple;
import sortbox.sort.cmp.AbstractComparator;
import sortbox.sort.cmp.DefaultComparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Where sorting happens.
 * What Sorter does is sorting a list of {@link sortbox.pcs.entity.SortItem}.
 * <p>
 * Created by Clever.Su on 9/18/2017.
 */
public final class Sorter<T> {
    private List<SortItem<T>> unSortedList;
    private Map<TwoTuple<Integer, String>, AbstractComparator<T>> sortConfigMap;
    private Map<TwoTuple<Integer, String>, List<T>> sortedResMap;
    private boolean useDefault;

    public Sorter(List<SortItem<T>> input, SortConfig<T> sortConfig) {
        unSortedList = input;
        sortConfigMap = sortConfig.getSortConfigMap();
        useDefault = sortConfig.isDefCmp();
    }

    private void sort(List<SortItem<T>> sortItemList, AbstractComparator<T> comparator) {
        sortItemList.sort(comparator);
    }

    public void sort() {
        for (Map.Entry<TwoTuple<Integer, String>, AbstractComparator<T>> entry : sortConfigMap.entrySet()) {

            AbstractComparator<T> comparator = null;
            if (entry.getValue() == null) {
                if (useDefault)
                    comparator = new DefaultComparator<>();
            } else {
                comparator = entry.getValue();
            }
            sort(unSortedList, comparator);

            if (sortedResMap == null) {
                sortedResMap = new HashMap<>();
            }

            List<T> originList = new ArrayList<>(unSortedList.size());
            originList.addAll(unSortedList.stream().map(SortItem::getOrigin).collect(Collectors.toList()));
            sortedResMap.put(entry.getKey(), originList);
        }
    }

    public Map<TwoTuple<Integer, String>, List<T>> getSortedResMap() {
        return this.sortedResMap;
    }
}
