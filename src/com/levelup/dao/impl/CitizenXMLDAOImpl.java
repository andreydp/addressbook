package com.levelup.dao.impl;

import com.levelup.dao.AbstractXMLDAO;
import com.levelup.dao.DataProvider;
import com.levelup.entity.Citizen;

/**
 * Created by java on 28.02.2017.
 */
public class CitizenXMLDAOImpl extends AbstractXMLDAO<Citizen> {

    public CitizenXMLDAOImpl(DataProvider fileDataProvider, String fileName) {
        super(fileDataProvider, fileName, "<CitizenList>", "</CitizenList>", Citizen.class);
    }

    @Override
    protected Citizen parseEntity(String str) {
        return parser.parseXML(str, Citizen.class);
    }

    @Override
    public String viewEntity(Citizen entity) {
        return parser.toXML(entity);
    }

}
