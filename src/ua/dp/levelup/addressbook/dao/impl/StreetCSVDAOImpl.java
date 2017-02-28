package ua.dp.levelup.addressbook.dao.impl;

import ua.dp.levelup.addressbook.dao.AbstractCSVDAO;
import ua.dp.levelup.addressbook.entity.Street;

/**
 * Created by andrey on 27.02.17.
 */
public class StreetCSVDAOImpl extends AbstractCSVDAO<Street>
{
    public StreetCSVDAOImpl(final FileDataProvider fileDataProvider)
    {
        super(fileDataProvider, "street.csv", "id;streetName");
    }

    @Override
    protected Street parseEntity(final String str)
    {
        String[] arr = str.split(";");
        return new Street(Long.parseLong(arr[0]), arr[1]);
    }

    @Override

    public String viewEntity(final Street street)
    {
        return String.format("%d;%s", street.getId(), street.getStreetName());
    }
}
