package ru.geekbrains.java3.lesson3.chatGui;

public class Messages {
    private String messages = "";

    public Messages() {
        messages = ChatHistory.loadHistory();
    }

    public String getMessages() {
        return messages;
    }

    public void addMessage(String message) {
        messages += message + "\n";
    }

    public void saveHistory() {
        ChatHistory.saveHistory(messages);
    }
}
