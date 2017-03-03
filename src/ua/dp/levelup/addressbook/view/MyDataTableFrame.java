package ua.dp.levelup.addressbook.view;

import ua.dp.levelup.addressbook.dao.DAO;
import ua.dp.levelup.addressbook.dao.DataProvider;
import ua.dp.levelup.addressbook.dao.impl.CitizenCSVDAOImpl;
import ua.dp.levelup.addressbook.dao.impl.CitizenJSONDAOImpl;
import ua.dp.levelup.addressbook.dao.impl.FileDataProviderImpl;
import ua.dp.levelup.addressbook.entity.Citizen;
import ua.dp.levelup.addressbook.view.impl.CitizenTablePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by andrey on 12.01.2017.
 */
public class MyDataTableFrame extends JFrame
{

    public MyDataTableFrame()
    {
        init();
    }

    private void init()
    {
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
