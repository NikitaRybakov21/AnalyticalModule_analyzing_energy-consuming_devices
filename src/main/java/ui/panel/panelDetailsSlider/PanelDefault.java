package ui.panel.panelDetailsSlider;


import ui.main.MainApp;
import ui.panel.interfacesPanel.InterfacePanel;
import javax.swing.*;

import java.awt.*;

import static ui.main.MainApp.baseUrlImage;

public class PanelDefault implements InterfacePanel {
    private final JPanel panelDefault = MainApp.getStylePanel();

    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraints =  new GridBagConstraints();

    public PanelDefault() {
        panelDefault.setBackground(MainApp.getRGBColor(254,254,254));
        panelDefault.setLayout(gridBagLayout);

        var icon = new ImageIcon(baseUrlImage +"icon_background.png");
        addComponent(0,0, new JLabel(icon),new Insets(0, 0, 0,0),1,0,GridBagConstraints.CENTER);
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

        panelDefault.add(component);
    }

    @Override
    public JPanel getPanel() { return panelDefault; }
}
