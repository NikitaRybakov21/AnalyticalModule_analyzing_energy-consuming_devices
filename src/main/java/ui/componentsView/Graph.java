package ui.componentsView;

import dataSourse.PointF;
import ui.main.MainApp;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Graph extends JComponent {

    private final int width = 800;
    private final int height = 400;

    private final int padding = 40;
    private final Point XOY = new Point(padding,height - padding);
    private final Point y1 = new Point(padding,padding);
    private final Point x1 = new Point(width - padding,height - padding);

    private final int stepX = 29;
    private final int stepY = 30;

    private final float stepValueX = 1f;
    private final float stepValueY = 10f;

    private final int startX = 0;
    private final int endX = 24 + 10;

    private double animatedX = startX;

    private final Font font = new Font("Verdana", Font.PLAIN, 15);
    private final CallBackFun callBackFun;
    private final ArrayList<PointF> listPoint;

    public Graph(CallBackFun callbackFun, ArrayList<PointF> listPoint) {
        this.callBackFun = callbackFun;
        this.listPoint = listPoint;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawBackground(g2);
        drawLine(g2);
        drawGraph(g2);
        drawGraphPoint(g2);

        if(animatedX <= endX) {
            repaint();
        }
    }

    private void drawBackground(Graphics2D g2) {
        g2.setColor(MainApp.getRGBColor(244,244,244));
        g2.fillRoundRect(0,0,width,height,66,66);
    }

    private void drawLine(Graphics2D g2) {
        g2.setFont(font);
        g2.setColor(Color.GRAY);
        g2.setStroke(new BasicStroke(4));

        g2.drawLine(XOY.x, XOY.y, y1.x, y1.y);
        g2.drawLine(XOY.x, XOY.y, x1.x, x1.y);

        g2.setStroke(new BasicStroke(2));

        int del = 10;
        for (int i = 0; XOY.x + stepX*(i + 1) < x1.x; i++) {

            g2.setColor(Color.GRAY);
            g2.drawLine(XOY.x + stepX*(i + 1), XOY.y + del, XOY.x + stepX*(i + 1), XOY.y - del);
            g2.setColor(Color.BLACK);

            g2.drawString(String.valueOf((int)((i+1)*stepValueX )) , XOY.x + stepX*(i + 1), XOY.y + padding/1.5f);
        }

        for (int i = 0; XOY.y - stepY*(i + 1) > y1.y; i++) {

            g2.setColor(Color.GRAY);
            g2.drawLine(XOY.x - del, XOY.y - stepY*(i + 1), XOY.x + del, XOY.y - stepY*(i + 1));
            g2.setColor(Color.BLACK);

            g2.drawString(String.valueOf((int)((i+1)*stepValueY)), XOY.x - padding/1.2f, XOY.y - stepY*(i + 1));
        }

        g2.setColor(Color.BLUE);
        g2.setFont(new Font("Verdana", Font.PLAIN, 25));
        g2.drawString("Y", XOY.x - padding/1.5f, y1.y);
        g2.drawString("X" , x1.x, XOY.y + padding/1.5f);
        g2.setFont(font);
    }

    private void drawGraph(Graphics2D g2) {
        g2.setColor(MainApp.getRGBColor(50,50,255));

        double stepFX = 0.02;

        double speedStartAnimation = 0.005f;
        double speedAnimation = speedStartAnimation * (endX - animatedX + 3) ;
        animatedX += speedAnimation;

        double x = startX;
        while (x < animatedX) {

            double fx = callBackFun.getValueFunX(x);

            int gx = (int) (x * stepX * (1f/stepValueX)   + XOY.x);
            int gy = (int) (XOY.y - fx * stepY * (1f/stepValueY) );

            if(gx < width) {
                g2.drawLine(gx, gy, gx, gy);
            }
            x += stepFX;
        }
    }

    private void drawGraphPoint(Graphics2D g2) {
        g2.setColor(Color.RED);

        for (PointF point : listPoint) {
            double y = point.y;
            double x = point.x;

            int gx = (int) (x * stepX * (1f/stepValueX)  + XOY.x);
            int gy = (int) (XOY.y - y * stepY * (1f/stepValueY) );

            if(gx < width) {
                g2.drawArc(gx - 1, gy - 1, 2, 2, 0, 360);
            }
        }
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
