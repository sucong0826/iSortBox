package sortbox.pcs.convert;

/**
 * Converter interface provides a pcs function that allow you to convert a set of element to another set of element with different type.
 * <p>
 * Created by Clever.Su on 9/14/2017.
 */
interface Converter<F, T> {

    /**
     * Convert F to T.
     *
     * @param from object of F named from
     * @return t means to the converting result
     */
    T convert(F from);
}
