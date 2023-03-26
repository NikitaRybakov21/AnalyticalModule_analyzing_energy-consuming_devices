package ui.helperView;

import ui.panel.interfacesPanel.CallbackTextAnimation;

import javax.swing.*;

public class TextAnimator implements InterfaceTextAnimator{

    @Override
    public synchronized void animationText(String mess, CallbackTextAnimation callbackTextAnimation,String color) {

        StringBuilder messAnimation = new StringBuilder();

        for (int i = 0; i < mess.length(); i++) {
            messAnimation.append(mess.charAt(i));

            try { Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SwingUtilities.invokeLater(() -> callbackTextAnimation.setTextInfoAnimation(messAnimation.toString(),color));
        }
    }

    @Override
    public void stop() { }
}
