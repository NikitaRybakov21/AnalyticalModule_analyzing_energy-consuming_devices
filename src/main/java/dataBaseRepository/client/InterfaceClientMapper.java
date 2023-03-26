package dataBaseRepository.client;

import dataSourse.User;

public interface InterfaceClientMapper {
    User getUser(User user);
    User addUser(User user);
}
