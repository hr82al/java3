package ru.geekbrains.java3.lesson3.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientChat {
    private static PrintWriter out;
    private static Scanner in;
    public static void init() {
        final String ADDR = "localhost";
        final int PORT = 8189;
        try (Socket socket = new Socket(ADDR, PORT)){
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
            Scanner input = new Scanner(System.in);

            new Chat(in, input, out, "Client").run();
        } catch (IOException e) {
            System.out.println("Произошла ошибка.");
        }
    }
}
