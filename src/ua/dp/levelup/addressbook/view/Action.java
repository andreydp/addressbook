package ua.dp.levelup.addressbook.view;

import java.util.List;

/**
 * Created by java on 10.01.2017.
 */
public interface Action
{
    void create();

    List read();

    void update();

    void delete();
}
