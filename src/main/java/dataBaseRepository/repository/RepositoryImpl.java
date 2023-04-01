package dataBaseRepository.repository;

import dataBaseRepository.client.Client;
import dataBaseRepository.connect.ConnectDataBase;
import dataSourse.*;
import dataSourse.constValue.ResponseStatus;

import java.util.ArrayList;
import static dataSourse.constValue.ResponseStatus.*;

public class RepositoryImpl implements InterfaceRepository {

    private final Client clientMapper = ConnectDataBase.getConnect();
    private User userSaved;

    @Override
    public ResponseStatus sendLogin(User user) {
        User dataBaseUser = clientMapper.getUser(user);

        if(dataBaseUser != null) {
            if (dataBaseUser.login.equals(user.login) && dataBaseUser.password.equals(user.password)) {
                userSaved = dataBaseUser;
                return Login_isSuccessful;
            } else {
                return Login_Error;
            }
        } else {
            return Login_Error;
        }
    }

    @Override
    public ResponseStatus sendAddUser(User user) {
        User newDataBaseUser = clientMapper.addUser(user);

        if(newDataBaseUser != null) {
            return Registration_isSuccessful;
        } else {
            return Registration_Error;
        }
    }

    @Override
    public Device sendGetDevices(String name, ArrayList<DevicesDeath> list) {

        Device device = clientMapper.getDevices(name);

  /*   if (device != null) {
            clientMapper.setDataDeathDevices(list);
            System.out.println("ок set");
        }*/

        clientMapper.addHistory(userSaved);
        return device;
    }

    @Override
    public ArrayList<PowerDevice> getListPowerDevices(String id) {
        return clientMapper.getListPowerDevices(id);
    }

    @Override
    public ArrayList<DevicesDeath> getListSurviveDevices(String id) {
        return clientMapper.getListSurviveDevices(id);
    }

    @Override
    public ProductivityDevices getProductivityDevices(String id) {
        return clientMapper.getProductivityDevices(id);
    }

    @Override
    public PlanningPeriod getPlanningPeriod(String id) { return clientMapper.getPlaningPeriod(id); }
}
