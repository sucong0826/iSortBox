package sortbox.pcs.convert;


import sortbox.common.utils.Util;
import sortbox.pcs.entity.SortItem;
import sortbox.pcs.entity.SortKey;
import sortbox.pcs.entity.TwoTuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/**
 * ReflectionConverter is used to convert a list of Two tuple instances to a list of SortItem instances.
 * It implements {@link Converter} and you can use it easily by creating an instance then calling the convert(args) method.
 * <p>
 * Created by Clever.Su on 9/15/2017.
 */
public class ReflectionConverter<T> implements Converter<List<TwoTuple<T, Queue<SortKey>>>, List<SortItem<T>>> {

    @Override
    public List<SortItem<T>> convert(List<TwoTuple<T, Queue<SortKey>>> from) {

        if (Util.isEmptyCollection(from))
            return Collections.emptyList();

        List<SortItem<T>> sortItems = new ArrayList<>();
        for (TwoTuple<T, Queue<SortKey>> tuple : from) {
            SortItem<T> sortItem = new SortItem<>();
            sortItem.setOrigin(tuple.extractFirst());
            sortItem.setSortKeyList(tuple.extractSecond());
            sortItems.add(sortItem);
        }

        return sortItems;
    }
}
