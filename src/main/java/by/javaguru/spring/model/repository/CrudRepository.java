package by.javaguru.spring.model.repository;

import java.util.Optional;

public interface CrudRepository<K, E> {
    Optional<E> findById(K id);

    E save(E entity);
}
