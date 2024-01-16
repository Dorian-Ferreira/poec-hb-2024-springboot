package fr.poec.springboot.instant_faking.repository;

import fr.poec.springboot.instant_faking.entity.Category;
import fr.poec.springboot.instant_faking.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository
        extends JpaRepository<Category, Long>,
                EntityNameRepository<Category>
{

    Optional<Category> findBySlug(String slug);

}