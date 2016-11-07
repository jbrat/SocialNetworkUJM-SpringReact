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

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    
    @Override
    public String toString() {
        return "UserCreateForm{" +
            "email='" + email.replaceFirst("@.+", "@***") + '\'' +
            ", password=***" + '\'' +
            ", passwordRepeated=***" + '\'' +
            ", role=" + role +
            '}';
    }
}
