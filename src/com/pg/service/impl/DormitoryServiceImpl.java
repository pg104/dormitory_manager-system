package com.pg.service.impl;

import com.pg.dao.DormitoryDao;
import com.pg.dao.StudentDao;
import com.pg.dao.impl.DormitoryDaoImpl;
import com.pg.dao.impl.StudentDaoImpl;
import com.pg.entity.Dormitory;
import com.pg.service.DormitoryService;

import java.util.List;

public class DormitoryServiceImpl implements DormitoryService {

    private DormitoryDao dormitoryDao = new DormitoryDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Dormitory> availableList() {
        return dormitoryDao.availableList();
    }

    @Override
    public List<Dormitory> list() {
        return dormitoryDao.list();
    }

    @Override
    public List<Dormitory> search(String key, String value) {
        if (value.equals("")) {
            return dormitoryDao.list();
        }
        return dormitoryDao.search(key, value);
    }

    @Override
    public void save(Dormitory dormitory) {
        Integer save = dormitoryDao.save(dormitory);
        if (save != 1) {
            throw new RuntimeException("添加宿舍信息失败");
        }
    }

    @Override
    public void update(Dormitory dormitory) {
        Integer update = dormitoryDao.update(dormitory);
        if (update != 1) {
            throw new RuntimeException("修改宿舍信息失败");
        }
    }

    @Override
    public void delete(Integer id) {
        List<Integer> studentIdList = studentDao.findStudentIdByDormitoryId(id);
        for (Integer studentId : studentIdList) {
            Integer availableId = dormitoryDao.availableId();
            Integer updateDormitory = studentDao.updateDormitory(studentId, availableId);
            Integer subAvailable = dormitoryDao.subAvailable(availableId);
            if (updateDormitory != 1 || subAvailable != 1) {
                throw new RuntimeException("学生更换宿舍失败");
            }
        }
        Integer delete = dormitoryDao.deleteById(id);
        if (delete != 1) {
            throw new RuntimeException("删除宿舍信息失败");
        }
    }

    @Override
    public List<Dormitory> findByBuildingId(Integer buildingId) {
        return dormitoryDao.findByBuildingId(buildingId);
    }

}