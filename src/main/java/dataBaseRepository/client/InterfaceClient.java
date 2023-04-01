package dataBaseRepository.client;

import dataSourse.*;

import java.util.ArrayList;

public interface InterfaceClient {
    User getUser(User user);
    User addUser(User user);
    Device getDevices(String name);
    void addHistory(User user);
    void setDataPowerDevices(ArrayList<PowerDevice> listPower);
    ArrayList<PowerDevice> getListPowerDevices(String id);
    void setDataDeathDevices(ArrayList<DevicesDeath> listDevicesDeath);
    ArrayList<DevicesDeath> getListSurviveDevices(String id);
    ProductivityDevices getProductivityDevices(String id);
    PlanningPeriod getPlaningPeriod(String id);
}
