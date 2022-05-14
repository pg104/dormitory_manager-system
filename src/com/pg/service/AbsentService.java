package com.pg.service;

import com.pg.entity.Absent;

import java.util.List;

public interface AbsentService {
    void save(Absent absent);
    List<Absent> list();
    List<Absent> search(String key,String value);
}
