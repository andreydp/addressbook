package com.levelup.dao;

import java.util.ArrayList;

/**
 * Created by andrey on 27.02.17.
 */
public interface DAO<T> {

    void create(T t);

    ArrayList<T> read();

    void update(T t);

    void delete(T t);

    T getOneById(long id);
}
