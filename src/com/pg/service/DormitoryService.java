package com.pg.service;

import com.pg.entity.Dormitory;

import java.util.List;

public interface DormitoryService {
    List<Dormitory> availableList();
    List<Dormitory> list();
    List<Dormitory> search(String key,String value);
    void save(Dormitory dormitory);
    void update(Dormitory dormitory);
    void delete(Integer id);
    List<Dormitory> findByBuildingId(Integer buildingId);
}
