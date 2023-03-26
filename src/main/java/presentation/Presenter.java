package presentation;

import ui.main.MainApp;
import ui.panel.PanelAnalyticalData;
import ui.panel.PanelAuthorization;
import ui.panel.panelDetailsSlider.PanelEffectivenessModule;
import ui.panel.panelDetailsSlider.PanelLifeCycleModule;
import ui.panel.panelDetailsSlider.PanelTechnicalSpecificationsModule;

import javax.swing.*;


public class Presenter implements InterfacePresenter {

    private final PanelAuthorization panelAuthorization = new PanelAuthorization(this);
    private PanelAnalyticalData panelAnalyticalData = new PanelAnalyticalData(this,MainApp.sizeX,MainApp.sizeY);

    private final MainApp mainApp;
    public JPanel panelCurrent;

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
            case MODULES_Effectiveness -> panelModule = new PanelEffectivenessModule(this).getPanel();
            case MODULES_TechnicalSpecifications -> panelModule = new PanelTechnicalSpecificationsModule(this).getPanel();
            case MODULES_LifeCycleModule -> panelModule = new PanelLifeCycleModule(this).getPanel();
            default -> panelModule = null;
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

}
