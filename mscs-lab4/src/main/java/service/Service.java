package service;

import file.FileUtils;
import model.Role;
import model.User;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Service {

    private Map<String, String> accessList;
    public List<User> users;
    public List<Role> roles;
    public List<Role> chosenRoles;

    public Service() {
        accessList = new LinkedHashMap<String, String>();
        users = new LinkedList<User>();
        roles = new LinkedList<Role>();
        chosenRoles = new LinkedList<Role>();
    }

    public Map<String, String> getAccessList() {
        return accessList;
    }

    public void copy(String filePath, String dirPath) {
        if (isDenied(filePath, dirPath)) {
            JOptionPane.showMessageDialog(null, "Access is denied");
        } else {

            try {
                File fileFrom = new File(filePath);
                File fileTo = new File(dirPath + filePath.substring(filePath.lastIndexOf("\\")));

                System.out.println(fileTo);
                System.out.println(dirPath);
                System.out.println(filePath.substring(filePath.lastIndexOf("\\")));

                FileUtils.copy(fileFrom, fileTo);

                JOptionPane.showMessageDialog(null, "Successfully");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Copping was failed");
            }

        }
    }

    private int getCorrectLastIndex(String filePath) {
        if (filePath.lastIndexOf("\\") > -1) {
            return (filePath.lastIndexOf("\\") - 1 == filePath.lastIndexOf(":")) ? filePath.lastIndexOf("\\") + 1 : filePath.lastIndexOf("\\");
        } else {
            return filePath.length();
        }
    }

    private boolean isDenied(String filePath, String dirPath) {
        filePath = filePath.substring(0, getCorrectLastIndex(filePath));

        System.out.println(filePath);
        return accessList.containsKey(filePath)
                && (
                (accessList.get(filePath).equals("Secret") && !(accessList.containsKey(dirPath) && (accessList.get(dirPath).equals("Secret") || accessList.get(dirPath).equals("Top Secret"))))
                        || (accessList.get(filePath).equals("Top Secret") && !(accessList.containsKey(dirPath) && accessList.get(dirPath).equals("Top Secret")))
        );
    }

    public void create(String dirName, String dirPath, String currentAccessCreate) {
        accessList.put(dirPath + correctChar(dirPath) + dirName, currentAccessCreate);
        System.out.println(dirPath + correctChar(dirPath) + dirName);
        System.out.println(currentAccessCreate);
        File file = new File(dirPath + "\\" + dirName);

        if (!file.exists()) {
            file.mkdir();
        }
    }

    private String correctChar(String dirPath) {
        return (dirPath.charAt(dirPath.length() - 1) == '\\') ? "" : "\\";
    }

    public void createRole(String text, List<String> boxLevelList) {
        this.roles.add(new Role(text, boxLevelList));
    }

    public void deleteRole(int roleIndex) {
        roles.remove(roleIndex);
    }

    public void changeRole(int roleIndex, String name, List<String> boxLevelList) {

        if (!name.isEmpty()) {
            roles.get(roleIndex).setName(name);
        }
        roles.get(roleIndex).setLevels(boxLevelList);

    }

    public void createUser(String name) {
        users.add(new User(name, chosenRoles));
    }

    public void deleteChosenRoles(int selectedIndex) {
        chosenRoles.remove(selectedIndex);
    }

    public void addToChosenRoles(int roleIndex) {
        chosenRoles.add(roles.get(roleIndex));
    }

    public void changeUser(String userName, int userIndex) {
        users.get(userIndex).setName(userName);
    }

    public void deleteUser(int userIndex) {
        users.remove(userIndex);
    }

    public void deleteUserRole(int userIndex, int roleIndex) {
        users.get(userIndex).getRoles().remove(roleIndex);
    }
}
