package ua.dp.levelup.addressbook;

import ua.dp.levelup.addressbook.dao.impl.FileDataProvider;
import ua.dp.levelup.addressbook.dao.impl.StreetCSVDAOImpl;
import ua.dp.levelup.addressbook.entity.Street;

import java.io.IOException;

/**
 * Created by java on 10.01.2017.
 */
public class Main
{

    public static void main(String[] args) throws IOException
    {
//        new MyDataTableFrame();
        FileDataProvider fileDataProvider = new FileDataProvider("");
        StreetCSVDAOImpl streetCSVDAO = new StreetCSVDAOImpl(fileDataProvider);
        fileDataProvider.openConnection();
        System.out.println(streetCSVDAO.read());
        Street gagarina = new Street(1L, "Gagarina");
        Street krasnaya = new Street(2L, "Krasnaya");
        streetCSVDAO.delete(gagarina);
        System.out.println(streetCSVDAO.read());
    }
}
