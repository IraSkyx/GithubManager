package converters;

import javafx.util.StringConverter;

/**
 *
 * @author Adrien
 */
public class PropertyStringConverter extends StringConverter {

    @Override
    public String toString(Object object) {
        return "Welcome\n" + object.toString();
    }

    @Override
    public Object fromString(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}