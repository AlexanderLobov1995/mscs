package ui;

import service.Service;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class Ui extends JFrame implements ActionListener {

    private Service service = null;

    JPanel pn1;
    JPanel pn2;
    JPanel pn3;

    private JLabel dirName;
    private JTextField dirNameTextField;
    private JLabel filePath;
    private JTextField filePathTextField;
    private JButton chooseFileCreate;
    private JComboBox al;
    private JButton create;

    private JLabel copyTitle;
    private JLabel from;
    private JTextField fromTextField;
    private JButton chooseFileCopyFrom;
    private JLabel to;
    private JTextField toTextField;
    private JButton chooseFileCopyTo;
    private JButton copy;

    private JLabel changeTitle;
    private JLabel accessStatus;
    private JComboBox accessListBox;
    private JComboBox accessSuggestionBox;
    private JButton change;


    private JFileChooser fileChooserCreate;
    private JFileChooser fileChooserFromCopy;
    private JFileChooser fileChooserToCopy;

    private String currentAccessCreate = "";
    private String currentAccessChange = "";
    private String currentDirChange;

    public Ui(String title, Service service) {
        super(title);
        this.service = service;

        fileChooserCreate = new JFileChooser();
        fileChooserFromCopy = new JFileChooser();
        fileChooserToCopy = new JFileChooser();
        fileChooserCreate.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooserToCopy.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        pn1 = new JPanel();
        dirName = new JLabel("Directory name");
        dirNameTextField = new JTextField(20);
        filePath = new JLabel("Path");
        filePathTextField = new JTextField(20);
        chooseFileCreate = new JButton("...");
        al = new JComboBox();
        al.addItem("");
        al.addItem("Secret");
        al.addItem("Top Secret");
        create = new JButton("Create");
        chooseFileCreate.addActionListener(this);
        fileChooserCreate.addActionListener(this);
        al.addActionListener(this);
        create.addActionListener(this);
        pn1.add(dirName);
        pn1.add(dirNameTextField);
        pn1.add(filePath);
        pn1.add(filePathTextField);
        pn1.add(chooseFileCreate);
        pn1.add(al);
        pn1.add(create);

        pn2 = new JPanel();
        copyTitle = new JLabel("Copy");
        from = new JLabel("From");
        fromTextField = new JTextField(20);
        chooseFileCopyFrom = new JButton("...");
        to = new JLabel("To");
        toTextField = new JTextField(20);
        chooseFileCopyTo = new JButton("...");
        copy = new JButton("Copy");
        chooseFileCopyFrom.addActionListener(this);
        chooseFileCopyTo.addActionListener(this);
        copy.addActionListener(this);
        fileChooserFromCopy.addActionListener(this);
        fileChooserToCopy.addActionListener(this);
        pn2.add(copyTitle);
        pn2.add(from);
        pn2.add(fromTextField);
        pn2.add(chooseFileCopyFrom);
        pn2.add(to);
        pn2.add(toTextField);
        pn2.add(chooseFileCopyTo);
        pn2.add(copy);

        pn3 = new JPanel();
        changeTitle = new JLabel("Change");
        accessListBox = new JComboBox();
        accessStatus = new JLabel("");
        accessSuggestionBox = new JComboBox();
        accessSuggestionBox.addItem("");
        accessSuggestionBox.addItem("Secret");
        accessSuggestionBox.addItem("Top Secret");
        change = new JButton("Change");
        accessListBox.addActionListener(this);
        accessSuggestionBox.addActionListener(this);
        change.addActionListener(this);
        pn3.add(changeTitle);
        pn3.add(accessListBox);
        pn3.add(accessStatus);
        pn3.add(accessSuggestionBox);
        pn3.add(change);

        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(pn1);
        contents.add(pn2);
        contents.add(pn3);

        getContentPane().add(contents);


        this.setVisible(true);
        this.setSize(900, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == create) {
            String dirName = dirNameTextField.getText();
            String dirPath = filePathTextField.getText();

            if (dirName.equals("") || dirPath.equals("")) {
                JOptionPane.showMessageDialog(null, "Directory Name/Path is not completed");
            } else {
                service.create(dirName, dirPath, currentAccessCreate);
                updateComboBox();
                JOptionPane.showMessageDialog(null, "Successfully");
            }

        }
        if (e.getSource() == al) {
            currentAccessCreate = (String) al.getItemAt(al.getSelectedIndex());
        }
        if (e.getSource() == chooseFileCreate) {
            fileChooserCreate.showSaveDialog(null);

        }
        if (e.getSource() == fileChooserCreate) {
            filePathTextField.setText(fileChooserCreate.getSelectedFile().toString());
        }

        if (e.getSource() == chooseFileCopyFrom) {
            fileChooserFromCopy.showDialog(null, "Select");
        }
        if (e.getSource() == fileChooserFromCopy) {
            fromTextField.setText(fileChooserFromCopy.getSelectedFile().toString());
        }
        if (e.getSource() == chooseFileCopyTo) {
            fileChooserToCopy.showDialog(null, "Select");
        }
        if (e.getSource() == fileChooserToCopy) {
            toTextField.setText(fileChooserToCopy.getSelectedFile().toString());
        }
        if (e.getSource() == copy) {
            String filePath = fromTextField.getText();
            String dirPath = toTextField.getText();

            if (filePath.equals("") || dirPath.equals("")) {
                JOptionPane.showMessageDialog(null, "File/Directory path is not completed");
            } else {
                service.copy(filePath, dirPath);
            }

        }

        if (e.getSource() == accessSuggestionBox) {
            currentAccessChange = (String) accessSuggestionBox.getItemAt(accessSuggestionBox.getSelectedIndex());
        }
        if (e.getSource() == accessListBox) {
            currentDirChange = (String) accessListBox.getItemAt(accessListBox.getSelectedIndex());
            accessStatus.setText(service.getAccessList().get(currentDirChange));
        }
        if (e.getSource() == change) {
            Map<String, String> accessList = service.getAccessList();

            accessList.put(currentDirChange, currentAccessChange);
            accessStatus.setText(service.getAccessList().get(currentDirChange));
        }
    }

    private void updateComboBox() {
        accessListBox.removeAllItems();
        for (Map.Entry<String, String> value : service.getAccessList().entrySet()) {
            accessListBox.addItem(value.getKey());
        }
    }
}
