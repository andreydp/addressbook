package com.levelup.dao.impl;

import com.levelup.dao.AbstractCSVDAO;
import com.levelup.dao.DataProvider;
import com.levelup.entity.Citizen;
import com.levelup.entity.Street;

/**
 * Created by java on 28.02.2017.
 */
public class CitizenCSVDAOImpl extends AbstractCSVDAO<Citizen> {

    public CitizenCSVDAOImpl(DataProvider fileDataProvider, String fileName) {
        super(fileDataProvider, fileName, "id;fName;lName;age;street_id");
    }

    @Override
    protected Citizen parseEntity(String str) {
        String[] params = str.split(";");

        long id = Long.parseLong(params[0]);
        String fName = params[1];
        String lName = params[2];
        int age = Integer.parseInt(params[3]);
        long streetId = Long.parseLong(params[4]);

        return new Citizen(id, fName, lName, age, streetId);
    }

    @Override
    public String viewEntity(Citizen entity) {
        return entity.getId() + ";"
                + entity.getFistName() + ";"
                + entity.getLastName() + ";"
                + entity.getAge() + ";"
                + entity.getStreetId()
                + "\r\n";
    }

}
