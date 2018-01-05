package business_logic.repository;

import java.util.function.Predicate;

/**
 * Business interface for Repository management : CompositeUtils : Tools to manipulate Repositories
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
@FunctionalInterface
public interface CompositeUtils<T> {   
    /**
     * Evaluate a predicate
     * 
     * @param action a predicate to evaluate
     * @return true of the evalutation is correct, false otherwise
     */
    public boolean contains(Predicate<? super T> action);
}
