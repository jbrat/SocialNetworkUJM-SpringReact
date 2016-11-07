package com.socialNetwork.model.user;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class to create a user with the form 
 *
 * @author UJM's students
 */
public class UserCreateForm {

    @NotEmpty
    private String login = "";
    @NotEmpty
    private String lastName = "";
    @NotEmpty
    private String firstName = "";
    @NotEmpty
    private String email = "";
    @NotEmpty
    private String password = "";
    @NotEmpty
    private String passwordRepeated = "";
    @NotNull
    private UserRole role = UserRole.USER;

    public String getLogin() {
        return login;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public UserRole getRole() {
        return role;
    }
    
    

}
