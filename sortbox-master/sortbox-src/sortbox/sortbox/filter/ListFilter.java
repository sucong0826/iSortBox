package sortbox.filter;


import sortbox.common.process.Process;

import java.util.List;

/**
 * It is an abstract class to let you extends it which plays the role of filtering a list.
 * <p>
 * Created by Clever.Su on 9/21/2017.
 */
public abstract class ListFilter<T> implements Filter<List<T>>, Process<List<?>> {

    @Override
    public void filter(List<T> input) {
        process(input);
    }

    @Override
    public void process(List<?> list, Object... args) {
        removeEmpty(list);
    }
}
