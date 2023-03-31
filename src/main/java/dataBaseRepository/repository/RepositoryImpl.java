package dataBaseRepository.repository;

import dataBaseRepository.client.ClientMapper;
import dataBaseRepository.connect.ConnectDataBase;
import dataBaseRepository.interfaces.Repository;
import dataSourse.*;
import geniratorRes.GenRes;

import java.util.ArrayList;

import static dataSourse.ResponseStatus.*;

public class RepositoryImpl implements Repository {

    private final ClientMapper clientMapper = ConnectDataBase.getConnect();
    private User userSaved;

    @Override
    public ResponseStatus sendLogin(User user) {
        User dataBaseUser = clientMapper.getUser(user);

        if(dataBaseUser != null) {
            if (dataBaseUser.login.equals(user.login) && dataBaseUser.password.equals(user.password)) {

                System.out.println("Login!!!");
                userSaved = dataBaseUser;
                return Login_isSuccessful;
            } else {

                System.out.println("ERROR!!!");
                return Login_Error;
            }
        } else {

            System.out.println("ERROR!!!");
            return Login_Error;
        }
    }

    @Override
    public ResponseStatus sendAddUser(User user) {
        User newDataBaseUser = clientMapper.addUser(user);

        if(newDataBaseUser != null) {

            System.out.println("Registration successful");
            return Registration_isSuccessful;
        } else {

            System.out.println("Registration error");
            return Registration_Error;
        }
    }

    @Override
    public Device sendGetDevices(String name, ArrayList<DevicesDeath> list) {

        Device device = clientMapper.getDevices(name);

  /*      if (device != null) {
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
}
