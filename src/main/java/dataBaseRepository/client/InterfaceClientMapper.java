package dataBaseRepository.client;

import dataSourse.Device;
import dataSourse.DevicesDeath;
import dataSourse.PowerDevice;
import dataSourse.User;

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
}
