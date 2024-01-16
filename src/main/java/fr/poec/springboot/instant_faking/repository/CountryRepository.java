package fr.poec.springboot.instant_faking.repository;

import fr.poec.springboot.instant_faking.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface CountryRepository
        extends JpaRepository<Country, Long>,
                EntityNameRepository<Country>
{

    // SELECT * FROM country WHERE slug = {slug}
    Optional<Country> findBySlug(String slug);

    Optional<Country> findByNameOrCodeOrSlugOrNationality(String name, String code, String slug, String nationality);

}