package fr.poec.springboot.instant_faking.repository;

import fr.poec.springboot.instant_faking.entity.Platform;
import fr.poec.springboot.instant_faking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long>,
                EntityNameRepository<User> {

    Optional<User> findByEmail(String email);

}