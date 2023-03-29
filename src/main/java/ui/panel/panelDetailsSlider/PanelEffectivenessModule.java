package ui.panel.panelDetailsSlider;

import dataSourse.PointF;
import dataSourse.PowerDevice;
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
        createLabelKPD();
        createGraphUi();
    }

    private void createGraphUi() {
        Graph graph = createGraph();
        addComponent(1,3, graph,new Insets(10, 110, 0,0),1,1,0,GridBagConstraints.NORTH);

        JLabel nameGraph = new JLabel("<html>Регрессионный анализ эффективности.<br><br><font color='blue'>Ось X -время работы устройтова в ( часах )<br>Ось Y -КПД устройства в ( % )</font></html>");
        nameGraph.setFont(new Font("Verdana", Font.PLAIN, 24));
        addComponent(1,2, nameGraph,new Insets(60, 110, 0,0),1,1,0,GridBagConstraints.HORIZONTAL);
    }

    private void createHeader(Presenter presenter) {
        JLabel label = new JLabel("Эффективность потребления устройства");
        Font fontHeader = new Font("Verdana", Font.PLAIN, 40);
        label.setFont(fontHeader);
        addComponent(0,0, label,new Insets(0, 0, 0,0),2,1,0,GridBagConstraints.CENTER);

        String nameDevices = "";
        if(presenter.device != null) {
            nameDevices = presenter.device.name;
        }

        JLabel labelNameDevices = new JLabel("<html>оборудование: <font color='green'>" + nameDevices + "</font></html>");
        labelNameDevices.setFont(new Font("Verdana", Font.BOLD, 18));
        addComponent(0,1, labelNameDevices,new Insets(15, 0, 0,0),2,1,0,GridBagConstraints.CENTER);
    }

    private void createLabelKPD() {
        JLabel labelPowerInput = new JLabel(calcKpd());
        labelPowerInput.setFont(new Font("Verdana", Font.PLAIN, 28));

        addComponent(0,2, labelPowerInput,new Insets(60, 0, 0,0),1,2,0,GridBagConstraints.HORIZONTAL);
    }

    private String calcKpd() {
        int sumInputPower = 0;
        int sumEffPower = 0;
        int timeSum = 0;
        for (PowerDevice powerDevice : listPower) {
            sumInputPower += powerDevice.inputPower;
            sumEffPower += powerDevice.effectivePower;
            timeSum += powerDevice.time;
        }
        float valueKpd = (((float) sumEffPower/sumInputPower)*100f);
        String kpd = String.format("%.2f", valueKpd);
        return "<html>Oбщая потребляемая мощность: "+sumInputPower+" ватт<br>Oбщая производимая полезная мощность: "+sumEffPower+" ватт<br>Oбщее время работы: "+timeSum+" cек<br>  <br>Среднее КПД = "+kpd+"%";
    }


    private Graph createGraph() {
        ArrayList<PointF> listPoint = new ArrayList<>();

        for (PowerDevice powerDevice : listPower) {

            double y = ((powerDevice.effectivePower / powerDevice.inputPower) * 100);
            double x =  (powerDevice.time/(60f*60f));

            listPoint.add(new PointF(x, y));
        }

        return new Graph( x -> sin(x)+3 , listPoint);
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
}
