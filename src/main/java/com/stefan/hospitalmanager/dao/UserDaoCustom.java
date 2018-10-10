package com.stefan.hospitalmanager.dao;

import com.stefan.hospitalmanager.entity.User;
import java.util.List;

public interface UserDaoCustom{
    User findById(Integer id);
}
