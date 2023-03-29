package presentation;

import dataSourse.TypeModules;
import ui.panel.PanelAnalyticalData;
import ui.panel.PanelAuthorization;

public interface InterfacePresenter {
    void navigatePanelsAuthorToAnalytical();
    PanelAuthorization getPanelAuthorization();
    PanelAnalyticalData getPanelAnalyticalData();
    void repaint();
    void setPanelDetailModule(TypeModules typeModules);
    PanelAnalyticalData getNewPanelAnalyticalData(int widthScreen, int heightScreen);
    void restoreModule();
    void sendDataBaseAuthorization(String password, String login);
    void sendDataBaseRegistration(String password, String login);
    void sendGetDeviceToAnalytical(String name);
}
