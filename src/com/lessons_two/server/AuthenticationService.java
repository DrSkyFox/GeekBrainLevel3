package com.lessons_two.server;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.lessons_two.server.interfaces.AuthInterface;


public class AuthenticationService {



    public Client logIN(String login, String pass) {
        Connection connection = null;
        try {
            connection = connection = ConnectionFactory.getInstance();
            PreparedStatement statement = connection.prepareStatement("select login, password, nickname from users as u where login = ? and password = ?");
            statement.setString(1,login);
            statement.setString(2,pass);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                System.out.println(resultSet.getString(1));
                return new Client(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong during DB-query");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }


    public String changeNickName(ClientHandler client ,String newName) {
        Connection connection = null;
        try {
            connection = connection = ConnectionFactory.getInstance();

            PreparedStatement statement = connection.prepareStatement("update users set nickname = ? where login = ?");
            statement.setString(1, newName);
            statement.setString(2, client.getName());
            statement.executeUpdate();

            return client.getName();
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong during DB-query");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }


    public boolean regNew(String clogin, String cpass, String cnickname) {
        Connection connection = null;
        try {
            connection = connection = ConnectionFactory.getInstance();
            PreparedStatement statement = connection.prepareStatement("insert into users (login, password, nickname) values (clogin, cpass, cnickname");
            statement.setString(1,clogin);
            statement.setString(2,cpass);
            statement.setString(3,cnickname);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong during DB-query");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public AuthenticationService() {

    }


    static public class Client {
        private String login;
        private String password;
        private String name;

        public Client(String login, String password, String name) {
            this.login = login;
            this.password = password;
            this.name = name;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }
    }
}
