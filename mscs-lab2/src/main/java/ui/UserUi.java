package ui;

import service.Service;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class UserUi extends JFrame implements ActionListener, TableModelListener {

    private Service service;
    private Ui ui;

    private JButton addUser;
    private JButton addChar;
    private JButton remUser;
    private JButton remChar;
    private JTable table;
    private JPanel panel;
    private JPanel takeGrant1;
    private JPanel takeGrant2;
    private JPanel takeGrant3;
    private JPanel takeGrant4;
    private JPanel checkBoxMenu;

    private DefaultTableModel tableModel;

    //Take-Grant
    private JLabel titleTakeGrant;
    private List<JCheckBox> checkBoxList = new LinkedList<JCheckBox>();
    private List<Integer> checkedList = new LinkedList<Integer>();

    private JComboBox fromUser;
    private JComboBox toUser;
    private JLabel from;
    private JLabel to;
    private JButton grant;

    private JComboBox usernameRemove;
    private JButton remove;

    private JComboBox usernameCreate;
    private JTextField rights;
    private JButton create;

    private int currentFromUser = 0;
    private int currentToUser = 0;
    private int currentUsernameRemove = 0;
    private int currentUsernameCreate = 0;

    List<List<String>> mtx = null;

    public UserUi(String title, Ui ui, Service service) {
        super(title);

        this.ui = ui;
        this.service = service;

        mtx = service.getAccessMatrix().getMtx();

        panel = new JPanel();
        takeGrant1 = new JPanel();
        takeGrant2 = new JPanel();
        takeGrant3 = new JPanel();
        takeGrant4 = new JPanel();

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(mtx.get(0).toArray());

        addUser = new JButton("Add user");
        addChar = new JButton("Add character");
        remUser = new JButton("Remove user");
        remChar = new JButton("Remove character");

        titleTakeGrant = new JLabel("Take-Grant");
        checkBoxMenu = new JPanel();

        fromUser = new JComboBox();
        toUser = new JComboBox();
        from = new JLabel("From");
        to = new JLabel("To");
        grant = new JButton("Grant");

        usernameRemove = new JComboBox();
        remove = new JButton("Remove");

        usernameCreate = new JComboBox();
        rights = new JTextField(15);
        create = new JButton("Create");

        for (List<String> row : mtx) {
            tableModel.addRow(row.toArray());
        }

        table = new JTable(tableModel);

        updateBox();

        addUser.setPreferredSize(new Dimension(180, 40));
        addChar.setPreferredSize(new Dimension(180, 40));
        remUser.setPreferredSize(new Dimension(180, 40));
        remChar.setPreferredSize(new Dimension(180, 40));

        panel.add(addUser);
        panel.add(addChar);
        panel.add(remUser);
        panel.add(remChar);

        takeGrant1.add(titleTakeGrant);
        takeGrant1.add(checkBoxMenu);

        takeGrant2.add(from);
        takeGrant2.add(fromUser);
        takeGrant2.add(to);
        takeGrant2.add(toUser);
        takeGrant2.add(grant);

        takeGrant3.add(usernameRemove);
        takeGrant3.add(remove);

        takeGrant4.add(usernameCreate);
        takeGrant4.add(rights);
        takeGrant4.add(create);

        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(table);
        contents.add(panel);
        contents.add(takeGrant1);
        contents.add(takeGrant2);
        contents.add(takeGrant3);
        contents.add(takeGrant4);

        getContentPane().add(contents);

        table.getModel().addTableModelListener(this);
        addUser.addActionListener(this);
        addChar.addActionListener(this);
        remUser.addActionListener(this);
        remChar.addActionListener(this);

        grant.addActionListener(this);
        remove.addActionListener(this);
        create.addActionListener(this);

        fromUser.addActionListener(this);
        toUser.addActionListener(this);
        usernameRemove.addActionListener(this);
        usernameCreate.addActionListener(this);

        this.setSize(1000, 300);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addUser) {

            service.getAccessMatrix().addUser();
            tableModel.addRow(mtx.get(mtx.size() - 1).toArray());
        }

        if (e.getSource() == addChar) {
            service.getAccessMatrix().addCharacter();
            tableModel.addColumn("");
        }

        if (e.getSource() == remUser) {
            if (table.getSelectedRow() > 0) {
                service.getAccessMatrix().removeUser(table.getSelectedRow());
                tableModel.removeRow(table.getSelectedRow());
            } else {
                JOptionPane.showMessageDialog(null, "Please, selectUser the user");
            }
        }

        if (e.getSource() == remChar) {
            if (table.getSelectedColumn() > 0) {
                service.getAccessMatrix().removeCharacter(table.getSelectedColumn());
                TableColumn colToDelete = table.getColumnModel().getColumn(table.getSelectedColumn());
                table.removeColumn(colToDelete);
                removeColumn();
            } else {
                JOptionPane.showMessageDialog(null, "Please, selectUser the character");
            }
        }

        if (e.getSource() == grant) {

            if (mtx.size() > 1) {
                service.grant(currentFromUser, currentToUser, checkedList);
                updateTable();
                JOptionPane.showMessageDialog(null, "Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "List of users is empty");
            }

        }

        if ((e.getSource() == remove)) {

            if (mtx.size() > 1) {
                service.remove(currentUsernameRemove, checkedList);
                updateTable();
                JOptionPane.showMessageDialog(null, "Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "List of users is empty");
            }
        }

        if (e.getSource() == create) {

            if (mtx.size() > 1) {
                service.create(currentUsernameCreate, Arrays.asList(rights.getText().split(",")));
                updateTable();
                updateBox();
                JOptionPane.showMessageDialog(null, "Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "List of users is empty");
            }
        }

        if (e.getSource() instanceof JCheckBox) {
            for (int i = 0; i < checkBoxList.size(); i++) {
                if (e.getSource() == checkBoxList.get(i)) {
                    if (((JCheckBox) e.getSource()).isSelected()) {
                        checkedList.add(i);
                    } else {
                        checkedList.remove((Object) i);
                    }
                }
            }
        }

        if (e.getSource() == fromUser) {
            currentFromUser = fromUser.getSelectedIndex();
        }

        if (e.getSource() == toUser) {
            currentToUser = toUser.getSelectedIndex();
        }

        if (e.getSource() == usernameRemove) {
            currentUsernameRemove = usernameRemove.getSelectedIndex();
        }

        if (e.getSource() == usernameCreate) {
            currentUsernameCreate = usernameCreate.getSelectedIndex();
        }

    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();
        if ((row >= 0 && col >= 0) && service.getAccessMatrix().getMtx().size() != row) {
            String value = (String) table.getValueAt(row, col);
            service.getAccessMatrix().update(row, col, value);

            updateBox();

            ui.showDataUi();
        }
    }

    private void updateTable() {

        List<String> exData = new LinkedList<>();

        int exCount = mtx.get(0).size() - tableModel.getColumnCount();

        for (int extension = 0; extension < exCount; extension++) {
            for (int indexUser = 0; indexUser < mtx.size(); indexUser++) {
                for (int indexColumn = tableModel.getColumnCount(); indexColumn < mtx.get(indexUser).size(); indexColumn++) {
                    exData.add(mtx.get(indexUser).get(indexColumn));
                }
            }
            tableModel.addColumn(exData);
            exData.clear();
        }

        for (int indexUser = 0; indexUser < mtx.size(); indexUser++) {
            for (int indexColumn = 0; indexColumn < mtx.get(indexUser).size(); indexColumn++) {
                tableModel.setValueAt(mtx.get(indexUser).get(indexColumn), indexUser, indexColumn);
            }
        }

    }

    private void updateBox() {
        toUser.removeAllItems();
        fromUser.removeAllItems();
        usernameRemove.removeAllItems();
        usernameCreate.removeAllItems();
        checkBoxMenu.removeAll();
        checkedList.clear();
        checkBoxList.clear();

        for (int i = 1; i < mtx.size(); i++) {
            fromUser.addItem(mtx.get(i).get(0));
            toUser.addItem(mtx.get(i).get(0));
            usernameRemove.addItem(mtx.get(i).get(0));
            usernameCreate.addItem(mtx.get(i).get(0));
        }
        for (int i = 1; i < mtx.get(0).size(); i++) {
            JCheckBox checkBox = new JCheckBox(mtx.get(0).get(i));
            checkBoxList.add(checkBox);
            checkBoxMenu.add(checkBox);
            checkBox.addActionListener(this);
        }
    }

    private void removeColumn() {
        tableModel.setColumnCount(0);
        tableModel.setColumnCount(mtx.get(0).size());
        updateTable();
        updateBox();
    }
}
