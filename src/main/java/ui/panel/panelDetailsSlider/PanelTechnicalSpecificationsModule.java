package ui.panel.panelDetailsSlider;

import presentation.Presenter;
import ui.main.MainApp;
import ui.panel.interfacesPanel.InterfacePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelTechnicalSpecificationsModule implements ActionListener , InterfacePanel {

    private final JPanel panelTechnicalSpecificationsModule = new JPanel();
    private final GridBagLayout gridBagLayout = new GridBagLayout();

    private final GridBagConstraints constraints =  new GridBagConstraints();
    private final Presenter presenter;

    public PanelTechnicalSpecificationsModule(Presenter presenter) {
        this.presenter = presenter;
        panelTechnicalSpecificationsModule.setBackground(MainApp.getRGBColor(254,254,254));
        panelTechnicalSpecificationsModule.setLayout(gridBagLayout);

        JLabel label = new JLabel("Технические характкристики");
        Font fontHeader = new Font("Verdana", Font.PLAIN, 48);
        label.setFont(fontHeader);
        addComponent(0,0, label,new Insets(0, 0, 0,0),1,0,GridBagConstraints.CENTER);
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

        panelTechnicalSpecificationsModule.add(component);
    }

    @Override
    public JPanel getPanel() { return panelTechnicalSpecificationsModule; }

    @Override
    public void actionPerformed(ActionEvent event) { }
}
