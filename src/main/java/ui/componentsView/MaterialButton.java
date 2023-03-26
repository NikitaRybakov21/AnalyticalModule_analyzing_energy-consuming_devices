package ui.componentsView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MaterialButton extends JButton {
    private boolean isMousePressed = false;

    private Image imButton;
    private Image imButtonPressed;
    int wButton;
    int hButton;

    public MaterialButton(String label, String nameButton, String nameButtonPressed, int wButton,int hButton) {
        super(label);
        this.wButton = wButton;
        this.hButton = hButton;

        File fileButton = new File(nameButton);
        File fileButtonPressed = new File(nameButtonPressed);
        try {
            this.imButton = ImageIO.read(fileButton);
            this.imButtonPressed = ImageIO.read(fileButtonPressed);

        } catch (IOException e) {
            e.printStackTrace();
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                isMousePressed = true;
                invalidateView();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                isMousePressed = false;
                invalidateView();
            }
        });
    }

    private void invalidateView() {
        this.repaint();
    }

    public void selectButton(boolean flag) {
        isMousePressed = flag;
        invalidateView();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(isMousePressed) {
            g2.drawImage(imButtonPressed,0,0,wButton,hButton,null);
        } else {
            g2.drawImage(imButton,0,0,wButton,hButton,null);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(wButton,hButton);
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
