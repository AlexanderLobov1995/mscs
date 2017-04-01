package starter;

import service.Service;
import ui.Ui;
import ui.UserUi;

import javax.swing.*;

public class Starter {
    private static Ui ui;
    private static UserUi userUi;

    public static void main(String[] args) {
        Service service = new Service();

        ui = new Ui("MSCS-Lab-2-3", service);
        userUi = new UserUi("Users", ui, service);

        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void setVisible() {
        userUi.setVisible(true);
    }
}
