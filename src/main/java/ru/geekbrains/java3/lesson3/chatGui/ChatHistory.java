package ru.geekbrains.java3.lesson3.chatGui;

import java.io.*;
import java.util.Scanner;

public class ChatHistory {
    private final static String FILE_NAME = "history.txt";
    private final static int MAX_MESSAGES = 100;
    private final static File file = new File(FILE_NAME);

    public static String loadHistory() {
        System.out.println("init chat history");//FIXME
        StringBuilder str = new StringBuilder();
        if (file.exists()) {
            try {
                Scanner sc = new Scanner(new FileInputStream(FILE_NAME));
                while (sc.hasNextLine()) {
                    str.append(sc.nextLine() + "\n");
                }
                sc.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return str.toString();
        } else
            return "";
    }

    public static void saveHistory(String messages) {
        System.out.println("saving messages");//FIXME
            Scanner sc = new Scanner(new BufferedReader(new StringReader(messages)));
            int counter = 0;

            while (sc.hasNextLine()) {
                sc.nextLine();
                counter++;
            }
            sc = new Scanner(new BufferedReader(new StringReader(messages)));
            if (counter > MAX_MESSAGES) {
                int skip = counter - MAX_MESSAGES;
                while (skip > 0 && sc.hasNextLine()) {
                    sc.nextLine();
                    skip--;
                }
            }
                try (PrintWriter pw = new PrintWriter(FILE_NAME)) {
                    while (sc.hasNextLine()) {
                        pw.println(sc.nextLine());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

    }
}
