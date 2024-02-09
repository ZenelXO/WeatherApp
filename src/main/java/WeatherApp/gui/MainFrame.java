package WeatherApp.gui;

import WeatherApp.api.FixerAPI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class MainFrame extends JFrame {
    private JTextField clientLabel = new JTextField();
    private JButton searchButton = new JButton();
    private JLabel weatherImage = new JLabel();
    private JLabel temperature = new JLabel();
    private JLabel temperatureCondition = new JLabel();
    private JLabel humidity = new JLabel();
    private JLabel humidityText = new JLabel();
    private JLabel humidityResult = new JLabel();
    private JLabel windSpeedImage = new JLabel();
    private JLabel windSpeedText = new JLabel();
    private JLabel windSpeedResult = new JLabel();
    public MainFrame() throws HeadlessException, IOException {
        this.setTitle("Weather App");
        this.setSize(450,650);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
            //Componentes
            this.add(loadClientLabel());
            this.add(loadSearchButton());
            this.add(loadWeatherImage());
            this.add(loadTemperatureText());
            this.add(loadTemperatureConditionText());
            this.add(loadHumidityLabel());
            this.add(loadHumidityText());
            this.add(loadHumidityResult());
            this.add(loadWindSpeedImage());
            this.add(loadWindSpeedText());
            this.add(loadWindSpeedResult());
    }

    private Component loadWindSpeedResult() {
        windSpeedResult.setText("4.1 km/h");
        windSpeedResult.setBounds(330,510,100,75);
        windSpeedResult.setFont(new Font("Dialog", Font.PLAIN, 14));

        return windSpeedResult;
    }

    private Component loadWindSpeedText() {
        windSpeedText.setText("WindSpeed");
        windSpeedText.setBounds(330,485,100,75);
        windSpeedText.setFont(new Font("Dialog", Font.BOLD, 14));

        return windSpeedText;
    }

    private Component loadWindSpeedImage() throws IOException {
        BufferedImage image = ImageIO.read(new File("src/main/java/WeatherApp/assets/windspeed.png"));
        windSpeedImage.setIcon(new ImageIcon(image));
        windSpeedImage.setBounds(240,505,100,75);


        return windSpeedImage;
    }

    private Component loadHumidityResult() {
        humidityResult.setText("63%");
        humidityResult.setBounds(95,510,100,75);
        humidityResult.setFont(new Font("Dialog", Font.PLAIN, 14));

        return humidityResult;
    }

    private Component loadHumidityText() {
        humidityText.setText("Humidity");
        humidityText.setBounds(95,485,100,75);
        humidityText.setFont(new Font("Dialog", Font.BOLD, 14));

        return humidityText;
    }

    private Component loadHumidityLabel() throws IOException {
        BufferedImage image = ImageIO.read(new File("src/main/java/WeatherApp/assets/humidity.png"));
        humidity.setIcon(new ImageIcon(image));
        humidity.setBounds(20,490,100,100);

        return humidity;
    }

    private Component loadTemperatureConditionText() {
        temperatureCondition.setText("Clear");
        temperatureCondition.setBounds(0,400,450,50);
        temperatureCondition.setFont(new Font("Dialog", Font.PLAIN, 32));
        temperatureCondition.setHorizontalAlignment(SwingConstants.CENTER);

        return temperatureCondition;
    }

    private Component loadTemperatureText() {
        temperature.setText("23 ºC");
        temperature.setBounds(160,270,200,200);
        temperature.setFont(new Font("Dialog", Font.BOLD, 48));

        return temperature;
    }

    private Component loadWeatherImage() throws IOException {
        weatherImage.setBounds(95,50,300,300);
        BufferedImage image = ImageIO.read(new File("src/main/java/WeatherApp/assets/clear.png"));
        weatherImage.setIcon(new ImageIcon(image));

        return weatherImage;
    }

    private Component loadSearchButton() throws IOException {
        searchButton.setBounds(375,15,44,44);
        BufferedImage image = ImageIO.read(new File("src/main/java/WeatherApp/assets/search.png"));
        searchButton.setIcon(new ImageIcon(image));
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Update gui
                String location = clientLabel.getText();
                FixerAPI cityWeather = new FixerAPI(clientLabel.getText());
                try {
                    temperature.setText(cityWeather.getTemperature() + " ºC");
                    temperatureCondition.setText(cityWeather.getTemperatureCondition());
                    humidityResult.setText(cityWeather.getHumidity() + "%");
                    windSpeedResult.setText(convertMphToKmh(cityWeather.getWindSpeed()) + " km/h");

                    //Switch images between weathers conditions
                    switch (cityWeather.getTemperatureCondition()){
                        case "Clear":
                            weatherImage.setIcon(loadImages("clear"));
                            break;
                        case "Sunny":
                            weatherImage.setIcon(loadImages("clear"));
                            break;
                        case "Partly cloudy":
                            weatherImage.setIcon(loadImages("cloudy"));
                            break;
                        case "Cloudy":
                            weatherImage.setIcon(loadImages("cloudy"));
                            break;
                        case "Light snow":
                            weatherImage.setIcon(loadImages("snow"));
                            break;
                        case "Light drizzle":
                            weatherImage.setIcon(loadImages("rain"));
                            break;
                        case "Light rain":
                            weatherImage.setIcon(loadImages("rain"));
                            break;
                    }
                } catch (IOException ex) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "City not found!");
                }
            }
        });

        return searchButton;
    }

    private Component loadClientLabel() {
        clientLabel.setFont(new Font("Dialog", Font.PLAIN,24));
        clientLabel.setBounds(15,15,351,45);

        return clientLabel;
    }

    private String convertMphToKmh(String windSpeed) {
        double windspeed = Double.parseDouble(windSpeed);
        double speed = windspeed * 1.6093;
        DecimalFormat df = new DecimalFormat("#.##");
        String result = String.valueOf(df.format(speed));

        return result;
    }

    private ImageIcon loadImages(String condition) throws IOException {
        BufferedImage image = ImageIO.read(new File("src/main/java/WeatherApp/assets/" + condition + ".png"));
        ImageIcon finalImage = new ImageIcon(image);

        return finalImage;
    }
}