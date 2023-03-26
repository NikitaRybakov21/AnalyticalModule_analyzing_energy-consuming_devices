package ui.componentsView;

import javax.swing.*;
import java.awt.*;

public class Graph extends JComponent {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawLine(0,0,400,400);

        g.setColor(Color.black);
        g.drawString("dfsvvvvddvdfdx",200,200);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400,400);
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
