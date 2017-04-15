package service;

import file.FileUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Service {
    private Map<String, String> accessList;

    public Service() {
        accessList = new LinkedHashMap<String, String>();
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

    public void create(String dirName, String dirPath, String currentAccessCreate){
        accessList.put(dirPath + correctChar(dirPath) + dirName, currentAccessCreate);

        File file = new File(dirPath+"\\"+dirName);

        if (!file.exists()) {
            file.mkdir();
        }
    }

    private String correctChar(String dirPath) {
        return (dirPath.charAt(dirPath.length() - 1) == '\\') ? "" : "\\";
    }
}
