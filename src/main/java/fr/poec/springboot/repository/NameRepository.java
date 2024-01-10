package fr.poec.springboot.repository;

import fr.poec.springboot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NameRepository<T>{
    Optional<T> findByName(String field);
}
