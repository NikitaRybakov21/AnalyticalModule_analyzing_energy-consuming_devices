package ui.panel.panelDetailsSlider;

import presentation.Presenter;
import ui.componentsView.CallBackFun;
import ui.componentsView.Graph;
import ui.main.MainApp;
import ui.panel.interfacesPanel.InterfacePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class PanelEffectivenessModule implements ActionListener , InterfacePanel {

    private final JPanel panelEffectivenessModule = new JPanel();
    private final GridBagLayout gridBagLayout = new GridBagLayout();

    private final GridBagConstraints constraints =  new GridBagConstraints();

    public PanelEffectivenessModule(Presenter presenter) {
        panelEffectivenessModule.setBackground(MainApp.getRGBColor(254,254,254));
        panelEffectivenessModule.setLayout(gridBagLayout);

        JLabel label = new JLabel("Эффективность потребления устройства");
        Font fontHeader = new Font("Verdana", Font.PLAIN, 40);
        label.setFont(fontHeader);
        addComponent(0,0, label,new Insets(0, 0, 0,0),1,0,GridBagConstraints.HORIZONTAL);

        Graph graph = new Graph(  x -> sin(x)-cos(2*x)+3  );

        addComponent(0,1, graph,new Insets(60, 0, 0,0),1,0,GridBagConstraints.CENTER);
    }

    private void addComponent(int gridX, int gridY, Component component, Insets insets, int gridWidth, int height, int fill) {
        constraints.ipady = height;
        constraints.gridwidth = gridWidth;
        constraints.fill = fill;

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
