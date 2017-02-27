package ua.dp.levelup.addressbook.dao;

/**
 * Created by andreypo on 2/27/2017.
 */
public interface DataProvider
{
    void openConnection();

    void closeConnection();
}
