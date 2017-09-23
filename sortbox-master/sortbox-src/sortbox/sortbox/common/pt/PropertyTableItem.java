package sortbox.common.pt;

/**
 * An instance of this class is an entry of a {@link sortbox.common.pt.PropertyTable}.
 * <p>
 * Created by Clever.Su on 9/21/2017.
 */
public class PropertyTableItem {

    /**
     * name of a property;
     */
    private String propName;

    /**
     * type info of a property;
     */
    private Class<?> propType;

    /**
     * value of a property;
     */
    private Object propValue;

    public PropertyTableItem(String propName) {
        this.propName = propName;
    }

    public String getPropName() {
        return propName;
    }

    public Class<?> getPropType() {
        return propType;
    }

    public void setPropType(Class<?> propType) {
        this.propType = propType;
    }

    public Object getPropValue() {
        return propValue;
    }

    public void setPropValue(Object propValue) {
        this.propValue = propValue;
    }

    @Override
    public String toString() {
        return "PropertyTableItem{" +
                "propName='" + propName + '\'' +
                ", propType=" + propType +
                ", propValue=" + propValue +
                '}';
    }
}
