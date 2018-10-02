package ru.geekbrains.java3.lesson3.chatGui;

import ru.geekbrains.java3.lesson3.net.ClientChat;

public class ChatMain {
    public static void main(String[] args) {
        Messages messages = new Messages();
        ChatWindow chatWindow = new ChatWindow();
        chatWindow.init(messages);
        Sender sender = new Sender(chatWindow, messages);
        ClientChat.init();
        sender.init();
    }
}
