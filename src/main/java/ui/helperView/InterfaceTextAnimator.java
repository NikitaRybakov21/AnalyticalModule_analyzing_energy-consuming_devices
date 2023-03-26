package ui.helperView;

import ui.panel.interfacesPanel.CallbackTextAnimation;

public interface InterfaceTextAnimator {
    void animationText(String mess, CallbackTextAnimation callbackTextAnimation,String color);
    void stop();
}
