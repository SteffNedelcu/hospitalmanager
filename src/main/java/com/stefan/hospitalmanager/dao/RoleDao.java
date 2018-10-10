package com.stefan.hospitalmanager.dao;

import com.stefan.hospitalmanager.entity.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
