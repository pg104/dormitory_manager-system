package com.pg.service.impl;

import com.pg.dao.DormitoryDao;
import com.pg.dao.MoveoutDao;
import com.pg.dao.StudentDao;
import com.pg.dao.impl.DormitoryDaoImpl;
import com.pg.dao.impl.MoveoutDaoImpl;
import com.pg.dao.impl.StudentDaoImpl;
import com.pg.entity.Moveout;
import com.pg.service.MoveoutServie;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MoveoutServieImpl implements MoveoutServie {

    private MoveoutDao moveoutDao = new MoveoutDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();
    private DormitoryDao dormitoryDao = new DormitoryDaoImpl();

    @Override
    public void save(Moveout moveout) {
        Integer updateStateById = studentDao.updateStateById(moveout.getStudentId());
        Integer addAvailable = dormitoryDao.addAvailable(moveout.getDormitoryId());

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        moveout.setCreateDate(simpleDateFormat.format(date));
        Integer save = moveoutDao.save(moveout);
        if (save != 1 ||updateStateById != 1 || addAvailable != 1){
            throw new RuntimeException("迁出学生失败");
        }
    }

    @Override
    public List<Moveout> list() {
        return moveoutDao.list();
    }

    @Override
    public List<Moveout> search(String key, String value) {
        if (value.equals("")){
            return moveoutDao.list();
        }
        return moveoutDao.search(key, value);
    }
}
