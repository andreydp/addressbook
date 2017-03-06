package com.levelup;

import com.levelup.view.MyDataTableFrame;

/**
 * Created by java on 10.01.2017.
 */
public class Main {

    public static void main(String[] args) {
        String citizen = "\t{\"id\": 1, \"firstName\": \"John\", \"lastName\": \"Doe\", \"age\": 46, \"streetId\": 21},";

        System.out.println(citizen);
        citizen = citizen.trim().replaceAll("[\"\\s:{}]|id|firstName|lastName|age|streetId", "");
        if (citizen.endsWith(",")) citizen = citizen.substring(0, citizen.length() - 1);
        System.out.println(citizen);

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
