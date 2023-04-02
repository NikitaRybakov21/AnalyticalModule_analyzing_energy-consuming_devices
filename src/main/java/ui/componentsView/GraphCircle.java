package ui.componentsView;

import ui.main.MainApp;

import javax.swing.*;
import java.awt.*;

public class GraphCircle extends JComponent {

    private final int width = 300;
    private final int height = 300;

    private final int startX = 0;
    private final int endX;

    private double animatedX = startX;

    public GraphCircle(int endPercent) {
        this.endX = (endPercent * 360) / 100;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double speedStartAnimation = 0.01f;
        double speedAnimation = speedStartAnimation * (endX - animatedX) ;
        animatedX += speedAnimation;

        drawCircleGraph(g2);

        if(animatedX <= endX) {
            repaint();
        }
    }

    private void drawCircleGraph(Graphics2D g2) {
        g2.setColor(MainApp.getRGBColor(248,248,248));
        g2.fillArc(0,0,width,height,0,360);

        g2.setColor(MainApp.getRGBColor(255,100,100));
        g2.fillArc(0,0,width,height,0, (int) animatedX);

        g2.setColor(Color.WHITE);
        int padding = 20;
        g2.fillArc(padding, padding,width - 2* padding,height - 2* padding,0,360);
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
