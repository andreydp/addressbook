package com.levelup.view.impl;

import javax.swing.*;

import com.levelup.dao.DAO;
import com.levelup.entity.Citizen;
import com.levelup.view.Action;
import com.levelup.view.CitizenTableModelContainer;

import java.awt.*;
import java.util.List;

/**
 * Created by java on 10.01.2017.
 */
public class CitizenTablePanel extends JPanel implements Action {

    private final JTable table;
    private final CreateCitizenDialog dialog = new CreateCitizenDialog();
    private final CitizenTableModelContainer tableContainer;
    private final DAO<Citizen> citizenDAO;

    public CitizenTablePanel(DAO<Citizen> citizenDAO) {
        this.tableContainer = new CitizenTableModelContainer();
        this.table = new JTable(tableContainer);
        this.citizenDAO = citizenDAO;
        setName("Citizen Tab");
        init();
    }

    private void init() {
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(new Dimension(595, 300));
        add(scrollPane);
        setSize(new Dimension(595, 300));
    }

    @Override
    public void create() {
        dialog.setVisible(true);
        if(dialog.isOkPressed()) {
            Citizen citizen = dialog.getEntity();
            tableContainer.getData().add(citizen);
            citizenDAO.create(citizen);
            table.updateUI();
        }
    }

    @Override
    public void read() {
        tableContainer.setData(citizenDAO.read());
        table.updateUI();
    }

    @Override
    public void update() {
        Citizen citizen = tableContainer.getSelectedRowData(table.getSelectedRow());
        citizenDAO.update(citizen);
    }

    @Override
    public void delete() {
        Citizen citizen = tableContainer.getSelectedRowData(table.getSelectedRow());
        tableContainer.getData().remove(citizen);
        citizenDAO.delete(citizen);
        table.updateUI();
    }
}
