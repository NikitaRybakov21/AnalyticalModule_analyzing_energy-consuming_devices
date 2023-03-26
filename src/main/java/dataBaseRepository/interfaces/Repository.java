package dataBaseRepository.interfaces;

import dataSourse.ResponseStatus;
import dataSourse.User;

public interface Repository {
    ResponseStatus sendLogin(User user);
    ResponseStatus sendAddUser(User user);
}
