package ru.geekbrains.java3.lesson2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ClientHandler {
    private Server server;
    private String nick;
    private Channel channel;
    private Socket socket;

    public ClientHandler(Socket socket, Server server) {
        this.server = server;
        this.socket = socket;

        try {
            channel = ChannelBase.of(socket);
            Server.getExecutorService().execute(() -> {
                auth();
                log.info(nick + " handler waiting for new massages");
                while (socket.isConnected()) {
                    Message msg = channel.getMessage();
                    if (msg == null) continue;
                    switch (msg.getType()) {
                        case EXIT_COMMAND:
                            server.unsubscribe(this);
                            break;
                        case PRIVATE_MESSAGE:
                            sendPrivateMessage(msg.getBody());
                            break;
                        case BROADCAST_CHAT:
                            server.sendBroadcastMessage(nick + " : " + msg.getBody());
                            break;
                        case CHANGE_NICK:
                            Pattern pattern = Pattern.compile("[^\\s]{1,15}");
                            Matcher matcher = pattern.matcher(msg.getBody());
                            if (matcher.find()) {
                                if (server.getAuthService().changeNick(this.nick, matcher.group(0))) {
                                    this.nick = matcher.group(0);
                                    channel.sendMessage("Ваш ник успешно изменён на " +
                                            matcher.group(0) + ".");
                                }else
                                    channel.sendMessage("Не удалось сменит ник. " +
                                            "Возможно такой ник уже занят");
                            }
                            break;
                        default:
                            log.error("invalid message type");
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendPrivateMessage(String messageWithNickTo) {
        int firstSpaceIndex = messageWithNickTo.indexOf(" ");
        final String nickTo = messageWithNickTo.substring(0, firstSpaceIndex);
        final String message = messageWithNickTo.substring(firstSpaceIndex).trim();
        if (server.isNickTaken(nickTo)) {
            server.sendPrivateMessage(nick, nickTo, nick + " -> " + nickTo + " : " + message);
        } else {
            sendMessage(nickTo + " not taken!");
        }
    }

    /**
     * Wait for command: "/auth login1 pass1"
     */
    private void auth() {
        while (true) {
            TimeoutChecker.set(this);
            if (!channel.hasNextLine()) break;
            Message message = channel.getMessage();
            if (MessageType.AUTH_MESSAGE.equals(message.getType())) {
                String[] commands = message.getBody().split(" ");// /auth login1 pass1
                if (commands.length >= 2) {
                    String login = commands[0];
                    String password = commands[1];
                    log.warn("Try to login with " + login + " and " + password);
                    String nick = server.getAuthService()
                            .authByLoginAndPassword(login, password);
                    if (nick == null) {
                        String msg = "Invalid login or password";
                        log.error(msg);
                        channel.sendMessage(msg);
                    } else if (server.isNickTaken(nick)) {
                        String msg = "Nick " + nick + " already taken!";
                        log.warn(msg);
                        channel.sendMessage(msg);
                    } else {
                        this.nick = nick;
                        String msg = "Auth ok!";
                        TimeoutChecker.unset(this);
                        log.info(msg);
                        channel.sendMessage(msg);
                        server.subscribe(this);
                        break;
                    }
                }
            } else {
                channel.sendMessage("Invalid command!");
            }
        }
    }

    public void sendMessage(String msg) {
        channel.sendMessage(msg);
    }

    public String getNick() {
        return nick;
    }

    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
