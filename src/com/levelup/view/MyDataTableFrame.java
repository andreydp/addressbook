package com.levelup.view;

import com.levelup.dao.DAO;
import com.levelup.dao.DataProvider;
import com.levelup.dao.impl.CitizenCSVDAOImpl;
import com.levelup.dao.impl.CitizenJSONDAOImpl;
import com.levelup.dao.impl.FileDataProviderImpl;
import com.levelup.entity.Citizen;
import com.levelup.view.impl.CitizenTablePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Алексей on 12.01.2017.
 */
public class MyDataTableFrame extends JFrame {

    public MyDataTableFrame() {
        init();
    }

    public void init() {
        Container container = getContentPane();

        TabbedPane tabbedPane = new TabbedPane();

        DataProvider provider = new FileDataProviderImpl();
        DAO<Citizen> citizenDAO = new CitizenJSONDAOImpl(provider, "citizen.json");

        CitizenTablePanel citizenTablePanel = new CitizenTablePanel(citizenDAO);
        tabbedPane.add(citizenTablePanel);

        container.add(tabbedPane, BorderLayout.CENTER);
        container.add(new ToolPanel(tabbedPane, provider), BorderLayout.PAGE_END);

        setBounds(0, 0, 800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
