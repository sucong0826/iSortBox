package sortbox.common;

import sortbox.pcs.entity.CombineMode;
import sortbox.pcs.entity.TwoTuple;

import java.util.List;
import java.util.Map;

/**
 * Created by Clever.Su on 9/20/2017.
 */
public class SortedResult<T> {
    private Map<TwoTuple<Integer, String>, List<T>> multipleResultSortedMap;
    private CombineMode combineMode = CombineMode.SINGLE;

    public SortedResult() {

    }

    public Map<TwoTuple<Integer, String>, List<T>> getMultipleResultSortedMap() {
        return multipleResultSortedMap;
    }

    public void setMultipleResultSortedMap(Map<TwoTuple<Integer, String>, List<T>> multipleResultSortedMap) {
        this.multipleResultSortedMap = multipleResultSortedMap;
    }

    public CombineMode getCombineMode() {
        return combineMode;
    }

    public void setCombineMode(CombineMode combineMode) {
        this.combineMode = combineMode;
    }


    @Override
    public String toString() {
        return "SortedResult{" +
                "multipleResultSortedMap=" + multipleResultSortedMap +
                ", combineMode=" + combineMode +
                '}';
    }
}
