package com.beanc.util;

import javafx.scene.control.TextArea;

import java.io.*;

public class LogInfoOperator {
    private static final String userHome = System.getProperty("user.home");
    public static String defaultLogPath = userHome + "\\.candice\\log.juan";

    private String path;

    public void readLog(TextArea logTextArea) {
        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String content = null;
            while ((content = bufferedReader.readLine()) != null) {
                logTextArea.appendText(content + "\n");
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeLog(TextArea logTextArea, String content) {
        try {
            File file = new File(path);

            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(content);
            bufferedWriter.newLine();

            logTextArea.appendText(content + "\n");

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LogInfoOperator(String path) {
        this.path = path;
    }
}
