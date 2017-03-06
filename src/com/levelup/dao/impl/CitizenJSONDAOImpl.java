package com.levelup.dao.impl;

import com.levelup.dao.AbstractJSONDAO;
import com.levelup.dao.DataProvider;
import com.levelup.entity.Citizen;

/**
 * Created by java on 03.03.2017.
 */
public class CitizenJSONDAOImpl extends AbstractJSONDAO<Citizen>
{


    public CitizenJSONDAOImpl(DataProvider fileDataProvider, String fileName)
    {
        super(fileDataProvider, fileName, "{\"citizenList\": [");
    }

    @Override
    protected Citizen parseEntity(String str)
    {
        String citizen = "";
        citizen = str.trim().replaceAll("[\"\\s:{}]|id|firstName|lastName|age|streetId", "");
        if (citizen.endsWith(",")) citizen = citizen.substring(0, citizen.length() - 1);

        String[] params = citizen.split(",");

        long id = Long.parseLong(params[0]);
        String fName = params[1];
        String lName = params[2];
        int age = Integer.parseInt(params[3]);
        long streetId = Long.parseLong(params[4]);

        return new Citizen(id, fName, lName, age, streetId);
    }

    @Override
    public String viewEntity(Citizen entity)
    {
        return "\t{" +
                "\"id\": " + entity.getId() + ", " +
                "\"firstName\": \"" + entity.getFistName() + "\", " +
                "\"lastName\": \"" + entity.getLastName() + "\", " +
                "\"age\": " + entity.getAge() + ", " +
                "\"streetId\": " + entity.getStreetId() +
                "}";
    }
}
