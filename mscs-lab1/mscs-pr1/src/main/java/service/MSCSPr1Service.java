package service;

import file.FileUtils;
import java.io.*;

public class MSCSPr1Service {
    private static String publicFilePath = "D:\\Trash\\mscs\\mscs-lab1\\src\\public\\data.txt";
    private static String privateFilePath = "D:\\Trash\\mscs\\mscs-lab1\\mscs-pr1\\src\\main\\resources\\private\\data.txt";

    File privateFile = new File(privateFilePath);
    File publicFile = new File(publicFilePath);

    private FileUtils fileUtils = new FileUtils();

    public String toFile(String text) {
        String status = "";

        try {
            //проверяем, что если файл не существует то создаем его
            if (!privateFile.exists()) {
                privateFile.createNewFile();
            }

            //PrintWriter обеспечит возможности записи в файл
            PrintWriter out = new PrintWriter(privateFile.getAbsoluteFile());

            try {
                //Записываем текст в файл
                out.print(text);
                status += "Text has been written to file (at  private package). ";
            } finally {
                //После чего мы должны закрыть файл
                //Иначе файл не запишется
                out.close();
            }

        } catch (IOException e) {
            status = "Error at manipulating the file. ";
        }

        return status;
    }

    public String copy(){
        String status="";
        try {
            fileUtils.copy(privateFile, publicFile);
            status += "File has been copied to the public package. ";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }

}
