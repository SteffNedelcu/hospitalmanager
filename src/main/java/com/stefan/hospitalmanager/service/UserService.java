package com.stefan.hospitalmanager.service;

import com.stefan.hospitalmanager.entity.User;
import com.stefan.hospitalmanager.entity.security.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {
    User findByUsername(String username);

    User findByEmail(String email);

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);

    void save(User User);

    User createUser(User user, Set<UserRole> userRoles);

    User saveUser(User User);

    List<User> findUserList();

    void enableUser(String username);

    void disableUser(String username);
}
