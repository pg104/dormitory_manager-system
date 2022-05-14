package com.pg.service.impl;

import com.pg.dao.SystemAdminDao;
import com.pg.dao.impl.SystemAdminDaoImpl;
import com.pg.entity.SystemAdmin;
import com.pg.entity.dto.SystemAdminDto;
import com.pg.service.SystemAdminService;

public class SystemAdminServiceImpl implements SystemAdminService {

    private SystemAdminDao systemAdminDao  = new SystemAdminDaoImpl();

    @Override
    public SystemAdminDto login(String username, String password) {
        SystemAdmin systemAdmin = systemAdminDao.findByUsername(username);
        SystemAdminDto systemAdminDto = new SystemAdminDto();
        if (systemAdmin == null){
            systemAdminDto.setCode(-1);
        } else {
            if (!systemAdmin.getPassword().equals(password)){
                systemAdminDto.setCode(-2);
            } else {
                systemAdminDto.setCode(0);
                systemAdminDto.setSystemAdmin(systemAdmin);
            }
        }
        return systemAdminDto;
    }
}
