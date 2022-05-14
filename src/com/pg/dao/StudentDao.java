package com.pg.dao;

import com.pg.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> list();
    List<Student> search(String key,String value);
    Integer save(Student student);
    Integer update(Student student);
    Integer delete(Integer id);
    List<Integer> findStudentIdByDormitoryId(Integer id);
    Integer updateDormitory(Integer studentId,Integer dormitoryId);
    List<Student> moveoutList();
    List<Student> searchForMoveout(String key,String value);
    Integer updateStateById(Integer id);
    List<Student> findByDormitoryId(Integer id);
}
