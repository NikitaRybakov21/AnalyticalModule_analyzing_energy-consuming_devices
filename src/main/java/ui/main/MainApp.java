package ui.main;

import geniratorRes.GenerateData;
import presentation.Presenter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainApp extends JFrame implements InterfaceMainApp {

    public static final int sizeX = 2300;
    public static final int sizeY = 1200;

    public static final String nameApp = "AnalyticalModule";
    public static final String baseUrlImage = "src/main/resources/images/";

    public static void main(String[] args) {
        GenerateData.getArrayListPowerDevices();
        new MainApp(nameApp , sizeX ,sizeY);
    }

    public MainApp(String header, int sizeX, int sizeY) {
        super(header);

        setSize(sizeX,sizeY);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon img = new ImageIcon(baseUrlImage +"module.png");
        setIconImage(img.getImage());

        initView();

        setVisible(true);
    }

    private void initView() {
        Presenter presenter = new Presenter(this);
        setFirsPanel(presenter);
        resizeWindow(presenter);
    }

    private void setFirsPanel(Presenter presenter) {
        JPanel panelFirs = presenter.getPanelAuthorization().getPanel();
        presenter.panelCurrent = panelFirs;
        add(panelFirs);
    }

    private void resizeWindow(Presenter presenter) {
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {

                if(presenter.panelCurrent != null) {
                    if (presenter.panelCurrent.equals(presenter.getPanelAnalyticalData().getPanel())) {
                        int widthScreen = evt.getComponent().getBounds().width;
                        int heightScreen = evt.getComponent().getBounds().height;

                        JPanel panel = presenter.getNewPanelAnalyticalData(widthScreen, heightScreen).getPanel();

                        navigation(presenter.panelCurrent,panel,presenter);
                    }
                }
            }
        });
    }

    @Override
    public void repaintFrame() {
        repaint();
        setVisible(true);
    }

    @Override
    public void navigation(JPanel panelRemoved, JPanel panel, Presenter presenter) {
        remove(panelRemoved);
        repaint();

        add(panel);
        setVisible(true);

        presenter.panelCurrent = panel;
    }

    public static Color getRGBColor(int r, int g, int b) {
        float[] hsb = Color.RGBtoHSB(r, g, b, null);
        return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
    }
}
