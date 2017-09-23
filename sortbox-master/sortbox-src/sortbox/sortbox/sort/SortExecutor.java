package sortbox.sort;


import sortbox.common.SortedResult;
import sortbox.common.config.SortConfig;
import sortbox.common.process.DefaultProcess;
import sortbox.common.process.Process;
import sortbox.pcs.convert.ReflectionConverter;
import sortbox.pcs.entity.SortItem;
import sortbox.pcs.entity.SortKey;
import sortbox.pcs.entity.TwoTuple;
import sortbox.pcs.finder.KeyFinder;
import sortbox.pcs.finder.ReflectKeyFinder;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;


/**
 * SortExecutor is helper class. It will achieve a set of chain call.
 * Receive an input, pre-process, configure sorting, find sorting keys, convert, these works are going to be called here.
 * <p>
 * Generic type T means that SortExecutor wants to handle a fixed type T, such as class Student.
 * <p>
 * Created by Clever.Su on 9/20/2017.
 */
public final class SortExecutor<T> {

    /**
     * Sort config.
     * Configure it by specifying the property name, priority and comparator.
     * <p>
     * Note that: it can't be null and empty.
     */
    private SortConfig<T> sortConfig;

    /**
     * ReflectConvert is used to convert a list of T to a list of SortItem.
     * Note that there doesn't declare a {@link sortbox.pcs.convert.Converter} interface, because it should be hidden.
     */
    private ReflectionConverter<T> converter;

    /**
     * a disordered input, a list.
     */
    private List<T> seInput;

    // the sort collection of finding sorting keys.
    private List<TwoTuple<T, Queue<SortKey>>> keyFindResult;

    /**
     * The convert result, a list of SortItem.
     */
    private List<SortItem<T>> sortItemList;

    /**
     * Input a disordered list.
     *
     * @param input a disordered list
     * @return the instance of SortExecutor
     */
    public SortExecutor<T> input(List<T> input) {
        this.seInput = input;
        return this;
    }

    /**
     * Setup pre-processing if you need.
     *
     * @param processItf an object which implements the Process interface
     * @return the instance of SortExecutor
     */
    public SortExecutor<T> preProcess(@SuppressWarnings("SameParameterValue") Process<List<T>> processItf) {
        if (processItf == null)
            processItf = new InputListPreProcess();

        processItf.process(seInput);
        return this;
    }

    /**
     * Setup the sorting by passing in an instance of SortConfig.
     * SortConfig can't be null otherwise NullPointerException will be thrown.
     *
     * @param sortConfig the configuration instance of sorting
     * @return the instance of SortExecutor
     */
    public SortExecutor<T> sortConfig(SortConfig<T> sortConfig) {
        // sortConfig can not be null.
        Objects.requireNonNull(sortConfig);

        this.sortConfig = sortConfig;
        return this;
    }

    /**
     * Execute the step of finding sorting keys.
     *
     * @return the instance of SortExecutor
     */
    private SortExecutor<T> findKeys() {
        /*
            key finder is used to find sorting keys.
        */
        KeyFinder<T, String, Object> keyFinder = sortConfig.getKeyFinder();
        if (keyFinder == null) {
            keyFinder = new ReflectKeyFinder<>(seInput, sortConfig.getSortKeyQueue());
        }

        keyFindResult = keyFinder.findKeys();
        return this;
    }

    /**
     * Execute the step of converting a list of original to a list of {@link sortbox.pcs.entity.SortItem}.
     *
     * @return the instance of SortExecutor
     */
    private SortExecutor<T> convert() {
        if (converter == null)
            converter = new ReflectionConverter<>();

        sortItemList = converter.convert(keyFindResult);
        return this;
    }

    /**
     * Execute the step of sorting a list of {@link sortbox.pcs.entity.SortItem}.
     *
     * @return the instance of SortExecutor
     */
    public SortedResult<T> sort() {

        // important steps
        findKeys().convert();

        /*
            create sorter and sort list
         */
        Sorter<T> sorter = new Sorter<>(sortItemList, this.sortConfig);
        sorter.sort();
        Map<TwoTuple<Integer, String>, List<T>> sortedResMap = sorter.getSortedResMap();

        /*
            create result and return it
         */
        SortedResult<T> res = new SortedResult<>();
        res.setCombineMode(sortConfig.getCombineMode());
        res.setMultipleResultSortedMap(sortedResMap);

        return res;
    }

    private class InputListPreProcess extends DefaultProcess<List<T>> {
        @Override
        public void process(List<T> target, Object... args) {
            removeEmpty(target);
        }
    }
}
