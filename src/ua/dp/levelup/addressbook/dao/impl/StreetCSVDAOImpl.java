package ua.dp.levelup.addressbook.dao.impl;

import ua.dp.levelup.addressbook.dao.AbstractCSVDAO;
import ua.dp.levelup.addressbook.entity.Street;

/**
 * Created by java on 28.02.2017.
 */
public class StreetCSVDAOImpl extends AbstractCSVDAO<Street>
{
    public StreetCSVDAOImpl(FileDataProviderImpl fileDataProvider, String fileName)
    {
        super(fileDataProvider, fileName, "id;street_name");
    }

    @Override
    protected Street parseEntity(String str)
    {
        String[] params = str.split(";");

        long id = Long.parseLong(params[0]);
        String streetName = params[1];

        return new Street(id, streetName);
    }

    @Override
    public String viewEntity(Street entity)
    {
        return entity.getId() + ";" + entity.getStreetName() + "\r\n";
    }

}
