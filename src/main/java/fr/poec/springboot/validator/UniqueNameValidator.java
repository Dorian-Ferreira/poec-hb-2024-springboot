package fr.poec.springboot.validator;

import fr.poec.springboot.repository.GameRepository;
import fr.poec.springboot.repository.NameRepository;
import fr.poec.springboot.validator.annotation.ExistingGame;
import fr.poec.springboot.validator.annotation.UniqueName;
import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Component;


@Component
public class UniqueNameValidator implements ConstraintValidator<UniqueName,String> {

    private Class<? extends NameRepository<?>> entity;

    private EntityManager em;

    @Autowired
    UniqueNameValidator(EntityManager em){
        this.em = em;
    }


    @Override
    public void initialize(UniqueName constraintAnnotation){
        this.entity = constraintAnnotation.entity();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        JpaRepositoryFactory jpaRepositoryFactory = new JpaRepositoryFactory(em);
        return jpaRepositoryFactory.getRepository(entity).findByName(s).isEmpty();
    }
}
