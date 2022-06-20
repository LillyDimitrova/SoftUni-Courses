package bg.softuni.FindYourHome.model.validation;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {

    private String first;
    private String second;
    private String message;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {

        first = constraintAnnotation.first();
        second = constraintAnnotation.second();
        message = constraintAnnotation.message();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);

        Object firstValue = beanWrapper.getPropertyValue(this.first);
        Object secondValue = beanWrapper.getPropertyValue(this.second);

        boolean valid;

        if (firstValue == null){
            valid = secondValue == null;
        } else {
            valid = firstValue.equals(secondValue);
        }

        if (!valid) {
            context.buildConstraintViolationWithTemplate(message).
                    addPropertyNode(second).
                    addConstraintViolation().
                    disableDefaultConstraintViolation();
        }

        return valid;
    }
}