package ua.dp.levelup.addressbook;

import ua.dp.levelup.addressbook.dao.impl.FileDataProviderImpl;
import ua.dp.levelup.addressbook.dao.impl.StreetCSVDAOImpl;
import ua.dp.levelup.addressbook.entity.Street;
import ua.dp.levelup.addressbook.view.MyDataTableFrame;

import java.io.IOException;

/**
 * Created by java on 10.01.2017.
 */
public class Main
{

    public static void main(String[] args) throws IOException
    {
        new MyDataTableFrame();
    }
}
