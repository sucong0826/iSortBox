package sortbox.common.utils;

import sortbox.pcs.entity.SortItem;
import sortbox.pcs.entity.SortKey;
import sortbox.pcs.entity.TwoTuple;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Clever.Su on 9/18/2017.
 */
public final class SortItemHelper {

    public static SortItemHelper newInstance() {
        return new SortItemHelper();
    }

    private SortItemHelper() {
        // nothing to do.
    }

    public <T> List<Object> getValues(int priority, List<SortItem<T>> sortItemList) {
        List<Object> values = new ArrayList<>();

        for (SortItem<T> sortItem : sortItemList) {
            Collection<SortKey> collection = sortItem.getSortKeyList();
            values.addAll(collection.stream().filter(sk -> sk.sortableKey() && sk.getPriority() == priority).map(SortKey::getKeyVal).collect(Collectors.toList()));
        }

        return values;
    }

    public <T> List<Object> getValues(String keyName, List<SortItem<T>> sortItemList) {
        List<Object> values = new ArrayList<>();

        for (SortItem<T> sortItem : sortItemList) {
            Collection<SortKey> collection = sortItem.getSortKeyList();
            values.addAll(collection.stream().filter(sk -> sk.sortableKey() && sk.getKeyName().equals(keyName)).map(SortKey::getKeyVal).collect(Collectors.toList()));
        }

        return values;
    }

    public <T> Map<Integer, List<Object>> getAllValuesByPriority(List<SortItem<T>> sortItemList) {
        Map<Integer, List<Object>> allValues = new HashMap<>();

        for (SortItem<T> sortItem : sortItemList) {
            Collection<SortKey> collection = sortItem.getSortKeyList();
            for (SortKey sk : collection) {
                int priority = sk.getPriority();
                Object val = sk.getKeyVal();

                if (!allValues.containsKey(priority)) {
                    allValues.put(priority, new ArrayList<>());
                }
                allValues.get(priority).add(val);
            }
        }

        return allValues;
    }

    public <T> Map<String, List<Object>> getAllValuesByKeyName(List<SortItem<T>> sortItemList) {
        Map<String, List<Object>> allValues = new HashMap<>();

        for (SortItem<T> sortItem : sortItemList) {
            Collection<SortKey> collection = sortItem.getSortKeyList();
            for (SortKey sk : collection) {
                String keyName = sk.getKeyName();
                Object val = sk.getKeyVal();

                if (!allValues.containsKey(keyName)) {
                    allValues.put(keyName, new ArrayList<>());
                }
                allValues.get(keyName).add(val);
            }
        }

        return allValues;
    }

    public <T> List<T> getOriginList(int priority, Map<TwoTuple<Integer, String>, List<T>> resMap) {
        for (Map.Entry<TwoTuple<Integer, String>, List<T>> entry : resMap.entrySet()) {
            if (priority == entry.getKey().extractFirst()) {
                return entry.getValue();
            }
        }

        return Collections.emptyList();
    }

    public <T> List<T> getOriginList(String propertyName, Map<TwoTuple<Integer, String>, List<T>> resMap) {
        for (Map.Entry<TwoTuple<Integer, String>, List<T>> entry : resMap.entrySet()) {
            if (propertyName.equals(entry.getKey().extractSecond())) {
                return entry.getValue();
            }
        }

        return Collections.emptyList();
    }
}
