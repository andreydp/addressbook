package ua.dp.levelup.addressbook.view.impl;

import javax.swing.*;
import java.awt.*;

/**
 * Created by andreypo on 1/12/2017.
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
        container.add(tabbedPane, BorderLayout.CENTER);
        container.add(new ToolPanel(tabbedPane), BorderLayout.PAGE_END);

        setVisible(true);
        setBounds(0, 0, 800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
