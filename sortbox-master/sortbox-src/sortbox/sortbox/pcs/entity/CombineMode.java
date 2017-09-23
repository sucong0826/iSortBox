package sortbox.pcs.entity;

/**
 * CombineMode decides one thing: how many properties you specify need to be converted to {@link SortKey}.
 * <p>
 * Created by Clever.Su on 9/13/2017.
 */
public enum CombineMode {
    /**
     * If choose SINGLE, you are able to specify several properties but there is only one which holds highest priority will be the sort key.
     * The others are disabled.
     */
    SINGLE,

    /**
     * If choose MULTIPLE, framework will sort the list by properties with the orders, that is, the priorities from the highest to the lowest.
     * As result, several lists will be created, as for the number, it depends on how many priorities you give.
     */
    MULTIPLE
}
