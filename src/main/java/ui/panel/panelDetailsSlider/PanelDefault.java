package ui.panel.panelDetailsSlider;

import presentation.Presenter;
import ui.main.MainApp;
import ui.panel.interfacesPanel.InterfacePanel;
import javax.swing.*;

public class PanelDefault implements InterfacePanel {
    private final JPanel panelDefault = new JPanel();

    public PanelDefault(Presenter presenter) {
        panelDefault.setBackground(MainApp.getRGBColor(255,255,255));
    }

    @Override
    public JPanel getPanel() { return panelDefault; }
}
