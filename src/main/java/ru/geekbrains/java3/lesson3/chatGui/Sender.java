package ru.geekbrains.java3.lesson3.chatGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sender {
    private  ChatWindow chatWindow;
    private Messages messages;

    public Sender(ChatWindow chatWindow, Messages messages) {
        this.chatWindow = chatWindow;
        this.messages = messages;
    }

    public void init() {
        chatWindow.getSendButton().addActionListener(send());
        chatWindow.getTextField().addActionListener(send());
    }

    private ActionListener send() {
        ActionListener _send = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chatWindow.addMessage();
            }
        };
        return _send;
    }
}
