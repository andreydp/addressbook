package com.levelup.view;

import com.levelup.dao.DataProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Алексей on 11.01.2017.
 */
public class ToolPanel extends JPanel {

    private final TabbedPane workingPanel;
    private JComboBox<String> connectionType = new JComboBox<>();
    private final DataProvider provider;

    private static final int ACTION_BTN_W = 75;
    private static final int ACTION_BTN_H = 50;
    private static final int CONNECTION_BTN_W = 125;
    private static final int BTN_Y = 310;
    private static final int BTN_X_STEP = 100;

    public ToolPanel(TabbedPane workingPanel, DataProvider provider) {
        this.workingPanel = workingPanel;
        this.provider = provider;
        initLayout();
    }

    private void initLayout() {
        setSize( new Dimension(200, 400));
        setVisible(true);
        initButtons();
    }

    private void initButtons() {
        createConnectionType();
        createConnectionButtons();
        createActionButtons();
    }



    private void createConnectionType() {
        addConnectionTypeList(connectionType);
        connectionType.setBounds(0, BTN_Y, CONNECTION_BTN_W, ACTION_BTN_H);
        add(connectionType);
    }

    private void addConnectionTypeList(JComboBox<String> connectionType) {
        String[] resources = {"CSV", "H2", "XML"};
        for (String type : resources) {
            connectionType.addItem(type);
        }
    }

    private void createConnectionButtons() {
        ButtonGroup buttonGroup = new ButtonGroup();
        JToggleButton[] buttons = {new JToggleButton("Connect"), new JToggleButton("Disconnect")};
        ActionListener[] listeners = {connectListener(), disconnectListener()};
        for (int i = 0; i < buttons.length; i++) {
            JToggleButton button = buttons[i];
            button.setBounds(25, BTN_Y, CONNECTION_BTN_W, ACTION_BTN_H);
            button.addActionListener(listeners[i]);
            buttonGroup.add(button);
            add(button);
        }
    }

    private ActionListener connectListener() {
        return event -> {
            provider.openConnection();
            System.out.println("Connected");
            workingPanel.read();
        };
    }

    private ActionListener disconnectListener() {
        return event -> {
            provider.closeConnection();
            System.out.println("Disconnected");
        };
    }

    private void createActionButtons() {
        JButton[] actionButtons = {new JButton("create"),
                new JButton("read"),
                new JButton("update"),
                new JButton("delete")};
        ActionListener[] listeners = {createListener(),
                readListener(),
                updateListener(),
                deleteListener()};

        for (int i = 0; i < actionButtons.length; i++) {
            JButton button = actionButtons[i];
            button.addActionListener(listeners[i]);
            button.setBounds(200 + BTN_X_STEP * i, BTN_Y, ACTION_BTN_W, ACTION_BTN_H);
            add(button);
        }
    }

    private ActionListener createListener() {
        return e -> workingPanel.create();
    }

    private ActionListener readListener() {
        return e -> workingPanel.read();
    }

    private ActionListener updateListener() {
        return e -> workingPanel.update();
    }

    private ActionListener deleteListener() {
        return e -> workingPanel.delete();
    }

}
