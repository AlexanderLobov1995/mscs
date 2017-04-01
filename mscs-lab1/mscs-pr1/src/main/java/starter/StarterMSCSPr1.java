package starter;

import ui.MSCSPr1Ui;
import javax.swing.*;

public class StarterMSCSPr1 {
    public static void main(String[] args) {
        MSCSPr1Ui ui = new MSCSPr1Ui("MSCS-Pr-1");
        ui.setVisible(true);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.setLocationRelativeTo(null);
        ui.setSize(660, 300);
        ui.setResizable(false);
    }
}
