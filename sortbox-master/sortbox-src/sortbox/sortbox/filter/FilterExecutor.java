package sortbox.filter;

/**
 * The executor class for filters.
 * <p>
 * Created by Clever.Su on 9/21/2017.
 */
public final class FilterExecutor {

    /**
     * Create an instance of FilterExecutor by this static method.
     *
     * @return an instance of FilterExecutor
     */
    public static FilterExecutor newInstance() {
        return new FilterExecutor();
    }

    private FilterExecutor() {
        // nothing...
    }

    /**
     * Execute the filter.
     *
     * @param target the object which is going to be filtered
     * @param filter a filter
     * @param <T>    generic type of target
     */
    public <T> void execute(T target, Filter<T> filter) {
        filter.filter(target);
    }
}
