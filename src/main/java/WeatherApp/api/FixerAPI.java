package WeatherApp.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FixerAPI {
    private final static String URL = "http://api.weatherapi.com/v1/current.json?key=";
    private String location;

    public FixerAPI(String location) {
        this.location = location;
    }

    public String getWindSpeed() throws IOException {
        URL url = new URL(URL + new ApiKey().getKey() + "&q=" + location);
        InputStream stream = url.openStream();
        String result =new String(stream.readAllBytes());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(result);
        JsonNode ratesNode = jsonNode.path("current");
        JsonNode ratesNode2 = ratesNode.get("wind_mph");

        return ratesNode2.asText();
    }

    public String getHumidity() throws IOException {
        URL url = new URL(URL + new ApiKey().getKey() + "&q=" + location);
        InputStream stream = url.openStream();
        String result =new String(stream.readAllBytes());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(result);
        JsonNode ratesNode = jsonNode.path("current");
        JsonNode ratesNode2 = ratesNode.get("humidity");

        return ratesNode2.asText();
    }

    public String getTemperatureCondition() throws IOException {
        URL url = new URL(URL + new ApiKey().getKey() + "&q=" + location);
        InputStream stream = url.openStream();
        String result =new String(stream.readAllBytes());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(result);
        JsonNode ratesNode = jsonNode.path("current");
        JsonNode ratesNode2 = ratesNode.path("condition");
        JsonNode ratesNode3 = ratesNode2.get("text");

        return ratesNode3.asText();
    }

    public String getTemperature() throws IOException {
        URL url = new URL(URL + new ApiKey().getKey() + "&q=" + location);
        InputStream stream = url.openStream();
        String result =new String(stream.readAllBytes());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(result);
        JsonNode ratesNode = jsonNode.path("current");
        JsonNode ratesNode2 = ratesNode.get("temp_c");

        return ratesNode2.asText();
    }
}