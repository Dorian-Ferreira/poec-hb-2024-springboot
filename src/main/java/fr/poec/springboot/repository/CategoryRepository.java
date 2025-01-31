package fr.poec.springboot.repository;

import fr.poec.springboot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, NameRepository<Category> {
    Optional<Category> findBySlug(String field);
}
