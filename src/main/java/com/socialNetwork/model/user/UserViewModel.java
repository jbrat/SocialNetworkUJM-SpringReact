package com.socialNetwork.model.user;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Class ViewModel to load a user from the view
 *
 * @author UJM's students
 */
public class UserViewModel {

    @NotEmpty
    private String login = "";
    @NotEmpty
    private String lastName = "";
    @NotEmpty
    private String firstName = "";
    @NotEmpty
    private String email = "";

    private String password = "";

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

    /**
     * Method to load a User from model
     * 
     * @return user 
     */
    public User parse() {
        return new User(lastName, firstName, login, new BCryptPasswordEncoder().encode(password), email);
    }
    
    /**
     * Method to update a User model from the view informations
     * 
     * @param u user to be modify
     * 
     * @return user modify
     */
    public User update(User u) {
        if(!getEmail().equals(u.getEmail())) {
            u.setEmail(getEmail());
        } else if(!getLastName().equals(u.getLastName())) {
            u.setLastName(getLastName());
        } else if(!getFirstName().equals(u.getFirstName())) {
            u.setFirstName(getFirstName());
        } else if(!getLogin().equals(u.getLogin())) {
            u.setLogin(getLogin());
        }
        
        return u;
    }
}
