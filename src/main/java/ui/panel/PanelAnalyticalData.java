package ui.panel;

import presentation.Presenter;
import ui.componentsView.ComponentsView;
import ui.componentsView.HintTextField;
import ui.componentsView.MaterialButton;
import ui.componentsView.MaterialButtonSlider;
import ui.helperView.TextAnimator;
import ui.main.MainApp;
import ui.panel.interfacesPanel.CallbackTextAnimation;
import ui.panel.interfacesPanel.InterfacePanel;
import ui.panel.panelDetailsSlider.PanelDefault;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static dataSourse.constValue.TypeModules.*;
import static ui.main.MainApp.baseUrlImage;

public class PanelAnalyticalData implements MouseListener , InterfacePanel , CallbackTextAnimation{

    private final JPanel panelAnalyticalData = new JPanel();

    private final MaterialButton searchButton = new MaterialButton("Test", baseUrlImage +"buttonAnalytics.png",baseUrlImage +"buttonAnalytics_pressed.png",101,30);

    private final MaterialButtonSlider slideButton1 = new MaterialButtonSlider("Test", baseUrlImage +"buttonSlide1.png",baseUrlImage +"buttonSlide1_pressed.png",151,30);
    private final MaterialButtonSlider slideButton2 = new MaterialButtonSlider("Test", baseUrlImage +"buttonSlide2.png",baseUrlImage +"buttonSlide2_pressed.png",151,30);
    private final MaterialButtonSlider slideButton3 = new MaterialButtonSlider("Test", baseUrlImage +"buttonSlide3.png",baseUrlImage +"buttonSlide3_pressed.png",151,30);

    private final JTextField searchData = new HintTextField(" ->");
    private final Font fontAppName = new Font("Verdana", Font.PLAIN, 36);
    private final JLabel labelSearchData = new JLabel("<html><font color='#708090'>введите наименование прибора для выполнения анализа </font>");
    private final JLabel appName = new JLabel("<html>Аналитический<br>модуль");
    private final JLabel labelInfo = new JLabel("");

    private final TextAnimator textAnimator = new TextAnimator();

    private final Presenter presenter;
    private final ExecutorService service = Executors.newCachedThreadPool();

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
        panelAnalyticalData.setBackground(MainApp.getRGBColor(240,240,254));

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
        int searchButtonWidth = 101;

        searchData.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        searchData.setFont(new Font("Verdana", Font.PLAIN, 14));

        addComponents(searchData, paddingStart,paddingTop, searchDataWidth,searchButtonHeight);
        addComponents(searchButton, searchDataWidth + (paddingStart) ,paddingTop, searchButtonWidth,searchButtonHeight);

        int labelInfoWidth = 300;
        int labelInfoHeight = 30;

        Font font = new Font("Verdana", Font.PLAIN, 15);

        labelInfo.setFont(font);
        labelSearchData.setFont(font);

        addComponents(labelInfo, searchDataWidth + (2*paddingStart) + searchButtonWidth ,paddingTop, labelInfoWidth,labelInfoHeight);
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

        ComponentsView componentsViewBottom = new ComponentsView(widthScreen);
        addComponents(componentsViewBottom, 0,paddingTopLine, widthScreen ,2);
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

            if(presenter.device != null) {
                presenter.setPanelDetailModule(MODULES_Effectiveness);
            }
        }
        if(event.getSource() == slideButton2) {
            slideButton1.selectButton(false);
            slideButton3.selectButton(false);

            if(presenter.device != null) {
                presenter.setPanelDetailModule(MODULES_TechnicalSpecifications);
            }
        }
        if(event.getSource() == slideButton3) {
            slideButton1.selectButton(false);
            slideButton2.selectButton(false);

            if(presenter.device != null) {
                presenter.setPanelDetailModule(MODULES_LifeCycleModule);
            }
        }
        if(event.getSource() == searchButton) {
            slideButton1.selectButton(true);
            slideButton3.selectButton(false);
            slideButton2.selectButton(false);

            searchData.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
            String name = searchData.getText();
            presenter.sendGetDeviceToAnalytical(name);
        }
    }

    private void startTextAnimation(String mess, CallbackTextAnimation callbackTextAnimation, String color) {
        service.submit(new Runnable() {
            public void run() {
                textAnimator.animationText(mess,callbackTextAnimation,color);
            }
        });
    }

    @Override
    public void setTextInfoAnimation(String messAnimation,String color) {
        labelInfo.setText("<html><font color='"+color+"'>" + messAnimation + "</font></html>");
    }

    public void nullDevices() {
        String mess = "ошибка: устройство не найдено!";
        String color = "red";

        startTextAnimation(mess,this,color);
        searchData.setBorder(new LineBorder(Color.RED, 1));

        presenter.setPanelDetailModule(MODULES_Default);
    }

    public void devicesSuccessful() {
        String mess = "устройство успешно найдено!";
        String color = "green";

        startTextAnimation(mess, this, color);
        presenter.setPanelDetailModule(MODULES_Effectiveness);
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
