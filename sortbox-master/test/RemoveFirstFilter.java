package sortbox.test;

import sortbox.filter.ListFilter;

import java.util.List;

/**
 * Test.
 * It is a filter to remove the first element.
 * <p>
 * Created by Clever.Su on 9/21/2017.
 */
public class RemoveFirstFilter<T> extends ListFilter<T> {

    @Override
    public void filter(List<T> ts) {
        super.filter(ts);
        ts.remove(0);
    }

    @Override
    public boolean test(List<T> ts) {
        return false;
    }
}
