package presentation;

import dataBaseRepository.repository.RepositoryImpl;
import dataSourse.*;
import geniratorRes.GenRes;
import ui.main.MainApp;
import ui.panel.PanelAnalyticalData;
import ui.panel.PanelAuthorization;
import ui.panel.panelDetailsSlider.PanelDefault;
import ui.panel.panelDetailsSlider.PanelEffectivenessModule;
import ui.panel.panelDetailsSlider.PanelLifeCycleModule;
import ui.panel.panelDetailsSlider.PanelTechnicalSpecificationsModule;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Presenter implements InterfacePresenter {

    private final RepositoryImpl repository = new RepositoryImpl();

    private final PanelAuthorization panelAuthorization = new PanelAuthorization(this);
    private PanelAnalyticalData panelAnalyticalData = new PanelAnalyticalData(this,MainApp.sizeX,MainApp.sizeY);

    private final MainApp mainApp;
    public JPanel panelCurrent;

    private final ExecutorService service = Executors.newCachedThreadPool();

    public Presenter(MainApp mainApp) { this.mainApp = mainApp; }

    @Override
    public PanelAuthorization getPanelAuthorization() { return panelAuthorization; }
    @Override
    public PanelAnalyticalData getPanelAnalyticalData() { return panelAnalyticalData; }

    @Override
    public void navigatePanelsAuthorToAnalytical() {
        mainApp.navigation(panelAuthorization.getPanel(),panelAnalyticalData.getPanel(),this);
    }

    private JPanel panelModule = null;

    @Override
    public void setPanelDetailModule(TypeModules typeModules) {
        switch (typeModules) {
            case MODULES_Effectiveness -> panelModule = new PanelEffectivenessModule(this,listPowerSave).getPanel();
            case MODULES_TechnicalSpecifications -> panelModule = new PanelTechnicalSpecificationsModule(this, listSurvivalDevices).getPanel();
            case MODULES_LifeCycleModule -> panelModule = new PanelLifeCycleModule(this).getPanel();
            case MODULES_Default -> panelModule = new PanelDefault(this).getPanel();
        }

        panelAnalyticalData.setPanelDetails(panelModule);
        repaint();
    }

    @Override
    public void restoreModule() {
        if(panelModule != null) {
            panelAnalyticalData.setPanelDetails(panelModule);
            repaint();
        }
    }

    @Override
    public void repaint() {
        mainApp.repaintFrame();
    }

    @Override
    public PanelAnalyticalData getNewPanelAnalyticalData(int widthScreen, int heightScreen) {
        PanelAnalyticalData panel = new PanelAnalyticalData(this,widthScreen,heightScreen);
        panelAnalyticalData = panel;
        restoreModule();

        return panel;
    }

    @Override
    public void sendDataBaseAuthorization(String password, String login) {
        service.submit(() -> {
            ResponseStatus responseStatus = repository.sendLogin(new User(login, password));

            switch (responseStatus) {
                case Login_isSuccessful -> SwingUtilities.invokeLater(panelAuthorization::loginSuccessful);
                case Login_Error -> SwingUtilities.invokeLater(panelAuthorization::loginError);
            }
        });
    }

  /*  public Device device = new Device("dd","ddd","ddd");
    private ArrayList<PowerDevice> listPowerSave  = GenRes.getArrayListPowerDevices() ;
    private ArrayList<DevicesDeath> listSurvivalDevices  = GenRes.getArrayListSurvivalDevices() ;*/

    public Device device = null;
    public ProductivityDevices productivityDevices;
    private ArrayList<PowerDevice> listPowerSave;
    private ArrayList<DevicesDeath> listSurvivalDevices;

    @Override
    public void sendGetDeviceToAnalytical(String name) {
        service.submit(() -> {
            device = repository.sendGetDevices(name,listSurvivalDevices);

            if (device != null) {
                listPowerSave = repository.getListPowerDevices(device.id);
                listSurvivalDevices = repository.getListSurviveDevices(device.id);
                productivityDevices = repository.getProductivityDevices(device.id);

                SwingUtilities.invokeLater(panelAnalyticalData::devicesSuccessful);
            } else {
                SwingUtilities.invokeLater(panelAnalyticalData::nullDevices);
            }
        });
    }

    @Override
    public void sendDataBaseRegistration(String password, String login) {
        service.submit(() -> {
            ResponseStatus responseStatus = repository.sendAddUser(new User(login, password));

            switch (responseStatus) {
                case Registration_isSuccessful -> SwingUtilities.invokeLater(panelAuthorization::registrationSuccessful);
                case Registration_Error -> SwingUtilities.invokeLater(panelAuthorization::registrationError);
            }
        });
    }
}
