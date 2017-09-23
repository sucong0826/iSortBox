package sortbox.common.process;

/**
 * This class acts as a bridge.
 * First of all, Process is an abstract concept that everyone is able to define it respectively.
 * Secondly, processing occurs everywhere such as processing before using input list, converting a list to another list with different status.
 * Thirdly, I wish all of actions in processing are alternative.
 * <p>
 * Created by Clever.Su on 9/13/2017.
 */
public abstract class DefaultProcess<T> implements Process<T> {
    /* nothing... */
    // NOTE THAT: you can define new actions or processes before using input list or after using it.
}
