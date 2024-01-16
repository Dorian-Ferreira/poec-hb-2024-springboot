package fr.poec.springboot.instant_faking.repository;

import fr.poec.springboot.instant_faking.entity.Game;
import fr.poec.springboot.instant_faking.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository
            extends JpaRepository<Publisher, Long>,
                    EntityNameRepository<Publisher> {

}