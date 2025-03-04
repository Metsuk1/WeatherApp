package org.example.services;

import com.google.gson.JsonObject;
import org.example.clients.WeatherApiClient;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherGuiService extends JFrame {
    private WeatherApiClient weatherClient;
    private JTextField cityField;
    private JTextArea  weatherArea;

    public WeatherGuiService() {
        weatherClient = new WeatherApiClient();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        setTitle("Weather");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("City:"));

        cityField = new JTextField(15);

        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String city = cityField.getText();
                JsonObject data = weatherClient.getWeatherData(city);
                if (data != null) {
                    displayWeather(data);
                }else {
                    weatherArea.setText("failed to find city");
                }
            }
        });
        panel.add(cityField);
        panel.add(searchButton);
        add(panel, BorderLayout.NORTH);

        weatherArea = new JTextArea(5,30);
        weatherArea.setEditable(false);
        add(new JScrollPane(weatherArea), BorderLayout.CENTER);


    }


    private void displayWeather(JsonObject data) {
        String city = data.get("name").getAsString();
        JsonObject main = data.get("main").getAsJsonObject();
        double temperature = main.get("temp").getAsDouble();
        double humidity = main.get("humidity").getAsDouble();

        String dataInfo =  String.format("City: %s\nTemperature: %.2f°C\nHumidity: %.2f%%",
                city, temperature, humidity);
        weatherArea.setText(dataInfo);

    }

}
