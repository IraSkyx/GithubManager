/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

/**
 *
 * @author adlenoir
 */
public class NullToBooleanConverter extends Format{

    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {        
        return obj == null ? new StringBuffer("NULL") : new StringBuffer("NOTNULL");
    }

    @Override
    public Object parseObject(String source, ParsePosition pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
