package ui.panel;

import presentation.Presenter;
import ui.componentsView.MaterialButton;
import ui.main.MainApp;
import ui.helperView.TextAnimator;
import ui.panel.interfacesPanel.CallbackTextAnimation;
import ui.panel.interfacesPanel.InterfacePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ui.main.MainApp.baseUrlImage;

public class PanelAuthorization implements ActionListener , InterfacePanel , CallbackTextAnimation {

    private final JPanel panelAuthorization = new JPanel( new BorderLayout() ) {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent( g );
            g.setColor( MainApp.getRGBColor(250,250,250) );

            for ( int i = 0; i < getHeight(); i += 4 ) {
                g.fillRect( 0, i,  getWidth(), 2);
            }

            Image imBack = null;
            File fileButton = new File(baseUrlImage + "background.png");
            try {
                imBack = ImageIO.read(fileButton);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int imageH = 563;
            int imageW = 1000;
            g.drawImage(imBack,getWidth()/2 - imageW/2,getHeight()/2 - imageH/2,imageW,imageH,null);
        }
    };

    private final GridBagLayout gridBagLayout = new GridBagLayout();

    private final MaterialButton buttonLogin = new MaterialButton("Test", baseUrlImage + "buttonLogin.png",baseUrlImage + "buttonLogin_pressed.png",338/2,114/2);
    private final MaterialButton buttonRegistration = new MaterialButton("Test", baseUrlImage + "buttonRegistration.png", baseUrlImage + "buttonRegistration_pressed.png", 338/2, 114/2);

    private final JPasswordField textFieldPassword = new JPasswordField(15);
    private final JTextField textFieldName = new JTextField(15);
    private final JLabel textInfo = new JLabel("");

    private final TextAnimator textAnimator = new TextAnimator();

    private final GridBagConstraints constraints =  new GridBagConstraints();

    private final Presenter presenter;
    private final ExecutorService service = Executors.newCachedThreadPool();

    public PanelAuthorization(Presenter presenter) {
        this.presenter = presenter;

        panelAuthorization.setBackground(MainApp.getRGBColor(254,254,254));
        panelAuthorization.setLayout(gridBagLayout);

        createHeader();
        createTextField();
        createButton();
        createTextInfo();
    }

    private void createHeader() {
        JLabel labelAuthorization = new JLabel("Авторизаци пользователя");
        labelAuthorization.setFont(new Font("Verdana", Font.PLAIN, 28));
        addComponent(0,0, labelAuthorization,new Insets(0, 0, 0,0),2,0,GridBagConstraints.CENTER);
    }

    private void createTextField() {
        Font fontLabel = new Font("Verdana", Font.PLAIN, 11);
        Font fontTextField = new Font("Verdana", Font.PLAIN, 16);

        setBorderTextFields();

        JLabel labelName = new JLabel("логин");
        labelName.setFont(fontLabel);
        textFieldName.setFont(fontTextField);

        addComponent(0,1, labelName,new Insets(14, 0, 0,0),2,0,GridBagConstraints.HORIZONTAL);
        addComponent(0,2, textFieldName,new Insets(2, 0, 0,0),2,20,GridBagConstraints.HORIZONTAL);

        JLabel labelPassword = new JLabel("пароль");
        labelPassword.setFont(fontLabel);

        textFieldPassword.setFont(fontTextField);

        addComponent(0,3, labelPassword,new Insets(14, 0, 0,0),2,0,GridBagConstraints.HORIZONTAL);
        addComponent(0,4, textFieldPassword,new Insets(2, 0, 0,0), 2,20,GridBagConstraints.HORIZONTAL);
    }

    private void createButton() {
        addComponent(0,5, buttonLogin,new Insets(20, 0, 0,0),1,0,GridBagConstraints.HORIZONTAL);
        addComponent(1,5, buttonRegistration, new Insets(20, 100, 0,0),1,0,GridBagConstraints.HORIZONTAL);

        buttonLogin.addActionListener(this);
        buttonRegistration.addActionListener(this);
    }

    private void setBorderTextFields() {
        textFieldPassword.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        textFieldName.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        textFieldName.setBorder(BorderFactory.createCompoundBorder(textFieldName.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        textFieldPassword.setBorder(BorderFactory.createCompoundBorder(textFieldName.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
    }

    private void setBorderTextFieldsError() {
        textFieldPassword.setBorder(new LineBorder(Color.RED, 1));
        textFieldName.setBorder(new LineBorder(Color.RED, 1));
        textFieldName.setBorder(BorderFactory.createCompoundBorder(textFieldName.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        textFieldPassword.setBorder(BorderFactory.createCompoundBorder(textFieldName.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
    }

    private void createTextInfo() {
        Font fontInfo = new Font("Verdana", Font.PLAIN, 15);
        textInfo.setFont(fontInfo);
        addComponent(0,6, textInfo,new Insets(30, 0, 0,0),2,0,GridBagConstraints.CENTER);
    }

    private void addComponent(int gridX, int gridY, Component component, Insets insets, int gridWidth, int height, int fill) {
        constraints.ipady = height;
        constraints.gridwidth = gridWidth;
        constraints.fill = fill;

        if(insets != null) {
            constraints.insets = insets;
        }
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        gridBagLayout.setConstraints(component, constraints);

        panelAuthorization.add(component);
    }

    @Override
    public JPanel getPanel() { return panelAuthorization; }

    @Override
    public void actionPerformed(ActionEvent event) {
        setBorderTextFields();

        String password = textFieldPassword.getText();
        String login = textFieldName.getText();

        if (event.getSource() == buttonLogin) {
            if (!password.isEmpty() && !login.isEmpty()) {
                presenter.sendDataBaseAuthorization(password, login);
            } else {
                notValidError();
             //   loginSuccessful();
            }
        }

        if (event.getSource() == buttonRegistration) {
            if (!password.isEmpty() && !login.isEmpty()) {
                presenter.sendDataBaseRegistration(password, login);
            } else {
                notValidError();
            }
        }
    }

    private void startTextAnimation(String mess,CallbackTextAnimation callbackTextAnimation,String color) {
        service.submit(new Runnable() {
            public void run() {
                textAnimator.animationText(mess,callbackTextAnimation,color);
            }
        });
    }

    @Override
    public void setTextInfoAnimation(String messAnimation,String color) {
        textInfo.setText("<html><font color='"+color+"'>" + messAnimation + "</font></html>");
    }

    private void notValidError() {
        String mess = "ошибка: поле логин или пароль пустое!";
        String color = "red";

        startTextAnimation(mess,this,color);
        setBorderTextFieldsError();
    }

    public void registrationSuccessful() {
        String mess = "новый пользователь успешно добавлен!";
        String color = "green";

        startTextAnimation(mess, this, color);
    }

    public void registrationError() {
        String mess = "ошибка: такой пользователь уже существует!";
        String color = "red";

        startTextAnimation(mess,this,color);
    }

    public void loginError() {
        String mess = "ошибка: не верный логин или пароль!";
        String color = "red";

        startTextAnimation(mess,this,color);
    }

    public void loginSuccessful() {
        String mess = "вход выполнен успешно!";
        String color = "green";

        startTextAnimation(mess,this,color);

        service.submit(new Runnable() {
            public void run() {

                try { Thread.sleep(1500);
               } catch (InterruptedException e) { e.printStackTrace(); }

                SwingUtilities.invokeLater(presenter::navigatePanelsAuthorToAnalytical);
            }
        });
    }
}
