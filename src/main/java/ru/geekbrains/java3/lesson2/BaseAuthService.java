package ru.geekbrains.java3.lesson2;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BaseAuthService implements AuthService{

    private Map<String, User> users = new HashMap<>();
    private static Connection conn = null;

    static {
        final String DB_PATH = ClassLoader.getSystemClassLoader().getResource("base.db").getFile();
        try {
            conn = DriverManager.getConnection(JDBC.PREFIX + DB_PATH);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public BaseAuthService() {
        users.put("nick1", new User("login1", "pass1", "nick1"));
        users.put("nick2", new User("login2", "pass2", "nick2"));
        users.put("nick3", new User("login3", "pass3", "nick3"));
    }

    @Override
    public String authByLoginAndPassword(String login, String password) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT nick FROM users WHERE login like '" + login +
                    "' AND pass LIKE '" + password + "';");
            if (resultSet.next()) {
                int nick = resultSet.findColumn("nick");
                String sNick = resultSet.getString(nick);
                return sNick;
            } else
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User createOrActivateUser(String login, String password, String nick) {
        User user = new User(login, password, nick);
        if (users.containsKey(nick)) {
            users.get(nick).setActive(true);
            System.out.println("User with nick " + nick + "already exist");
        } else {
            users.put(nick, user);
            persist();
        }
        return user;
    }

    private void persist() {
        //do some logic...
//        new File("users.txt");
    }

    @Override
    public boolean deactivateUser(String nick) {
        User user = users.get(nick);
        if (user != null) {
            user.setActive(false);
            return true;
        }
        return false;
    }

    @Override
    public boolean changeNick(String currentNick, String newNick) {
        if (isNickFree(newNick)) {
            try {
                Statement stmt = conn.createStatement();
                int result = stmt.executeUpdate("UPDATE users SET nick = '" + newNick + "' " +
                        "WHERE nick LIKE '" + currentNick + "';");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        } else
            return false;
    }

    private boolean isNickFree(String newNick) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM users WHERE nick like '" + newNick + "';");
            if (resultSet.next()) {
                return false;
            } else
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}
