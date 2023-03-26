package ui.componentsView;

import ui.main.MainApp;

import javax.swing.*;
import java.awt.*;

public class ComponentsView extends JComponent {

    int width = 0;
    int height = 2;

    public ComponentsView(int wight) {
        this.width = wight;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(MainApp.getRGBColor(150,150,150));
        g2.fillRect(0,0,width,height);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width,height);
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
}
