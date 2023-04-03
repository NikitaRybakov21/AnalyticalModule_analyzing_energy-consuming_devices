package ui.panel.panelDetailsSlider;

import dataSourse.PlanningPeriod;
import dataSourse.PointF;
import mathematicalModels.InventoryManagementModels;
import presentation.Presenter;
import ui.componentsView.CallBackFun;
import ui.componentsView.FunColorX;
import ui.componentsView.Graph;
import ui.main.MainApp;
import ui.panel.interfacesPanel.InterfacePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.Math.sin;

public class PanelLifeCycleModule implements ActionListener  , InterfacePanel {

    private final JPanel panelLifeCycleModule = MainApp.getStylePanel();

    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraints =  new GridBagConstraints();

    // 0.1f*365f,1,0.8f,365

    public PanelLifeCycleModule(Presenter presenter, PlanningPeriod planningPeriod) {
        panelLifeCycleModule.setBackground(MainApp.getRGBColor(254,254,254));
        panelLifeCycleModule.setLayout(gridBagLayout);

        InventoryManagementModels inventoryMM;
        if(planningPeriod != null) {
            inventoryMM = new InventoryManagementModels(planningPeriod.D, planningPeriod.K, planningPeriod.H, planningPeriod.T);
        } else {
            inventoryMM  = new InventoryManagementModels(0,0,0, 0);
        }

        createHeader(presenter);
        createGraphUi1(inventoryMM);
        createDescriptionGraphUi1(inventoryMM);
        createGraphUi2(inventoryMM);
        createDescriptionGraphUi2();
    }

    private void createGraphUi1(InventoryManagementModels inventoryMM) {
        ArrayList<FunColorX> functions = new ArrayList<>();
        functions.add(new FunColorX(inventoryMM.getFunctionOrderCosts() ,MainApp.getRGBColor(185,185,255)));
        functions.add(new FunColorX(inventoryMM.getFunctionStorageCost() ,MainApp.getRGBColor(255,185,185)));
        functions.add(new FunColorX(inventoryMM.getFunctionSumCost(),MainApp.getRGBColor(255, 50, 50)));

        ArrayList<PointF> listPoint = new ArrayList<>();
        for (float i = 0; i < 300; i += 12f) {
            listPoint.add(new PointF(inventoryMM.getOptimalSizeOrder(),i));
        }

        Graph graph = new Graph(functions,listPoint,5,50f,50,44,0,100,new Dimension(800,350));

        addComponent(0,3, graph,new Insets(10, 0, 0,0),2,1,0,GridBagConstraints.WEST);
    }

    private void createDescriptionGraphUi1(InventoryManagementModels inventoryMM) {
        JLabel nameGraph = new JLabel(getDescriptionGraphUi1());
        nameGraph.setFont(new Font("Verdana", Font.PLAIN, 18));
        addComponent(0,2, nameGraph,new Insets(20, 0, 0,0),1,1,0,GridBagConstraints.WEST);

        JLabel nameGraph2 = new JLabel(getDescriptionGraphUi1Part2(inventoryMM));
        nameGraph2.setFont(new Font("Verdana", Font.PLAIN, 17));
        addComponent(1,2, nameGraph2,new Insets(0, 0, 0,0),1,1,0,GridBagConstraints.SOUTH);
    }

    private void createGraphUi2(InventoryManagementModels inventoryMM) {
        ArrayList<FunColorX> functions = new ArrayList<>();
        functions.add(new FunColorX(inventoryMM.getFunctionInventory() ,MainApp.getRGBColor(255,100,100)));

        Graph graph = new Graph(functions,null,30,5f,50,44,0,365,new Dimension(700,350));
        addComponent(2,3, graph,new Insets(10, 50, 0,0),1,1,0,GridBagConstraints.WEST);
    }

    private void createDescriptionGraphUi2() {
        JLabel nameGraph = new JLabel(getDescriptionGraphUi2());
        nameGraph.setFont(new Font("Verdana", Font.PLAIN, 18));
        addComponent(2,2, nameGraph,new Insets(60, 50, 0,0),1,1,0,GridBagConstraints.WEST);
    }

    private void createHeader(Presenter presenter) {
        String nameDevices = "";
        if(presenter.device != null) {
            nameDevices = presenter.device.name;
        }

        JLabel label = new JLabel("Управление запасами оборудования");
        JLabel labelNameDevices = new JLabel("<html>оборудование: <font color='green'>" + nameDevices + "</font></html>");
        labelNameDevices.setFont(new Font("Verdana", Font.BOLD, 14));
        label.setFont(new Font("Verdana", Font.PLAIN, 36));

        addComponent(0,0, label,new Insets(0, 0, 0,0),3,1,0,GridBagConstraints.CENTER);
        addComponent(0,1, labelNameDevices,new Insets(15, 0, 0,0),3,1,0,GridBagConstraints.CENTER);
    }

    private void addComponent(int gridX, int gridY, Component component, Insets insets, int gridWidth,int gridHeight, int height, int fill) {
        constraints.ipady = height;
        constraints.gridwidth = gridWidth;
        constraints.gridheight = gridHeight;
        constraints.fill = fill;
        constraints.anchor = fill;

        if(insets != null) {
            constraints.insets = insets;
        }
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        gridBagLayout.setConstraints(component, constraints);

        panelLifeCycleModule.add(component);
    }

    private String getDescriptionGraphUi1() {
        return  "<html>Модель управления запасами.<br>" +
                "Методика минимизация совокупных издержек.<br><br>" +

                "<font color='#708090'>Ось X -размер заказа в: </font> тонах<br>" +
                "<font color='#708090'> Ось Y -издержки: </font> тысяч рублей <br>" +
                "<font color='red'>---</font><font color='#708090'> функция совокупных издержек: </font> C(q) <br>"+
                "<font color='#ff8b6b'>---</font><font color='#708090'> функция издержек хранения: </font> с1(q) <br>"+
                "<font color='#ab94ff'>---</font><font color='#708090'> функция издержек заказа: </font> с2(q) <br>";
    }

    private String getDescriptionGraphUi1Part2(InventoryManagementModels inventoryMM) {
        return  "<html>" +
                "<font color='#708090'>Период планирования</font> "+inventoryMM.getT()+" дней<br><br>" +

                "<font color='#708090'>Оптимальный размер заказа</font> Q = "+String.format("%.3f", inventoryMM.getOptimalSizeOrder())+" тонн<br>" +
                "<font color='#708090'>Оптимальное число заказов</font> N = "+inventoryMM.getOptimalQuantityOrder()+" <br>" +
                "<font color='#708090'>Время циклов заказов</font> T = "+inventoryMM.getOptimalPeriodOrder()+" дней <br>";
    }

    private String getDescriptionGraphUi2() {
        return  "<html>Динамика изменения количества продукта Q на складе.<p><p>" +

                "График количества оборудования на складе -<p>" +
                "построеный по методу минимальных совокупных издержек.<p><p>" +
                "<font color='#708090'>Ось X -время в:</font> днях <p>" +
                "<font color='#708090'>Ось Y -количество оборудование на складе в: </font> тонах";
    }

    @Override
    public JPanel getPanel() { return panelLifeCycleModule; }

    @Override
    public void actionPerformed(ActionEvent event) { }
}
