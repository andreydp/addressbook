package com.levelup.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by java on 10.01.2017.
 */
public abstract class Dialog extends JDialog {

    protected JButton ok = null;
    protected JButton cancel = null;
    protected JPanel panel = new JPanel();
    private final ActionListener listener;
    private boolean isOkPressed = false;

    public Dialog() {
        setModal(true);
        setLayout(null);
        setBounds(300, 400, 250, 240);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        add(panel);
        panel.setBounds(0, 0, 250, 240);
        panel.setLayout(null);

        this.listener = getButtonListener();

        this.ok = createActionButton("ok", new Rectangle(30, 160, 80, 30));
        this.cancel = createActionButton("cancel", new Rectangle(130, 160, 80, 30));

        panel.add(ok);
        panel.add(cancel);
    }

    public boolean isOkPressed() {
        boolean res = isOkPressed;
        isOkPressed = false;
        return res;
    }

    public void setOkPressed(boolean okPressed) {
        isOkPressed = okPressed;
    }

    public abstract Object getEntity();

    private JButton createActionButton(String name, Rectangle rectangle) {
        JButton btn = new JButton(name);
        btn.addActionListener(listener);
        btn.setBounds(rectangle);
        return btn;
    }

    private ActionListener getButtonListener() {
        return e -> {
            if(e.getActionCommand().equals("ok")) {
                isOkPressed = true;
            }
            dispose();
        };
    }
}
