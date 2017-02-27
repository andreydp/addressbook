package ua.dp.levelup.addressbook.view.impl;

import ua.dp.levelup.addressbook.view.*;

import javax.swing.*;
import java.util.List;

/**
 * Created by andreypo on 1/12/2017.
 */
public class TabbedPane extends JTabbedPane implements ua.dp.levelup.addressbook.view.Action
{
    @Override
    public void create()
    {
        ToolPanel citizenPanel = new ToolPanel();
        ToolPanel streetPanel = new ToolPanel();
    }

    @Override
    public List read()
    {
        return null;
    }

    @Override
    public void update()
    {

    }

    @Override
    public void delete()
    {

    }
}
