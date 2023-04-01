package dataBaseRepository.connect;

import dataBaseRepository.client.Client;

import java.sql.*;

public class ConnectDataBase {

    private static final String DATA_BASE_NAME = "dataBaseAnalyticsModule";
    private static final String HOST = "localhost";
    private static final String PORT = "5432";

    private static final String user = "postgres";
    private static final String password = "2020";

    private static Connection connection;

    public static Client getConnect() {
        Client clientMapper = null;

        try {
            String url = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATA_BASE_NAME;

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to the " + DATA_BASE_NAME + " database is successful");

            clientMapper = new Client(connection);

        } catch (SQLException e) {
            e.printStackTrace();

            try { connection.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }

            System.out.println("Connection to the " + DATA_BASE_NAME + " database - ERROR");
        }
        return clientMapper;
    }
}


