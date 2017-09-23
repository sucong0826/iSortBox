package sortbox.common.pt;

import sortbox.common.utils.Util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PropertyTable holds the properties information of `T`.
 * The `PropertyTable` records the name of a property, the type of a property, and the value of a property.
 * The table is used to be searched for developers and provided to show at some UI interface for being selected by users.
 * <p>
 * Created by Clever.Su on 9/21/2017.
 */
public class PropertyTable<T> {

    private List<PropertyTableItem> itemList = new ArrayList<>();

    /**
     * check and generate a PropertyTable with input.
     * `input` can't be null;
     *
     * @param input input object
     * @return an instance of PropertyTable including properties values .
     */
    public PropertyTable<T> check(T input) {
        Objects.requireNonNull(input);

        Field[] declaredFields = input.getClass().getDeclaredFields();

        try {
            for (Field f : declaredFields) {

                if (!f.isAccessible())
                    f.setAccessible(true);

                PropertyTableItem item = new PropertyTableItem(f.getName());
                item.setPropType(f.getType());
                item.setPropValue(f.get(input));

                itemList.add(item);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return this;
    }

    /**
     * If you just want to check property name and type. call this method.
     *
     * @param inputClass the class object of T
     * @return an instance of PropertyTable without properties values .
     */
    public PropertyTable<T> check(Class<T> inputClass) {
        Objects.requireNonNull(inputClass);

        Field[] declaredFields = inputClass.getDeclaredFields();

        for (Field f : declaredFields) {

            if (!f.isAccessible())
                f.setAccessible(true);

            PropertyTableItem item = new PropertyTableItem(f.getName());
            item.setPropType(f.getType());

            itemList.add(item);
        }

        return this;
    }

    public List<PropertyTableItem> getPropertyList() {
        return this.itemList;
    }

    /**
     * Check whether has the PropertyTableItem by judging the name.
     *
     * @param name property name
     * @return a table item in PropertyTable
     */
    public PropertyTableItem shot(String name) {
        if (Util.isEmptyText(name))
            return null;

        for (PropertyTableItem item : itemList) {
            if (name.equals(item.getPropName())) {
                return item;
            }
        }

        return null;
    }
}
