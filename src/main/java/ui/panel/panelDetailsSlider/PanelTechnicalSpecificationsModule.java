package ui.panel.panelDetailsSlider;

import dataSourse.DevicesDeath;
import dataSourse.PointF;
import dataSourse.PowerDevice;
import dataSourse.ProductivityDevices;
import mathematicalModels.*;
import presentation.Presenter;
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

    private final JPanel panelTechnicalSpecificationsModule = new JPanel();
    private final GridBagLayout gridBagLayout = new GridBagLayout();

    private final GridBagConstraints constraints =  new GridBagConstraints();

    public PanelTechnicalSpecificationsModule(Presenter presenter, ArrayList<DevicesDeath> listDeath) {
        panelTechnicalSpecificationsModule.setBackground(MainApp.getRGBColor(254,254,254));
        panelTechnicalSpecificationsModule.setLayout(gridBagLayout);

        createHeader(presenter);
        createGraphUi(listDeath);
        createLabelKPD(presenter);
    }

    private void createLabelKPD(Presenter presenter) {
        String label = "";
        if(presenter.productivityDevices != null) {
           label = DegreeOfWear.getStringProductivityDevices(presenter.productivityDevices);
        }

        JLabel labelPowerInput = new JLabel(label);

        labelPowerInput.setFont(new Font("Verdana", Font.PLAIN, 24));
        addComponent(0,2, labelPowerInput,new Insets(60, 0, 0,0),1,2,0,GridBagConstraints.HORIZONTAL);
    }

    private void createGraphUi(ArrayList<DevicesDeath> listDeath) {
        ArrayList<PointF> listPoints = getListPoints();

        KaplanMeierEstimator kaplanMeierEstimator = new KaplanMeierEstimator(listDeath);

        Graph graph = new Graph(x -> kaplanMeierEstimator.funSurviveKaplanMeier(x) * 1 ,listPoints,5,0.2f,50,60,0,70);
        addComponent(1,3, graph,new Insets(10, 110, 0,0),1,1,0,GridBagConstraints.NORTH);


        JLabel nameGraph = new JLabel(getStringDescriptionMethod(kaplanMeierEstimator.getTimeLiveAVG()));
        nameGraph.setFont(new Font("Verdana", Font.PLAIN, 24));
        addComponent(1,2, nameGraph,new Insets(60, 110, 0,0),1,1,0,GridBagConstraints.HORIZONTAL);
    }

    private void createHeader(Presenter presenter) {
        JLabel label = new JLabel("Характеристики надёжности устройтва");
        label.setFont(new Font("Verdana", Font.PLAIN, 40));

        addComponent(0,0, label,new Insets(0, 0, 0,0),2,1,0,GridBagConstraints.CENTER);

        String nameDevices = "";
        if(presenter.device != null) {
            nameDevices = presenter.device.name;
        }

        JLabel labelNameDevices = new JLabel("<html>оборудование: <font color='green'>" + nameDevices + "</font></html>");
        labelNameDevices.setFont(new Font("Verdana", Font.BOLD, 18));
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

    private ArrayList<PointF> getListPoints() {
        return new ArrayList<>();
    }

    @Override
    public JPanel getPanel() { return panelTechnicalSpecificationsModule; }

    @Override
    public void actionPerformed(ActionEvent event) { }

    private String getStringDescriptionMethod(long timeLiveAVG) {
        timeLiveAVG /= (60*60);
        return  "<html>Оценка выживаймости устройтва.<br><br>" +
                "Метод Каплана-Мейера<br>" +
                "<font color='#708090'>Ось X -время наработки устройства в </font>( днях )<br>" +
                "<font color='#708090'>Ось Y -функция выживаймости </font>( S(t) )<br>" +
                "<font color='#708090'>средняя выживаемось устройства</font>( "+timeLiveAVG+" часов )<br>";
    }
}
