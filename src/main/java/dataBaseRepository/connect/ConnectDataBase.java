package dataBaseRepository.connect;

import java.sql.*;

public class ConnectDataBase {

    private static final String DATA_BASE_NAME = "postgres";
    private static final String HOST = "localhost";
    private static final String PORT = "5432";

    private static final String user = "postgres";
    private static final String password = "2020";

    private static Connection connection;

    public static void connect() {

        try {
            String url = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATA_BASE_NAME;

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to the " + DATA_BASE_NAME + " database is successful");

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM \"Users\"";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getRow() + "    " + rs.getString("password") + "\t");
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { connection.close(); } catch(SQLException ignored) {  }
        }
    }
}
