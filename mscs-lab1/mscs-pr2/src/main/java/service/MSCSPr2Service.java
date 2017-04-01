package service;

import file.FileUtils;

import java.io.File;
import java.io.IOException;

public class MSCSPr2Service {

    private static String publicFilePath = "D:\\Trash\\mscs\\mscs-lab1\\src\\public\\data.txt";
    private static String stolenFilePath = "D:\\Trash\\mscs\\mscs-lab1\\mscs-pr2\\src\\main\\resources\\stolen\\data.txt";

    private boolean toggleToFind = false;

    private FileUtils fileUtils = new FileUtils();

    public String toFindAndSteal() {
        File stolenFile = new File(stolenFilePath);
        File publicFile = new File(publicFilePath);

        String status = "";

        toggleToFind = !toggleToFind;

        do{
          /*  try {*/
                if (publicFile.exists()) {
                    try {
                        fileUtils.copy(publicFile, stolenFile);
                        status += "File has been stolen. Press Open to observe. ";
                    } catch (IOException e) {
                        status = "File is not exist. ";
                        e.printStackTrace();
                    }
                }

           /*    *//* Thread.sleep(5000);
                System.out.println("5 seconds");*//*
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        } while (toggleToFind==true);


        return status;
    }

    public static void toOpen() {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("notepad " + stolenFilePath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void setToggleToFind(boolean toggleToFind) {
        this.toggleToFind = toggleToFind;
    }
}
