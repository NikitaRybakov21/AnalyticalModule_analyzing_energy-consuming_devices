package ui.panel.panelDetailsSlider;

import presentation.Presenter;
import ui.main.MainApp;
import ui.panel.interfacesPanel.InterfacePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLifeCycleModule implements ActionListener  , InterfacePanel {

    private final JPanel panelLifeCycleModule = new JPanel();
    private final GridBagLayout gridBagLayout = new GridBagLayout();

    private final Font fontHeader = new Font("Verdana", Font.PLAIN, 48);
    private final JLabel label = new JLabel("Жизненный цикл");
    private final GridBagConstraints constraints =  new GridBagConstraints();
    private final Presenter presenter;

    public PanelLifeCycleModule(Presenter presenter) {
        this.presenter = presenter;
        panelLifeCycleModule.setBackground(MainApp.getRGBColor(254,254,254));
        panelLifeCycleModule.setLayout(gridBagLayout);

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

        panelLifeCycleModule.add(component);
    }

    @Override
    public JPanel getPanel() { return panelLifeCycleModule; }

    @Override
    public void actionPerformed(ActionEvent event) { }
}
