package ua.dp.levelup.addressbook;

import ua.dp.levelup.addressbook.view.impl.CitizenTablePanel;
import ua.dp.levelup.addressbook.view.impl.CreateCitizenDialog;

import javax.swing.*;

/**
 * Created by java on 10.01.2017.
 */
public class Main
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setBounds(0,0,600,400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CitizenTablePanel panel = new CitizenTablePanel();
//        panel.setVisible(true);
        frame.add(panel);
        frame.setVisible(true);
    }
}
