package form.validation.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;

    @Override
    public void initialize(CourseCode courseCode) {
        this.coursePrefix = courseCode.value();
    }

    @Override
    public boolean isValid(String userInput, ConstraintValidatorContext constraintValidatorContext) {
        if (userInput == null) {
            return true;
        }

        return userInput.startsWith(this.coursePrefix);
    }
}
