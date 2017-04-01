package ui;

import service.MSCSPr1Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MSCSPr1Ui extends JFrame implements ActionListener {

    private JTextArea textArea;
    private JButton buttonToWrite;
    private JButton buttonToCopy;
    private JButton buttonToReset;
    private JLabel label;

    private MSCSPr1Service service;

    public MSCSPr1Ui(String title) {
        super(title);
        service = new MSCSPr1Service();
        setLayout(new FlowLayout());
        textArea = new JTextArea(10, 50);
        buttonToWrite = new JButton("Write to file");
        buttonToCopy = new JButton("Copy to public directory");
        buttonToReset = new JButton("Reset");
        label = new JLabel("");
        add(buttonToWrite);
        add(buttonToCopy);
        add(buttonToReset);
        add(textArea);
        add(label);
        buttonToWrite.addActionListener(this);
        buttonToCopy.addActionListener(this);
        buttonToReset.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonToWrite) {
            label.setText(service.toFile(textArea.getText()));
        }
        if (e.getSource() == buttonToCopy) {
            label.setText(service.copy());
        }
        if (e.getSource() == buttonToReset) {
            label.setText(null);
            textArea.setText(null);
        }

    }
}
