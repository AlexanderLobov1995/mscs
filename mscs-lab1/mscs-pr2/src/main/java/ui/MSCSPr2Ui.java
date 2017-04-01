package ui;

import service.MSCSPr2Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MSCSPr2Ui extends JFrame implements ActionListener {


    private JButton buttonToFindAndSteal;
    private JButton buttonToStop;
    private JButton buttonToOpen;
    private JLabel label;

    private MSCSPr2Service service;

    public MSCSPr2Ui(String title) {
        super(title);
        service = new MSCSPr2Service();
        setLayout(new FlowLayout());
        buttonToFindAndSteal = new JButton("Find");
        buttonToStop = new JButton("Stop");
        buttonToOpen = new JButton("Open");
        label = new JLabel("");

        buttonToStop.setVisible(true);

        add(buttonToFindAndSteal);
        add(buttonToStop);
        add(buttonToOpen);
        add(label);

        buttonToFindAndSteal.addActionListener(this);
        buttonToStop.addActionListener(this);
        buttonToOpen.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonToFindAndSteal) {
            buttonToFindAndSteal.setVisible(false);
            buttonToStop.setVisible(true);
            label.setText(service.toFindAndSteal());
        }
        if (e.getSource() == buttonToStop) {
            buttonToFindAndSteal.setVisible(true);
            buttonToStop.setVisible(false);
            service.setToggleToFind(false);
        }
        if (e.getSource() == buttonToOpen) {
            service.toOpen();
        }
    }
}
