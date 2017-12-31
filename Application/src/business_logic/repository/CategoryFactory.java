package business_logic.repository;

import java.security.InvalidParameterException;

/**
 *
 * @author Adrien
 */
public class CategoryFactory {
    public static Category create(){return new Category();}
    public static Category create(String name){
        if(name.isEmpty())
            throw new InvalidParameterException("Category name is not set");
        return new Category(name);
    }
}
