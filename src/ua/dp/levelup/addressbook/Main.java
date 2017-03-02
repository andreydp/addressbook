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
        Street gagarina = new Street(1L, "gag");
        Street krasnaya = new Street(2L, "Krasnaya");
        Street minina = new Street(3L, "Minina");

//        streetCSVDAO.update(gagarina);
        System.out.println(streetCSVDAO.getMaxId());
        System.out.println(streetCSVDAO.getMaxId());
    }
}
