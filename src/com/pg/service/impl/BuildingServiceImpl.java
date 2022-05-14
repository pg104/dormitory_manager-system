package com.pg.service.impl;

import com.pg.dao.BuildingDao;
import com.pg.dao.DormitoryDao;
import com.pg.dao.StudentDao;
import com.pg.dao.impl.BuildingDaoImpl;
import com.pg.dao.impl.DormitoryDaoImpl;
import com.pg.dao.impl.StudentDaoImpl;
import com.pg.entity.Building;
import com.pg.entity.Dormitory;
import com.pg.service.BuildingService;

import java.util.List;

public class BuildingServiceImpl implements BuildingService {

    private BuildingDao buildingDao = new BuildingDaoImpl();
    private DormitoryDao dormitoryDao = new DormitoryDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Building> list() {
        return buildingDao.list();
    }

    @Override
    public List<Building> search(String key, String value) {
        if (value.equals("")){
            return buildingDao.list();
        }
        return buildingDao.search(key, value);
    }

    @Override
    public void save(Building building) {
        Integer result = buildingDao.save(building);
        if (result != 1){
            throw new RuntimeException("添加楼宇信息失败");
        }
    }

    @Override
    public void update(Building building) {
        Integer result = buildingDao.update(building);
        if (result != 1){
            throw new RuntimeException("修改楼宇信息失败");
        }
    }

    @Override
    public void delete(Integer id) {
        //学生换宿舍
        List<Integer> dormitoryIdList = dormitoryDao.findDormitoryIdByBuildingId(id);
        for (Integer dormitoryId : dormitoryIdList) {
            List<Integer> studentIdList = studentDao.findStudentIdByDormitoryId(dormitoryId);
            for (Integer studentId : studentIdList) {
                Integer availableId = dormitoryDao.availableId();
                Integer updateDormitory = studentDao.updateDormitory(studentId, availableId);
                Integer subAvailable = dormitoryDao.subAvailable(availableId);
                if (updateDormitory != 1 || subAvailable != 1){
                    throw new RuntimeException("学生更换宿舍失败");
                }
            }
        }
        //删除宿舍
        for (Integer dormitoryId : dormitoryIdList) {
            Integer delete = dormitoryDao.deleteById(dormitoryId);
            if (delete != 1){
                throw new RuntimeException("宿舍信息删除失败");
            }
        }
        //删除楼宇
        Integer delete = buildingDao.delete(id);
        if (delete != 1){
            throw new RuntimeException("楼宇信息删除失败");
        }
    }
}
