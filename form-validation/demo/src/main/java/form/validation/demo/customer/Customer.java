package form.validation.demo.customer;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import form.validation.demo.validation.CourseCode;

public class Customer {
    private String firstName;

    @NotNull(message = "is required")
    @Size(min=1, message = "is required")
    private String lastName;

    @Min(value = 0, message = "must be greater than or equal to zero")
    @Max(value = 10, message = "must be less than or equal to 10")
    @NotNull(message = "is required")
    private Integer freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "{typeMismatch.customer.freePasses}")
    private String postalCode;

    @CourseCode(value = "TOPS", message = "must start with TOPS")
    private String courseCode;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getFreePasses() {
        return this.freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

}
