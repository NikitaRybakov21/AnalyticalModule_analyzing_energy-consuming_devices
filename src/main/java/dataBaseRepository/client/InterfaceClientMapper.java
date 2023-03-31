package dataBaseRepository.client;

import dataSourse.*;

import java.util.ArrayList;

public interface InterfaceClientMapper {
    User getUser(User user);
    User addUser(User user);
    Device getDevices(String name);
    void addHistory(User user);
    void setDataPowerDevices(ArrayList<PowerDevice> listPower);
    ArrayList<PowerDevice> getListPowerDevices(String id);
    void setDataDeathDevices(ArrayList<DevicesDeath> listDevicesDeath);
    ArrayList<DevicesDeath> getListSurviveDevices(String id);
    ProductivityDevices getProductivityDevices(String id);
}
