package com.levelup.view;

import com.levelup.view.impl.CitizenTablePanel;

import javax.swing.*;
import java.util.List;

/**
 * Created by Алексей on 11.01.2017.
 */
public class TabbedPane extends JTabbedPane implements Action {

    @Override
    public void create() {
        CitizenTablePanel panel = (CitizenTablePanel) getSelectedComponent();
        panel.create();
    }

    @Override
    public void read() {
        CitizenTablePanel panel = (CitizenTablePanel) getSelectedComponent();
        panel.read();
    }

    @Override
    public void update() {
        CitizenTablePanel panel = (CitizenTablePanel) getSelectedComponent();
        panel.update();
    }

    @Override
    public void delete() {
        CitizenTablePanel panel = (CitizenTablePanel) getSelectedComponent();
        panel.delete();
    }
}
