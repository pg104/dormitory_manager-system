package com.pg.dao;

import com.pg.entity.SystemAdmin;

public interface SystemAdminDao {
    SystemAdmin findByUsername(String username);
}
