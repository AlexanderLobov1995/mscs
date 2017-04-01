package starter;

import ui.MSCSPr2Ui;
import javax.swing.*;

public class StarterMSCSPr2 {
    public static void main(String[] args) {
        MSCSPr2Ui ui = new MSCSPr2Ui("MSCS-Pr-2");
        ui.setVisible(true);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.setLocationRelativeTo(null);
        ui.setSize(660, 300);
        ui.setResizable(false);
    }
}
