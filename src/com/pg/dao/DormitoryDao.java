package com.pg.dao;

import com.pg.entity.Dormitory;

import java.util.List;

public interface DormitoryDao {
    List<Dormitory> list();
    List<Dormitory> search(String key,String value);
    List<Dormitory> availableList();
    Integer subAvailable(Integer id);
    Integer addAvailable(Integer id);
    List<Integer> findDormitoryIdByBuildingId(Integer id);
    Integer availableId();
    Integer deleteById(Integer id);
    Integer save(Dormitory dormitory);
    Integer update(Dormitory dormitory);
    List<Dormitory> findByBuildingId(Integer id);
}
