package org.example;

import services.weatherGui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new weatherGui().setVisible(true);
            }
        });


    }
}