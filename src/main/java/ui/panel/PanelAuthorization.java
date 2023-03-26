package ui.panel;

import presentation.Presenter;
import ui.componentsView.MaterialButton;
import ui.main.MainApp;
import ui.panel.interfacesPanel.InterfacePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.main.MainApp.baseUrlImage;

public class PanelAuthorization implements ActionListener , InterfacePanel {

    private final JPanel panelAuthorization = new JPanel();

    private final GridBagLayout gridBagLayout = new GridBagLayout();

    private final JTextField textFieldPatronymic = new JTextField(15);
    private final JTextField textFieldSurname = new JTextField(15);

    private final MaterialButton buttonLogin = new MaterialButton("Test", baseUrlImage + "buttonLogin.png",baseUrlImage + "buttonLogin_pressed.png",338/2,114/2);
    private final MaterialButton buttonRegistration = new MaterialButton("Test", baseUrlImage + "buttonRegistration.png", baseUrlImage + "buttonRegistration_pressed.png", 338/2, 114/2);

    private final GridBagConstraints constraints =  new GridBagConstraints();

    private final Presenter presenter;

    public PanelAuthorization(Presenter presenter) {
        this.presenter = presenter;

        panelAuthorization.setBackground(MainApp.getRGBColor(254,254,254));
        panelAuthorization.setLayout(gridBagLayout);


        Font fontHeader = new Font("Verdana", Font.PLAIN, 28);
        JLabel labelAuthorization = new JLabel("Авторизаци пользователя");
        labelAuthorization.setFont(fontHeader);
        addComponent(0,0, labelAuthorization,new Insets(0, 0, 0,0),2,0,GridBagConstraints.CENTER);


        Font fontName = new Font("Verdana", Font.PLAIN, 11);
        JLabel labelName = new JLabel("логин");
        labelName.setFont(fontName);
        addComponent(0,1, labelName,new Insets(14, 0, 0,0),2,0,GridBagConstraints.HORIZONTAL);


        JTextField textFieldName = new JTextField(15);
        addComponent(0,2, textFieldName,new Insets(2, 0, 0,0),2,20,GridBagConstraints.HORIZONTAL);


        JLabel labelPassword = new JLabel("пароль");
        labelPassword.setFont(fontName);
        addComponent(0,3, labelPassword,new Insets(14, 0, 0,0),2,0,GridBagConstraints.HORIZONTAL);


        JPasswordField textFieldPassword = new JPasswordField(15);
        addComponent(0,4, textFieldPassword,new Insets(2, 0, 0,0), 2,20,GridBagConstraints.HORIZONTAL);


        addComponent(0,5, buttonLogin,new Insets(20, 0, 0,0),1,0,GridBagConstraints.HORIZONTAL);
        addComponent(1,5, buttonRegistration, new Insets(20, 100, 0,0),1,0,GridBagConstraints.HORIZONTAL);

        buttonLogin.addActionListener(this);
        buttonRegistration.addActionListener(this);
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
        if(event.getSource() == buttonLogin) {
           presenter.navigatePanelsAuthorToAnalytical();

           //System.out.println(textFieldPassword.getText());
        }
        if(event.getSource() == buttonRegistration) { }
    }
}
