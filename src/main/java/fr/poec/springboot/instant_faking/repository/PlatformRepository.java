package fr.poec.springboot.instant_faking.repository;

import fr.poec.springboot.instant_faking.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository
        extends JpaRepository<Platform, Long>,
                EntityNameRepository<Platform> {

}