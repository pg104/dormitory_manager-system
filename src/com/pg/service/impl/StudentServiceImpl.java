package com.pg.service.impl;

import com.pg.dao.DormitoryDao;
import com.pg.dao.StudentDao;
import com.pg.dao.impl.DormitoryDaoImpl;
import com.pg.dao.impl.StudentDaoImpl;
import com.pg.entity.Student;
import com.pg.service.StudentService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = new StudentDaoImpl();
    private DormitoryDao dormitoryDao = new DormitoryDaoImpl();

    @Override
    public List<Student> list() {
        return studentDao.list();
    }

    @Override
    public List<Student> search(String key, String value) {
        if (value.equals("")){
            return studentDao.list();
        }
        return studentDao.search(key, value);
    }

    @Override
    public void save(Student student) {
        student.setState("入住");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        student.setCreateDate(simpleDateFormat.format(date));
        Integer result = studentDao.save(student);
        Integer sub = dormitoryDao.subAvailable(student.getDormitoryId());
        if (result != 1 || sub != 1){
            throw new RuntimeException("添加学生信息失败");
        }
    }

    @Override
    public void update(Student student,Integer oldDormitoryId) {
        Integer result = studentDao.update(student);
        Integer dormitory1 = dormitoryDao.addAvailable(oldDormitoryId);
        Integer dormitory2 = dormitoryDao.subAvailable(student.getDormitoryId());
        if (result != 1 || dormitory1 != 1 || dormitory2 != 1){
            throw new RuntimeException("更新学生信息失败");
        }
    }

    @Override
    public void delete(Integer id, Integer dormitoryId) {
        Integer delete = studentDao.delete(id);
        Integer available = dormitoryDao.addAvailable(dormitoryId);
        if (delete != 1 || available != 1){
            throw new RuntimeException("删除学生信息失败");
        }
    }

    @Override
    public List<Student> moveoutList() {
        return studentDao.moveoutList();
    }

    @Override
    public List<Student> searchForMoveout(String key, String value) {
        if (value.equals("")){
            return studentDao.moveoutList();
        }
        return studentDao.searchForMoveout(key, value);
    }

    @Override
    public List<Student> findByDormitoryId(Integer dormitoryId) {
        return studentDao.findByDormitoryId(dormitoryId);
    }
}
