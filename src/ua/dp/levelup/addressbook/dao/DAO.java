package ua.dp.levelup.addressbook.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by andrey on 27.02.17.
 */
public interface DAO<T>
{
    void create(T t) throws FileNotFoundException;

    ArrayList<T> read() throws IOException;

    void update(T t);

    void delete(T t);
}
