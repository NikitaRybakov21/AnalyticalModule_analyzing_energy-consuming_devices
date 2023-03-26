package ui.panel.panelDetailsSlider;

import presentation.Presenter;
import ui.main.MainApp;
import ui.panel.interfacesPanel.InterfacePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelEffectivenessModule implements ActionListener , InterfacePanel {

    private final JPanel panelEffectivenessModule = new JPanel();
    private final GridBagLayout gridBagLayout = new GridBagLayout();

    private final Font fontHeader = new Font("Verdana", Font.PLAIN, 48);
    private final JLabel label = new JLabel("Эффективность");
    private final GridBagConstraints constraints =  new GridBagConstraints();
    private final Presenter presenter;

    public PanelEffectivenessModule(Presenter presenter) {
        this.presenter = presenter;
        panelEffectivenessModule.setBackground(MainApp.getRGBColor(254,254,254));
        panelEffectivenessModule.setLayout(gridBagLayout);

        label.setFont(fontHeader);
        addComponent(0,0,label,new Insets(0, 0, 0,0),1,0,GridBagConstraints.CENTER);
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
