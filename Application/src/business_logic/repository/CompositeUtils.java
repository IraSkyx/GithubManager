package business_logic.repository;

import java.util.function.Predicate;

/**
 * Business interface for Repository management : CompositeUtils<T> : Tools to manipulate Repositories
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 * @param <T>
 */
@FunctionalInterface
public interface CompositeUtils<T> {   
    public boolean contains(Predicate<? super T> action);
}
