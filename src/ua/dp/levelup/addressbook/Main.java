package ua.dp.levelup.addressbook;

import ua.dp.levelup.addressbook.dao.impl.FileDataProvider;
import ua.dp.levelup.addressbook.dao.impl.StreetCSVDAOImpl;

import java.io.IOException;

/**
 * Created by java on 10.01.2017.
 */
public class Main
{

    public static void main(String[] args) throws IOException
    {
//        new MyDataTableFrame();

        StreetCSVDAOImpl streetCSVDAO = new StreetCSVDAOImpl(new FileDataProvider(""));
//        streetCSVDAO.create(new Street(Long.parseLong("1"),"Gagarina"));

        System.out.println(streetCSVDAO.read());
    }
}
