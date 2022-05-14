package com.pg.service;

import com.pg.entity.dto.SystemAdminDto;

public interface SystemAdminService {

    SystemAdminDto login(String username,String password);

}
