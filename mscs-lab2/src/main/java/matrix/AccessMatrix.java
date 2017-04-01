package matrix;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AccessMatrix {
    private int row = 4;
    private int col = 7;

    private List<List<String>> mtx = new LinkedList<List<String>>();

    List<String> chars = null;

    private Scanner scn;

    public AccessMatrix() {
        openFile();
        readFile();
    }

    private void openFile() {
        try {
            scn = new Scanner(new File("src//main/resources/accessMatrix.txt"));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found");
        }
    }

    public void readFile() {
        while (scn.hasNext()) {
            for (int i = 0; i < row; i++) {
                chars = new LinkedList<String>();

                for (int j = 0; j < col; j++) {
                    chars.add(scn.next());
                }

                mtx.add(chars);

            }
        }
    }

    public void writeToFile() {
        File file = new File("src//main/resources/accessMatrixOut.txt");
        StringBuilder string = new StringBuilder();

        try {
            //проверяем, что если файл не существует то создаем его
            if (!file.exists()) {
                file.createNewFile();
            }

            for (List<String> row : mtx) {
                for (String col : row) {
                    string.append(col + " ");
                }
                string.append('\n');
            }

            //PrintWriter обеспечит возможности записи в файл
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                //Записываем текст в файл
                out.print(string);
            } finally {
                //После чего мы должны закрыть файл
                //Иначе файл не запишется
                out.close();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File error");
        }
    }

    public void addUser() {
        row++;
        chars = new LinkedList<String>();

        for (int i = 0; i < col; i++) {
            chars.add("");
        }

        mtx.add(chars);
    }

    public void addCharacter() {
        col++;
        for (int i = 0; i < row; i++) {
            mtx.get(i).add("");
        }
    }

    public void removeCharacter(int col) {

        for (List<String> row : mtx) {
            row.remove(col);
        }
        this.col--;
        writeToFile();
    }

    public void removeUser(int row) {
        mtx.remove(row);
        this.row--;
        writeToFile();
    }

    private void out() {
        for (int j = 0; j < row; j++) {
            System.out.print(mtx.get(j) + " ");
        }

        System.out.println("row : " + row);
        System.out.println("col : " + col);
    }

    public List<List<String>> getMtx() {
        return mtx;
    }

    public void update(int row, int col, String value) {
        this.mtx.get(row).set(col, value);
        writeToFile();
    }
}
