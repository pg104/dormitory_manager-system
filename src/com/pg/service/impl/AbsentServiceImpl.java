package com.pg.service.impl;

import com.pg.dao.AbsentDao;
import com.pg.dao.impl.AbsentDaoImpl;
import com.pg.entity.Absent;
import com.pg.service.AbsentService;

import java.util.List;

public class AbsentServiceImpl implements AbsentService {

    private AbsentDao absentDao = new AbsentDaoImpl();

    @Override
    public void save(Absent absent) {
        Integer save = absentDao.save(absent);
        if (save != 1){
            throw new RuntimeException("添加缺寝记录失败");
        }
    }

    @Override
    public List<Absent> list() {
        return absentDao.list();
    }

    @Override
    public List<Absent> search(String key, String value) {
        if (value.equals("")){
            return absentDao.list();
        }
        return absentDao.search(key, value);
    }
}
