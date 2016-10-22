package main;

import ui.MainFrame;

public class Intro {
    public static void main(String[] args) {
        MainFrame mainGUI = new MainFrame();
        mainGUI.showRadioButton();
        mainGUI.buttonHandle();
        mainGUI.setVisible(true);
    }
}
