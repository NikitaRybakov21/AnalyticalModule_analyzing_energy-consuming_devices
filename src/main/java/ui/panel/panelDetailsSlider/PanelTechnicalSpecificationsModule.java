package ui.panel.panelDetailsSlider;

import dataSourse.DevicesDeath;
import dataSourse.PointF;
import dataSourse.PowerDevice;
import dataSourse.ProductivityDevices;
import mathematicalModels.*;
import presentation.Presenter;
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

public class PanelTechnicalSpecificationsModule implements ActionListener , InterfacePanel {

    private final JPanel panelTechnicalSpecificationsModule = MainApp.getStylePanel();

    private final GridBagLayout gridBagLayout = new GridBagLayout();

    private final GridBagConstraints constraints =  new GridBagConstraints();

    public PanelTechnicalSpecificationsModule(Presenter presenter, ArrayList<DevicesDeath> listDeath) {
        panelTechnicalSpecificationsModule.setBackground(MainApp.getRGBColor(254,254,254));
        panelTechnicalSpecificationsModule.setLayout(gridBagLayout);

        KaplanMeierEstimator kaplanMeierEstimator = new KaplanMeierEstimator(listDeath);

        createHeader(presenter);
        createGraphUi(kaplanMeierEstimator);
        createDescriptionGraphUi(kaplanMeierEstimator);
        createLabelProductivityDevices(presenter);
    }

    private void createLabelProductivityDevices(Presenter presenter) {
        String label = "";
        if(presenter.productivityDevices != null) {
           label = DegreeOfWear.getStringProductivityDevices(presenter.productivityDevices);
        }

        JLabel labelPowerInput = new JLabel(label);
        labelPowerInput.setFont(new Font("Verdana", Font.PLAIN, 24));

        addComponent(0,2, labelPowerInput,new Insets(60, 0, 0,0),1,2,0,GridBagConstraints.HORIZONTAL);
    }

    private void createGraphUi(KaplanMeierEstimator kaplanMeierEstimator) {
        ArrayList<FunColorX> functions = new ArrayList<>();
        functions.add(new FunColorX(x -> kaplanMeierEstimator.funSurviveKaplanMeier(x) * 1 ,MainApp.getRGBColor(255,100,100)));

        Graph graph = new Graph(functions,null,5,0.2f,50,60,0,70, new Dimension(800,400));
        addComponent(1,3, graph,new Insets(10, 110, 0,0),1,1,0,GridBagConstraints.NORTH);
    }

    private void createDescriptionGraphUi(KaplanMeierEstimator kaplanMeierEstimator) {
        JLabel nameGraph = new JLabel(getStringDescriptionMethod(kaplanMeierEstimator.getTimeLiveAVG()));

        nameGraph.setFont(new Font("Verdana", Font.PLAIN, 24));
        addComponent(1,2, nameGraph,new Insets(60, 110, 0,0),1,1,0,GridBagConstraints.HORIZONTAL);
    }

    private void createHeader(Presenter presenter) {
        String nameDevices = "";
        if(presenter.device != null) {
            nameDevices = presenter.device.name;
        }

        JLabel label = new JLabel("Характеристики надёжности устройтва");
        JLabel labelNameDevices = new JLabel("<html>оборудование: <font color='green'>" + nameDevices + "</font></html>");
        labelNameDevices.setFont(new Font("Verdana", Font.BOLD, 18));
        label.setFont(new Font("Verdana", Font.PLAIN, 40));

        addComponent(0,0, label,new Insets(0, 0, 0,0),2,1,0,GridBagConstraints.CENTER);
        addComponent(0,1, labelNameDevices,new Insets(15, 0, 0,0),2,1,0,GridBagConstraints.CENTER);
    }

    private void addComponent(int gridX, int gridY, Component component, Insets insets, int gridWidth,int gridHeight, int height, int fill) {
        constraints.ipady = height;
        constraints.gridwidth = gridWidth;
        constraints.gridheight = gridHeight;
        constraints.fill = fill;
        constraints.anchor = GridBagConstraints.NORTH;

        if(insets != null) {
            constraints.insets = insets;
        }
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        gridBagLayout.setConstraints(component, constraints);

        panelTechnicalSpecificationsModule.add(component);
    }

    @Override
    public JPanel getPanel() { return panelTechnicalSpecificationsModule; }

    @Override
    public void actionPerformed(ActionEvent event) { }

    private String getStringDescriptionMethod(long timeLiveAVG) {
        timeLiveAVG /= (60*60);
        return  "<html>Оценка выживаймости устройтва.<br>" +
                "Оценка Каплана-Мейера.<br><br>" +
                "<font color='#708090'>Ось X -время наработки устройства в: </font> днях <br>" +
                "<font color='#708090'>Ось Y -функция выживаймости: </font> S(t) <br>" +
                "<font color='#708090'>средняя выживаемось устройства</font>: "+timeLiveAVG+" часов <br>";
    }
}
