package WeatherApp;

import WeatherApp.gui.MainFrame;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MainFrame display = new MainFrame();
        display.setVisible(true);
    }
}