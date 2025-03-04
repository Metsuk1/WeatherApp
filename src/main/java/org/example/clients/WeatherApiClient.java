package org.example.clients;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.Closeable;
import java.net.http.HttpClient;

public class WeatherApiClient {
    private static final String API_KEY = "506097ffa89a0dfa1f5febf7b127482b";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public JsonObject getWeatherData(String city) {
        String url = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric";

        try(CloseableHttpClient httpClient = HttpClients.createDefault()){ //создаем httpclient
            HttpGet request = new HttpGet(url);                            //создаем get запрос к API
            HttpResponse response = httpClient.execute(request);           //отправляем запрос и ждем ответа от API
            String json = EntityUtils.toString(response.getEntity());      //преобразует ответ в строку с помощью Json

            return JsonParser.parseString(json).getAsJsonObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }



}
