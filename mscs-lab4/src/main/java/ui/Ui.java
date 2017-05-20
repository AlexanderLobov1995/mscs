package ui;

import model.Role;
import model.User;
import service.Service;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

public class Ui extends JFrame implements ActionListener {

    private Service service = null;

    JPanel pn1;
    JPanel pn2;
    JPanel pn3;
    JPanel pn4;
    JPanel pn5;
    JPanel pn6;

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

    private JLabel userLabel;
    private JComboBox users;
    private JLabel roleLabel;
    private JComboBox userRoles;
    private JLabel changeUserLabel;
    private JTextField changeUsername;
    private JButton changeUser;
    private JButton deleteUser;
    private JButton deleteUserRole;


    private List<String> boxLevelList;
    private List<String> chosenRoleList;

    private JLabel chosenRolesLabel;
    private JComboBox chosenRoles;
    private JLabel createUserLabel;
    private JTextField createUsername;
    private JButton createUser;
    private JButton deleteChosenRole;


    private JLabel rolesLabel;
    private JComboBox roles;
    private JLabel roleNameLabel;
    private JTextField roleName;
    private JLabel lev1Label;
    private JLabel lev2Label;
    private JLabel lev3Label;
    private JCheckBox lev1;
    private JCheckBox lev2;
    private JCheckBox lev3;
    private JButton createRole;
    private JButton changeRole;
    private JButton addToChosenRoles;
    private JButton deleteRole;


    private JFileChooser fileChooserCreate;
    private JFileChooser fileChooserFromCopy;
    private JFileChooser fileChooserToCopy;

    private String currentAccessCreate = "";
    private String currentAccessChange = "";
    private String currentDirChange;

    private String chosenUser = "";

    public Ui(String title, Service service) {
        super(title);
        this.service = service;

        boxLevelList = new LinkedList<String>();
        chosenRoleList = new LinkedList<String>();

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
        al.addItem("Non Secret");
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

        pn4 = new JPanel();
        userLabel = new JLabel("Users");
        users = new JComboBox();
        roleLabel = new JLabel("Roles");
        userRoles = new JComboBox();
        changeUserLabel = new JLabel("Username");
        deleteUser = new JButton("delete the user");
        changeUser = new JButton("change the user");
        changeUsername = new JTextField(10);
        deleteUserRole = new JButton("delete user's role");
        deleteUser.addActionListener(this);
        deleteUserRole.addActionListener(this);
        changeUser.addActionListener(this);
        users.addActionListener(this);
        pn4.add(userLabel);
        pn4.add(users);
        pn4.add(roleLabel);
        pn4.add(userRoles);
        pn4.add(changeUserLabel);
        pn4.add(changeUsername);
        pn4.add(changeUser);
        pn4.add(deleteUser);
        pn4.add(deleteUserRole);


        pn5 = new JPanel();
        chosenRolesLabel = new JLabel("Chosen userRoles");
        chosenRoles = new JComboBox();
        createUserLabel = new JLabel("Username");
        createUsername = new JTextField(10);
        createUser = new JButton("create the user");
        deleteChosenRole = new JButton("delete the role");
        createUser.addActionListener(this);
        deleteChosenRole.addActionListener(this);

        pn5.add(chosenRolesLabel);
        pn5.add(chosenRoles);
        pn5.add(createUserLabel);
        pn5.add(createUsername);
        pn5.add(createUser);
        pn5.add(deleteChosenRole);

        pn6 = new JPanel();
        rolesLabel = new JLabel("Roles");
        roles = new JComboBox();
        roleNameLabel = new JLabel("Role name");
        roleName = new JTextField(10);
        lev1Label = new JLabel("Top Secret");
        lev1 = new JCheckBox();
        lev1.setName("Top Secret");
        lev2Label = new JLabel("Secret");
        lev2 = new JCheckBox();
        lev2.setName("Secret");
        lev3Label = new JLabel("Non Secret");
        lev3 = new JCheckBox();
        lev3.setName("Non Secret");
        createRole = new JButton("create");
        changeRole = new JButton("change");
        deleteRole = new JButton("delete");
        addToChosenRoles = new JButton("Add to chosen roles");
        addToChosenRoles.addActionListener(this);
        roles.addActionListener(this);
        lev1.addActionListener(this);
        lev2.addActionListener(this);
        lev3.addActionListener(this);
        createRole.addActionListener(this);
        changeRole.addActionListener(this);
        deleteRole.addActionListener(this);
        pn6.add(rolesLabel);
        pn6.add(roles);
        pn6.add(roleNameLabel);
        pn6.add(roleName);
        pn6.add(lev1Label);
        pn6.add(lev1);
        pn6.add(lev2Label);
        pn6.add(lev2);
        pn6.add(lev3Label);
        pn6.add(lev3);
        pn6.add(createRole);
        pn6.add(changeRole);
        pn6.add(addToChosenRoles);
        pn6.add(deleteRole);

        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(pn1);
        contents.add(pn2);
        contents.add(pn3);
        contents.add(pn4);
        contents.add(pn5);
        contents.add(pn6);

        getContentPane().add(contents);

        this.setVisible(true);
        this.setSize(950, 300);
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


        if (e.getSource() instanceof JCheckBox) {
            if (((JCheckBox) e.getSource()).isSelected()) {
                boxLevelList.add(((JCheckBox) e.getSource()).getName());
            } else {
                boxLevelList.remove(((JCheckBox) e.getSource()).getName());
            }
        }

        if (e.getSource() == createRole) {
            if (!roleName.getText().equals("") && boxLevelList.size() > 0) {
                service.createRole(roleName.getText(), boxLevelList);
                roleName.setText("");
                updateComboBox();
            } else {
                JOptionPane.showMessageDialog(null, "roleName or levels are not specified");
            }
        }
        if (e.getSource() == deleteRole) {
            if (roles.getItemCount() > 0) {
                service.deleteRole(roles.getSelectedIndex());
                updateComboBox();
            } else {
                JOptionPane.showMessageDialog(null, "roles are not exist");
            }
        }
        if (e.getSource() == changeRole) {
            if (!boxLevelList.isEmpty()) {
                service.changeRole(roles.getSelectedIndex(), roleName.getText(), boxLevelList);
                roleName.setText("");
                updateComboBox();
            } else {
                JOptionPane.showMessageDialog(null, "Levels are not selected!");
            }
        }
        if (e.getSource() == addToChosenRoles) {
            if (roles.getItemCount() > 0) {
                service.addToChosenRoles(roles.getSelectedIndex());
                updateComboBox();
            } else {
                JOptionPane.showMessageDialog(null, "roles are not exist");
            }
        }


        if (e.getSource() == createUser) {
            if (chosenRoles.getItemCount() > 0 && !createUsername.getText().isEmpty()) {
                service.createUser(createUsername.getText());
                updateComboBox();
            } else {
                JOptionPane.showMessageDialog(null, "username or userRoles are not selected");
            }
        }
        if (e.getSource() == deleteChosenRole) {
            if (chosenRoles.getItemCount() > 0) {
                service.deleteChosenRoles(chosenRoles.getSelectedIndex());
                updateComboBox();
            } else {
                JOptionPane.showMessageDialog(null, "chosen roles are not exist");
            }
        }


        if (e.getSource() == changeUser) {
            if (users.getItemCount() > 0 && !changeUsername.getText().isEmpty()) {
                service.changeUser(changeUsername.getText(), users.getSelectedIndex());
                updateComboBox();
            } else {
                JOptionPane.showMessageDialog(null, "user is not chosen or not specified");
            }
        }
        if (e.getSource() == deleteUser) {
            if (users.getItemCount() > 0) {
                service.deleteUser(users.getSelectedIndex());
                updateComboBox();
            } else {
                JOptionPane.showMessageDialog(null, "users are not exist");
            }
        }
        if (e.getSource() == deleteUserRole) {
            if (users.getItemCount() > 0 && userRoles.getItemCount() > 1) {
                service.deleteUserRole(users.getSelectedIndex(), userRoles.getSelectedIndex());
                updateComboBox();
            } else {
                JOptionPane.showMessageDialog(null, "Users are not there or attempt to remove the only role");
            }
        }
        if (e.getSource() == users && users.getItemCount() > 0) {
            userRoles.removeAllItems();
            for (Role role : (service.users.get(users.getSelectedIndex()).getRoles())) {
                userRoles.addItem(role.getName());
            }
            hideFolders();
        }

    }

    private void updateComboBox() {

        accessListBox.removeAllItems();
        roles.removeAllItems();
        chosenRoles.removeAllItems();
        users.removeAllItems();

        for (Map.Entry<String, String> value : service.getAccessList().entrySet()) {
            accessListBox.addItem(value.getKey());
        }
        for (Role role : service.roles) {
            roles.addItem(role.getName());
        }
        for (Role role : service.chosenRoles) {
            chosenRoles.addItem(role.getName());
        }
        if (service.users.size() > 0) {
            for (User user : service.users) {
                users.addItem(user.getName());
            }
        }

    }

    private void hideFolders() {
        try {
            Set<String> levelList = new HashSet<String>();

            for (Role userRole : service.users.get(users.getSelectedIndex()).getRoles()) {
                for (String level : userRole.getLevels()) {
                    levelList.add(level);
                }
            }
            System.out.println(levelList);
            label:
            for (Map.Entry<String, String> folder : service.getAccessList().entrySet()) {
                for (String level : levelList) {
                    if (folder.getValue().equals(level)) {
                        System.out.println("Показать");

                        Runtime.getRuntime().exec("attrib -h " + folder.getKey());

                        continue label;
                    }
                }
                System.out.println("Вскрыть");
                Runtime.getRuntime().exec("attrib +h " + folder.getKey());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
