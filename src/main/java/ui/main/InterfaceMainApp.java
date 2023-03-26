package ui.main;

import presentation.Presenter;

import javax.swing.*;

public interface InterfaceMainApp {
    void navigation(JPanel panelRemoved, JPanel panel, Presenter presenter);
    void repaintFrame();
}
