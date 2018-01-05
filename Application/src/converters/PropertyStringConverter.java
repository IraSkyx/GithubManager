package converters;

import javafx.util.StringConverter;

/**
 * Converter : Object to String and String to Object
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class PropertyStringConverter extends StringConverter {

    /**
     * Object to String converter
     * 
     * @param object Object to convert into String
     * @return a String 
     */
    @Override
    public String toString(Object object) {
        return "Welcome\n" + object.toString();
    }

    /**
     * String to Object converter : not implemented
     * 
     * @param string String to convert into Object
     * @return an Object
     */
    @Override
    public Object fromString(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }   
}
