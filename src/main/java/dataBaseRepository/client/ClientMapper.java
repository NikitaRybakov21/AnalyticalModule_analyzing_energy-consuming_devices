package dataBaseRepository.client;

import dataSourse.Device;
import dataSourse.DevicesDeath;
import dataSourse.PowerDevice;
import dataSourse.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
                deviceDB = new Device(rs.getString("name"),rs.getString("iddevices"),rs.getString("power"));
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

    @Override
    public void setDataPowerDevices(ArrayList<PowerDevice> listPower) {
        for (PowerDevice powerDevice : listPower) {
            String query = "INSERT INTO power_devices (iddevices,working_time,input_power,effect_power) VALUES ('" + powerDevice.id + "','" + powerDevice.time + "','" + powerDevice.inputPower + "','" + powerDevice.effectivePower + "');";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                int rs = preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<PowerDevice> getListPowerDevices(String id) {
        ArrayList<PowerDevice> listPowerDevices = new ArrayList<>();
        String query =  "SELECT * FROM power_devices WHERE iddevices = '"+id+"'";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                listPowerDevices.add(new PowerDevice(
                        Integer.parseInt(rs.getString("iddevices")),
                        Float.parseFloat(rs.getString("working_time")),
                        Float.parseFloat(rs.getString("input_power")),
                        Float.parseFloat(rs.getString("effect_power"))
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPowerDevices;
    }

    @Override
    public void setDataDeathDevices(ArrayList<DevicesDeath> listDevicesDeath) {
        for (DevicesDeath deathTime : listDevicesDeath) {
            String query = "INSERT INTO survive_devices (iddevices_model,timedeath) VALUES ('" + deathTime.id + "','" + deathTime.timeDeath + "');";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                int rs = preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<DevicesDeath> getListSurviveDevices(String id) {
        ArrayList<DevicesDeath> listSurviveDevices = new ArrayList<>();
        String query =  "SELECT * FROM survive_devices WHERE iddevices_model = '"+id+"'";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                listSurviveDevices.add(new DevicesDeath(
                        Integer.parseInt(rs.getString("iddevices_model")),
                        rs.getString("timedeath")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSurviveDevices;
    }
}
