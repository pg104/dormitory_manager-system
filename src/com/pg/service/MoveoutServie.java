package com.pg.service;

import com.pg.entity.Moveout;

import java.util.List;

public interface MoveoutServie {
    void save(Moveout moveout);
    List<Moveout> list();
    List<Moveout> search(String key,String value);
}
