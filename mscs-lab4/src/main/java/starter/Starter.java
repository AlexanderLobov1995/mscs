package starter;


import service.Service;
import ui.Ui;

public class Starter {
    public static void main(String[] args) {

        Service service = new Service();
        Ui ui = new Ui("MSCS-Lab-4", service);

    }
}
