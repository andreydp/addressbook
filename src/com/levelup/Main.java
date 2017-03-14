package com.levelup;

import com.levelup.dao.DAO;
import com.levelup.dao.DataProvider;
import com.levelup.dao.impl.FileDataProviderImpl;
import com.levelup.dao.impl.StreetCSVDAOImpl;
import com.levelup.entity.Street;
import com.levelup.view.MyDataTableFrame;
import com.levelup.view.impl.CitizenTablePanel;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by java on 10.01.2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {
//        String citizen = "\t{\"id\": 1, \"firstName\": \"John\", \"lastName\": \"Doe\", \"age\": 46, \"streetId\": 21},";

//        System.out.println(citizen.trim().replaceAll("[\",\\s{}]|id|firstName|lastName|age|streetId", ""));

        new MyDataTableFrame();

//        FileDataProviderImpl provider = new FileDataProviderImpl("");
//
//        DAO<Street> streetDAO = new StreetCSVDAOImpl(provider, "street.csv");
//
//        provider.openConnection();
//
//        streetDAO.create(new Street(7L, "Topolia_1"));
//
//        ArrayList<Street> streets = streetDAO.read();
//
//        System.out.println(streets);
    }

}
