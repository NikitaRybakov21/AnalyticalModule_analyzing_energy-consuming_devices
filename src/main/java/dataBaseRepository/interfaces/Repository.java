package dataBaseRepository.interfaces;

import dataSourse.*;

import java.util.ArrayList;
import java.util.List;

public interface Repository {
    ResponseStatus sendLogin(User user);
    ResponseStatus sendAddUser(User user);
    Device sendGetDevices(String name, ArrayList<DevicesDeath> list);
    List<PowerDevice> getListPowerDevices(String id);
    ArrayList<DevicesDeath> getListSurviveDevices(String id);
    ProductivityDevices getProductivityDevices(String id);
}
