package dataBaseRepository.client;

import dataSourse.Device;
import dataSourse.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ClientMapper implements InterfaceClientMapper{

    Connection connection;

    public ClientMapper(Connection connection){
        this.connection = connection;
    }

    @Override
    public User getUser(User user) {
        String query =  "SELECT * FROM Users WHERE  name = '"+user.login+"'";
        User dataBaseUser = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                System.out.println(rs.getRow() + "  select user  " + rs.getString("name") + "\t");
                dataBaseUser = new User(rs.getString("name"),rs.getString("password"));
            } else {
                System.out.println("select user NULL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataBaseUser;
    }

    @Override
    public User addUser(User user) {
        String query =  "INSERT INTO Users (name,password) VALUES ('"+user.login+"','"+user.password+"');";
        User newDataBaseUser = null;

        try {
            if(getUser(user) == null) {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                int rs = preparedStatement.executeUpdate();

                newDataBaseUser = user;

                System.out.println("select user added");
            } else {
                System.out.println("select user already exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newDataBaseUser;
    }

    @Override
    public Device getDevices(String name) {
        String query =  "SELECT * FROM Devices WHERE  name = '"+name+"'";
        Device deviceDB = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                System.out.println(rs.getRow() + "  device  " + rs.getString("name") +"  "+ rs.getString("iddevices") + "\t");
                deviceDB = new Device(rs.getString("name"),rs.getString("iddevices"));
            } else {
                System.out.println("device NULL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deviceDB;
    }

    @Override
    public void addHistory(User user) {
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String query =  "INSERT INTO History (username,date) VALUES ('"+user.login+"','"+date+"');";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int rs = preparedStatement.executeUpdate();

            System.out.println("user added send history");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
