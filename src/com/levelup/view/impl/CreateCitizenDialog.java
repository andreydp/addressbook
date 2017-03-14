package com.levelup.view.impl;

import com.levelup.entity.Citizen;
import com.levelup.view.*;

import javax.swing.*;
import java.awt.*;
import com.levelup.view.Dialog;

/**
 * Created by java on 10.01.2017.
 */
public class CreateCitizenDialog extends Dialog {

    private TextField firstName;
    private TextField lastName;
    private TextField age;
    private TextField streetId;

    public CreateCitizenDialog() {
        initComponents();
    }

    @Override
    public Citizen getEntity() {
        Citizen citizen = createCitizen();
        clearFields();
        return citizen;
    }

    private void clearFields() {
        firstName.setText("");
        lastName.setText("");
        age.setText("");
        streetId.setText("");
    }

    private Citizen createCitizen() {
        String fName = firstName.getText();
        String lName = lastName.getText();
        int age = Integer.parseInt(this.age.getText());
        String sId = this.streetId.getText();
        Long streetId = sId.isEmpty() ? null : Long.parseLong(sId);
        return new Citizen(fName, lName, age, streetId);
    }

    private void initComponents() {
        initLabels();
        initTextFields();
    }

    private void initLabels() {
        JLabel firstName = new JLabel("First Name:");
        JLabel lastName = new JLabel("Last Name:");
        JLabel age = new JLabel("Age:");
        JLabel streetId = new JLabel("Street id:");

        firstName.setBounds(30, 0, 65, 25);
        lastName.setBounds(30, 35, 65, 25);
        age.setBounds(30, 70, 65, 25);
        streetId.setBounds(30, 105, 65, 25);

        panel.add(firstName);
        panel.add(lastName);
        panel.add(age);
        panel.add(streetId);
    }

    private void initTextFields() {
        firstName = new TextField();
        lastName = new TextField();
        age = new TextField();
        streetId = new TextField();

        firstName.setBounds(110, 0, 100, 25);
        lastName.setBounds(110, 35, 100, 25);
        age.setBounds(110, 70, 100, 25);
        streetId.setBounds(110, 105, 100, 25);

        panel.add(firstName);
        panel.add(lastName);
        panel.add(age);
        panel.add(streetId);
    }
}
