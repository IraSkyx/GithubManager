package business_logic.repository;

import java.util.function.Predicate;

/**
 *
 * @author Adrien
 * @param <T>
 */
@FunctionalInterface
public interface CompositeUtils<T> {   
    public boolean contains(Predicate<? super T> action);
}
