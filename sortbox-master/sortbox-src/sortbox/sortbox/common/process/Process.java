package sortbox.common.process;

import sortbox.common.utils.Util;

import java.util.List;
import java.util.ListIterator;

/**
 * Process is an interface that defines what the actions should do when the input list comes.
 * <p>
 * Created by Clever.Su on 9/12/2017.
 */
public interface Process<T> {

    /**
     * When a list comes as input, as developers we must want to common.process it before using it.
     * Because of this intent, preProcess method takes the responsibility to common.process the input list as they need.
     * <p>
     * The method is alternative, that is, you can choose it to be implemented or not.
     * If you want to implement it, you can implement {@link Process} directly, otherwise,
     * you are able to extend {@link DefaultProcess}. It is an abstract class that allow us to customized
     * what should be done before using input list.
     *  @param target which object is going to be processed. This cannot be null
     * @param args   help the underlying function to common.process. Note that args can be any length 0 - n;
     */
    void process(T target, Object... args);

    /**
     * This method is a default one composed in {@link Process}.
     * I just want provide some pcs and common calls for developers where checking nullable and removing nullable is necessary.
     * <p>
     * Whereas it is default, you can choose it randomly, moreover, if you get your own demands, you can define new default calls
     * by extending the interface.
     *
     * @param input a list of target elements.
     */
    default void removeEmpty(List<?> input) {
        if (Util.isEmptyCollection(input)) {
            return;
        }

        ListIterator iterator = input.listIterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if (obj == null)
                iterator.remove();
        }
    }
}
