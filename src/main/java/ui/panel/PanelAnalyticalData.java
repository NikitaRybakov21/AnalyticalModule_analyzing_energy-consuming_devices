package ui.panel;

import presentation.Presenter;
import ui.componentsView.ComponentsView;
import ui.componentsView.Graph;
import ui.componentsView.MaterialButton;
import ui.componentsView.MaterialButtonSlider;
import ui.main.MainApp;
import ui.panel.interfacesPanel.InterfacePanel;
import ui.panel.panelDetailsSlider.PanelDefault;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static dataSourse.TypeModules.*;
import static ui.main.MainApp.baseUrlImage;

public class PanelAnalyticalData implements MouseListener , InterfacePanel {

    private final JPanel panelAnalyticalData = new JPanel();

    private final MaterialButton searchButton = new MaterialButton("Test", baseUrlImage +"buttonAnalytics.png",baseUrlImage +"buttonAnalytics_pressed.png",101,30);

    private final MaterialButtonSlider slideButton1 = new MaterialButtonSlider("Test", baseUrlImage +"buttonSlide1.png",baseUrlImage +"buttonSlide1_pressed.png",151,30);
    private final MaterialButtonSlider slideButton2 = new MaterialButtonSlider("Test", baseUrlImage +"buttonSlide2.png",baseUrlImage +"buttonSlide2_pressed.png",151,30);
    private final MaterialButtonSlider slideButton3 = new MaterialButtonSlider("Test", baseUrlImage +"buttonSlide3.png",baseUrlImage +"buttonSlide3_pressed.png",151,30);

    private final JTextField searchData = new JTextField(15);

    private final Font font = new Font("Verdana", Font.PLAIN, 15);
    private final Font fontAppName = new Font("Verdana", Font.PLAIN, 36);

    private final JLabel labelSearchData = new JLabel("введите наименование прибора для выполнения анализа:");
    private final JLabel appName = new JLabel("<html>Аналитический<br>модуль");

    private final Presenter presenter;

    private final int paddingStart = 20;
    private final int paddingTop = 30;
    private final int searchButtonHeight = 30;

    private final int widthScreen;
    private final int heightScreen;

    public PanelAnalyticalData(Presenter presenter, int widthScreen, int heightScreen) {
        this.presenter = presenter;
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;

        panelAnalyticalData.setLayout(null);
        panelAnalyticalData.setBackground(MainApp.getRGBColor(230,230,254));

        createSearch();
        createLabelApp(widthScreen);
        createSlider(widthScreen);

        setPanelDetails(new PanelDefault(presenter).getPanel());
    }

    JPanel currentPanel = null;

    public void setPanelDetails(JPanel panel) {
        final int paddingTopLine = paddingTop + searchButtonHeight + 7 * paddingTop;

        if(currentPanel != null) {
            panelAnalyticalData.remove(currentPanel);
            panelAnalyticalData.repaint();
        }
        currentPanel = panel;

        addComponents(panel,0,paddingTopLine,widthScreen,heightScreen - paddingTopLine);
    }

    private void createSearch()  {
        searchButton.addMouseListener(this);

        int searchDataWidth = 800;
        searchData.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        addComponents(searchData, paddingStart,paddingTop, searchDataWidth,searchButtonHeight);

        int searchButtonWidth = 101;
        addComponents(searchButton, searchDataWidth + (paddingStart) ,paddingTop, searchButtonWidth,searchButtonHeight);

        labelSearchData.setFont(font);
        addComponents(labelSearchData,(paddingStart),paddingTop + searchButtonHeight, searchDataWidth,searchButtonHeight);
    }

    private void createSlider(int widthScreen)  {
        final int paddingTopLine = paddingTop + searchButtonHeight + 7 * paddingTop;
        final int y = paddingTopLine - searchButtonHeight;

        slideButton1.addMouseListener(this);
        slideButton2.addMouseListener(this);
        slideButton3.addMouseListener(this);

        int sliderButtonWidth = 151;
        addComponents(slideButton1,0, y, sliderButtonWidth,searchButtonHeight);
        addComponents(slideButton2, sliderButtonWidth, y, sliderButtonWidth,searchButtonHeight);
        addComponents(slideButton3,2 * sliderButtonWidth, y, sliderButtonWidth,searchButtonHeight);

        ComponentsView componentsView = new ComponentsView(widthScreen);
        addComponents(componentsView, 0,paddingTopLine, widthScreen ,2);
    }

    private void createLabelApp(int widthScreen)  {
        int iconW = 200;
        var icon = new ImageIcon(baseUrlImage +"module_im.png");
        addComponents(new JLabel(icon),widthScreen - iconW - 3*paddingStart,2*paddingStart,iconW,iconW);

        int labelW = 290;
        appName.setFont(fontAppName);
        addComponents(appName,(widthScreen - labelW - (iconW + 3*paddingStart)),paddingStart, labelW , 100);
    }

    private void addComponents(Component component, int x, int y, int width, int height) {
        component.setBounds(x ,y,width,height);
        panelAnalyticalData.add(component);
    }

    @Override
    public JPanel getPanel() { return panelAnalyticalData; }

    @Override
    public void mousePressed(MouseEvent event) {
        if(event.getSource() == slideButton1) {
            slideButton3.selectButton(false);
            slideButton2.selectButton(false);

            presenter.setPanelDetailModule(MODULES_Effectiveness);
        }
        if(event.getSource() == slideButton2) {
            slideButton1.selectButton(false);
            slideButton3.selectButton(false);

            presenter.setPanelDetailModule(MODULES_TechnicalSpecifications);
        }
        if(event.getSource() == slideButton3) {
            slideButton1.selectButton(false);
            slideButton2.selectButton(false);

            presenter.setPanelDetailModule(MODULES_LifeCycleModule);
        }
        if(event.getSource() == searchButton) {
            slideButton1.selectButton(true);
            slideButton3.selectButton(false);
            slideButton2.selectButton(false);

            presenter.setPanelDetailModule(MODULES_Effectiveness);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
}
