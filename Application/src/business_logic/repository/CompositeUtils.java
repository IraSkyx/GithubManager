package business_logic.repository;

import java.util.function.Predicate;

/**
 *
 * @author Adrien LENOIR and Fabien SIMONET
 * @param <T>
 */
@FunctionalInterface
public interface CompositeUtils<T> {   
    public boolean contains(Predicate<? super T> action);
}
