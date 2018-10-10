package com.stefan.hospitalmanager.dao;

import com.stefan.hospitalmanager.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User,Long>,UserDaoCustom{
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
}
