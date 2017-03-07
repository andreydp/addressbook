package com.levelup.dao.impl;

import com.levelup.dao.AbstractJSONDAO;
import com.levelup.dao.DataProvider;
import com.levelup.entity.Street;

/**
 * Created by java on 03.03.2017.
 */
public class StreetJSONDAOImpl extends AbstractJSONDAO<Street>
{
    public StreetJSONDAOImpl(DataProvider fileDataProvider, String fileName)
    {
        super(fileDataProvider, fileName, "{\"streetList\": [");
    }

    @Override
    protected Street parseEntity(String str)
    {
        String citizenStr;
        citizenStr = str.trim().replaceAll("[\"\\s:{}]|id|firstName|streetName", "");
        if (citizenStr.endsWith(",")) citizenStr = citizenStr.substring(0, citizenStr.length() - 1);

        String[] params = citizenStr.split(",");

        long id = Long.parseLong(params[0]);
        String streetName = params[1];

        return new Street(id, streetName);
    }

    @Override
    public String viewEntity(Street entity)
    {
        return "\t{" + "\"id\": " + entity.getId() + "\"streetName\": " + entity.getStreetName() + "}";
    }
}
