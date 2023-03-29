package dataBaseRepository.client;

import dataSourse.Device;
import dataSourse.User;

public interface InterfaceClientMapper {
    User getUser(User user);
    User addUser(User user);
    Device getDevices(String name);
    void addHistory(User user);
}
