package org.example;

import org.example.services.WeatherGuiService;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WeatherGuiService().setVisible(true);
            }
        });


    }
}