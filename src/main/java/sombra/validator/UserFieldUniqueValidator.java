package sombra.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sombra.dao.UsersDAO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
public class UserFieldUniqueValidator implements ConstraintValidator<Unique, String>{

    @Qualifier("usersDAO")
    @Autowired
    UsersDAO usersDAO;

    private Unique constraint;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.constraint = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        switch (constraint.field()){
            case NAME:
                return usersDAO.countByName(value) <= 0;
            case EMAIL:
                return usersDAO.countByEmail(value) <= 0;
            case PHONE:
                return usersDAO.countByPhone(value) <= 0;
            default:
                return false;
        }
    }
}
