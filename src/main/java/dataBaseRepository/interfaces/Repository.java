package dataBaseRepository.interfaces;

import dataSourse.Device;
import dataSourse.PowerDevice;
import dataSourse.ResponseStatus;
import dataSourse.User;

import java.util.ArrayList;
import java.util.List;

public interface Repository {
    ResponseStatus sendLogin(User user);
    ResponseStatus sendAddUser(User user);
    Device sendGetDevices(String name);
    List<PowerDevice> getListPowerDevices(String id);
}
