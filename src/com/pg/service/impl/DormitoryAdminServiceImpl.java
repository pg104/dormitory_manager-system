package com.pg.service.impl;

import com.pg.dao.DormitoryAdminDao;
import com.pg.dao.impl.DormitoryAdminDaoImpl;
import com.pg.entity.DormitoryAdmin;
import com.pg.entity.dto.DormitoryAdminDto;
import com.pg.service.DormitoryAdminService;

import java.util.List;

public class DormitoryAdminServiceImpl implements DormitoryAdminService {

    private DormitoryAdminDao dormitoryAdminDao = new DormitoryAdminDaoImpl();

    @Override
    public List<DormitoryAdmin> list() {
       return dormitoryAdminDao.list();
    }

    @Override
    public List<DormitoryAdmin> search(String key, String value) {
        if (value.equals("")){
            return dormitoryAdminDao.list();
        }
        return dormitoryAdminDao.search(key,value);
    }

    @Override
    public void save(DormitoryAdmin dormitoryAdmin) {
        Integer result = dormitoryAdminDao.save(dormitoryAdmin);
        if (result != 1){
            throw new RuntimeException("宿管信息添加失败");
        }
    }

    @Override
    public void update(DormitoryAdmin dormitoryAdmin) {
        Integer result = dormitoryAdminDao.update(dormitoryAdmin);
        if (result != 1){
            throw new RuntimeException("宿管信息更新失败");
        }
    }

    @Override
    public void delete(Integer id) {
        Integer result = dormitoryAdminDao.delete(id);
        if (result != 1){
            throw new RuntimeException("宿管信息删除失败");
        }
    }

    @Override
    public DormitoryAdminDto login(String username, String password) {
        DormitoryAdmin dormitoryAdmin = dormitoryAdminDao.findByUsername(username);
        DormitoryAdminDto dormitoryAdminDto = new DormitoryAdminDto();
        if (dormitoryAdmin == null){
            dormitoryAdminDto.setCode(-1);
        } else {
            if (!dormitoryAdmin.getPassword().equals(password)){
                dormitoryAdminDto.setCode(-2);
            } else {
                dormitoryAdminDto.setCode(0);
                dormitoryAdminDto.setDormitoryAdmin(dormitoryAdmin);
            }
        }
        return dormitoryAdminDto;
    }
}
