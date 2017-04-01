package ui;

import service.Service;
import starter.Starter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Ui extends JFrame implements KeyListener, ActionListener {

    private Service service;

    private JComboBox comboBox;
    private JButton button;
    private JLabel labelInput;
    private JLabel labelOutput;
    private JTextArea inputText;
    private JTextArea outputText;

    public Ui(String title, Service service) {
        super(title);

        this.service = service;

        comboBox = new JComboBox();
        button = new JButton("Users");
        labelInput = new JLabel("Input");
        labelOutput = new JLabel("Output");
        inputText = new JTextArea(4, 17);
        outputText = new JTextArea(4, 17);

        showDataUi();

        JPanel pn1 = new JPanel();
        pn1.add(comboBox);
        pn1.add(button);

        JPanel pn2 = new JPanel();
        pn2.add(labelInput);
        pn2.add(inputText);

        JPanel pn3 = new JPanel();
        pn3.add(labelOutput);
        pn3.add(outputText);

        Box contents = new Box(BoxLayout.Y_AXIS);

        contents.add(pn1);
        contents.add(pn2);
        contents.add(pn3);

        getContentPane().add(contents);

        inputText.addKeyListener(this);
        comboBox.addActionListener(this);
        button.addActionListener(this);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setSize(270, 300);
        this.setResizable(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        outputText.setText(service.filter(inputText.getText()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox && comboBox.getSelectedIndex() >= 0) {
            service.selectUser(comboBox.getSelectedIndex());
        }
        if (e.getSource() == button) {
            Starter.setVisible();
        }
    }

    public void showDataUi() {

        comboBox.removeAllItems();

        comboBox.addItem("");

        List<List<String>> mtx = service.getAccessMatrix().getMtx();

        for (int i = 1; i < mtx.size(); i++) {
            comboBox.addItem(mtx.get(i).get(0));
        }
    }
}
