package ui.panel.panelDetailsSlider;

import dataSourse.PointF;
import dataSourse.PowerDevice;
import mathematicalModels.CalculationOfEfficiency;
import mathematicalModels.PairAB;
import mathematicalModels.SumOfSquaredErrorsSSE;
import presentation.Presenter;
import ui.componentsView.Graph;
import ui.main.MainApp;
import ui.panel.interfacesPanel.InterfacePanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelEffectivenessModule implements ActionListener , InterfacePanel {

    private final JPanel panelEffectivenessModule = new JPanel();
    private final GridBagLayout gridBagLayout = new GridBagLayout();

    private final ArrayList<PowerDevice> listPower;

    private final GridBagConstraints constraints =  new GridBagConstraints();

    public PanelEffectivenessModule(Presenter presenter, ArrayList<PowerDevice> listPowerSave) {
        this.listPower = listPowerSave;
        panelEffectivenessModule.setBackground(MainApp.getRGBColor(254,254,254));
        panelEffectivenessModule.setLayout(gridBagLayout);

        createHeader(presenter);
        createLabelKPD(presenter.device.power);
        createGraphUi();
    }

    private void createGraphUi() {
        ArrayList<PointF> listPoints = getListPoints();
        PairAB pairAB = SumOfSquaredErrorsSSE.sse(listPoints);
        double a = pairAB.a;
        double b = pairAB.b;

        Graph graph = new Graph( x -> a * x + b,listPoints,1f,10f,29,30,0,24+10);
        addComponent(1,3, graph,new Insets(10, 110, 0,0),1,1,0,GridBagConstraints.NORTH);

        JLabel nameGraph = new JLabel(getStringDescriptionMethod(a,b));
        nameGraph.setFont(new Font("Verdana", Font.PLAIN, 24));
        addComponent(1,2, nameGraph,new Insets(60, 110, 0,0),1,1,0,GridBagConstraints.HORIZONTAL);
    }

    private void createHeader(Presenter presenter) {
        JLabel label = new JLabel("Эффективность потребления устройства");
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

    private void createLabelKPD(String power) {
        JLabel labelPowerInput = new JLabel(CalculationOfEfficiency.calculationKpd(listPower,power));
        labelPowerInput.setFont(new Font("Verdana", Font.PLAIN, 24));

        addComponent(0,2, labelPowerInput,new Insets(60, 0, 0,0),1,2,0,GridBagConstraints.HORIZONTAL);
    }

    private ArrayList<PointF> getListPoints() {
        ArrayList<PointF> listPoint = new ArrayList<>();

        for (PowerDevice powerDevice : listPower) {

            double y = ((powerDevice.effectivePower / powerDevice.inputPower) * 100);
            double x =  (powerDevice.time/(60f*60f));

            listPoint.add(new PointF(x, y));
        }
        return listPoint;
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

        panelEffectivenessModule.add(component);
    }

    @Override
    public JPanel getPanel() { return panelEffectivenessModule; }

    @Override
    public void actionPerformed(ActionEvent event) { }

    private String getStringDescriptionMethod(double a,double b) {
        return "<html>Регрессионный анализ эффективности.<br><br>Метод наименьших общих квадратов" + "<br>" +
                "<font color='#708090'>Ось X -время работы устройства в </font>( часах )<br>" +
                "<font color='#708090'>Ось Y -КПД устройства в </font>( % )<br>" +
                "<font color='#708090'>регрессионная прямая </font>y="+String.format("%.2f", a)+"*x+"+String.format("%.2f", b)+"</html>";
    }
}
