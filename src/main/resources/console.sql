CREATE TABLE IF NOT EXISTS users(UserID  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, login TEXT NOT NULL, pass TEXT NOT NULL, nick TEXT NOT NULL);
INSERT INTO users (login, pass, nick) VALUES("login1", "pass1", "nick1");
INSERT INTO users (login, pass, nick) VALUES("login2", "pass2", "nick2");
INSERT INTO users (login, pass, nick) VALUES("login3", "pass3", "nick3");
