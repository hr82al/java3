package ru.geekbrains.java3.lesson3.chatGui;

public class ChatMain {
    public static void main(String[] args) {
        Messages messages = new Messages();
        ChatWindow chatWindow = new ChatWindow();
        chatWindow.init(messages);
        Sender sender = new Sender(chatWindow, messages);
        sender.init();

    }
}
