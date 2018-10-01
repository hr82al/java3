package ru.geekbrains.java3.lesson2;

public interface AuthService {

    String authByLoginAndPassword(String login, String password);

    User createOrActivateUser(String login, String password, String nick);

    boolean deactivateUser(String nick);

    boolean changeNick(String currentNick, String newNick);
}
