package fr.poec.springboot.instant_faking.repository;

import java.util.Optional;

public interface EntityNameRepository<T> {

    Optional<T> findByName(String name);

}
