package com.pg.service;

import com.pg.entity.DormitoryAdmin;
import com.pg.entity.dto.DormitoryAdminDto;

import java.util.List;

public interface DormitoryAdminService {
    List<DormitoryAdmin> list();
    List<DormitoryAdmin> search(String key,String value);
    void save(DormitoryAdmin dormitoryAdmin);
    void update(DormitoryAdmin dormitoryAdmin);
    void delete(Integer id);
    DormitoryAdminDto login(String username,String password);
}
