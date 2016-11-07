package com.socialNetwork.model.user;

import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author julien
 */
public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

}
