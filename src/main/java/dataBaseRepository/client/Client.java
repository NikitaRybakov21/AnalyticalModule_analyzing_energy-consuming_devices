package dataBaseRepository.client;

import dataSourse.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Client implements InterfaceClient {

    Connection connection;

    public Client(Connection connection){
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
                dataBaseUser = new User(rs.getString("name"),rs.getString("password"));
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
                deviceDB = new Device(rs.getString("name"),rs.getString("iddevices"),rs.getString("power"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deviceDB;
    }

    @Override
    public void setItemDevices(Device device) {
        String query = "INSERT INTO devices (iddevices,name,power) VALUES ('" + device.id + "','" + device.name + "','"+ device.power + "');";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int rs = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addHistory(User user) {
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String query =  "INSERT INTO History (username,date) VALUES ('"+user.login+"','"+date+"');";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int rs = preparedStatement.executeUpdate();

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

    @Override
    public ProductivityDevices getProductivityDevices(String id) {
        String query =  "SELECT * FROM productivity_devices WHERE iddevices = '"+id+"'";
        ProductivityDevices productivityDevices = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                productivityDevices = new ProductivityDevices(
                       Integer.parseInt(rs.getString("iddevices")),
                       Integer.parseInt(rs.getString("period_work")),
                       Integer.parseInt(rs.getString("productivityfirst")),
                       Integer.parseInt(rs.getString("productivitylast"))
               );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productivityDevices;
    }

    @Override
    public void setItemProductivityDevices(ProductivityDevices device) {
        String query = "INSERT INTO productivity_devices (iddevices,period_work,productivityfirst,productivitylast) VALUES ('" + device.id + "','" + device.periodWork + "','"+ device.productivityFirst + "','"+ device.productivityLast + "');";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int rs = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PlanningPeriod getPlaningPeriod(String id) {
        String query =  "SELECT * FROM planning_period WHERE iddevices_model = '"+id+"'";
        PlanningPeriod planningPeriod = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                planningPeriod = new PlanningPeriod(
                        Integer.parseInt(rs.getString("iddevices_model")),
                        Float.parseFloat(rs.getString("requirement_periodD")),
                        Float.parseFloat(rs.getString("order_costsK")),
                        Float.parseFloat(rs.getString("storage_costH")),
                        Integer.parseInt(rs.getString("planning_periodT"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planningPeriod;
    }

    @Override
    public void setItemPlaningPeriod(PlanningPeriod device) {
        String query = "INSERT INTO planning_period (iddevices_model,requirement_periodD,order_costsK,storage_costH,planning_periodT) " +
                "VALUES ('" + device.id + "','" + device.D + "','"+ device.K + "','"+ device.H + "','"+ device.T + "');";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int rs = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
