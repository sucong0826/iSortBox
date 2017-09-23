package sortbox.filter;

import java.util.function.Predicate;

/**
 * An interface just convert the name from {@link Predicate} to {@link Filter};
 * So you are able to define your own filters by implementing this interface.
 * <p>
 * Created by Clever.Su on 9/21/2017.
 */
public interface Filter<T> extends Predicate<T> {

    /**
     * Filter the input.
     *
     * @param t input
     */
    void filter(T t);
}
