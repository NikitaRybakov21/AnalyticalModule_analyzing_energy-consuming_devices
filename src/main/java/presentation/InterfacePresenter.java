package presentation;

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
}
