package ui.componentsView;

import dataSourse.PointF;
import ui.main.MainApp;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Graph extends JComponent {

    private int width = 800;
    private int height = 400;

    private final int padding = 40;
    private final Point XOY;
    private final Point y1;
    private final Point x1;

    private final int stepX;
    private final int stepY;

    private final float stepValueX;
    private final float stepValueY;

    private int startX;
    private final int endX;

    private double animatedX = startX;

    private final Font font = new Font("Verdana", Font.PLAIN, 15);
    private final ArrayList<FunColorX> listFunction;
    private final ArrayList<PointF> listPoint;

    public Graph(ArrayList<FunColorX> listFunction, ArrayList<PointF> listPoint,float stepValueX,float stepValueY,int stepX,int stepY,int startX, int endX,Dimension dimension) {
        this.stepValueY = stepValueY;
        this.stepValueX = stepValueX;
        this.stepX = stepX;
        this.stepY = stepY;
        this.startX = startX;
        this.endX = endX;

        this.width = dimension.width;
        this.height = dimension.height;

        this.listFunction = listFunction;
        this.listPoint = listPoint;

        this.XOY = new Point(padding,height - padding);
        this.y1 = new Point(padding,padding);
        this.x1 = new Point(width - padding,height - padding);
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
        g2.setColor(MainApp.getRGBColor(246,246,246));
        g2.fillRoundRect(0,0,width,height,66,66);
    }

    private void drawLine(Graphics2D g2) {
        g2.setFont(font);
        g2.setColor(Color.GRAY);
        g2.setStroke(new BasicStroke(4));

        g2.drawLine(XOY.x, XOY.y, y1.x, y1.y);
        g2.drawLine(XOY.x, XOY.y, x1.x, x1.y);

        divisions(g2);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Verdana", Font.PLAIN, 25));
        g2.drawString("Y", XOY.x - padding/1.5f, y1.y);
        g2.drawString("X" , x1.x, XOY.y + padding/1.5f);
        g2.setFont(font);
    }

    private void divisions(Graphics2D g2) {
        g2.setStroke(new BasicStroke(2));

        int del = 10;
        for (int i = 0; XOY.x + stepX*(i + 1) < x1.x; i++) {

            g2.setColor(Color.GRAY);
            g2.drawLine(XOY.x + stepX*(i + 1), XOY.y + del, XOY.x + stepX*(i + 1), XOY.y - del);
            g2.setColor(Color.BLACK);

            if(stepValueX/((int)stepValueX) > 1) {
                g2.drawString(String.valueOf(((i+1)*stepValueX )) , XOY.x + stepX*(i + 1), XOY.y + padding/1.5f);
            } else  {
                g2.drawString(String.valueOf((int)((i+1)*stepValueX )) , XOY.x + stepX*(i + 1), XOY.y + padding/1.5f);
            }
        }

        for (int i = 0; XOY.y - stepY*(i + 1) > y1.y; i++) {

            g2.setColor(Color.GRAY);
            g2.drawLine(XOY.x - del, XOY.y - stepY*(i + 1), XOY.x + del, XOY.y - stepY*(i + 1));
            g2.setColor(Color.BLACK);

            if(stepValueY/((int)stepValueY) > 1) {
                g2.drawString(String.valueOf(((i+1)*stepValueY)), XOY.x - padding/1.2f, XOY.y - stepY*(i + 1));
            } else  {
                g2.drawString(String.valueOf((int)((i+1)*stepValueY)), XOY.x - padding/1.2f, XOY.y - stepY*(i + 1));
            }
        }
    }

    private void drawGraph(Graphics2D g2) {
        double stepFX = 0.08;

        double speedStartAnimation = 0.005f;
        double speedAnimation = speedStartAnimation * (endX - animatedX + 30) ;
        animatedX += speedAnimation;

        double x = startX;
        while (x < animatedX) {
            if(listFunction != null) {
                for (FunColorX function : listFunction) {
                    double fx = function.callBackFun.getValueFunX(x);
                    g2.setColor(function.color);

                    int gx = (int) (x * stepX * (1f / stepValueX) + XOY.x);
                    int gy = (int) (XOY.y - fx * stepY * (1f / stepValueY));

                    if (gx < width) {
                        g2.drawLine(gx, gy, gx, gy);
                    }
                }
            }
            x += stepFX;
        }
    }

    private void drawGraphPoint(Graphics2D g2) {
        g2.setColor(Color.RED);

        if (listPoint != null) {
            for (PointF point : listPoint) {
                double y = point.y;
                double x = point.x;

                int gx = (int) (x * stepX * (1f / stepValueX) + XOY.x);
                int gy = (int) (XOY.y - y * stepY * (1f / stepValueY));

                if (gx < width) {
                    g2.drawArc(gx - 1, gy - 1, 2, 2, 0, 360);
                }
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
