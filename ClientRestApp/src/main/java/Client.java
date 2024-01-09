import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        final String name = "Sensor3";

        registerSensor(name);

        Random random = new Random();

        double minTemp = 0.0;
        double maxTemp = 45.0;
        for (int i = 0; i < 500 ; i++) {
            System.out.println(i);
            sendMeasurement(random.nextDouble() * maxTemp  , random.nextBoolean(), name);
        }
    }

    private static void registerSensor(String name) {
        final String url = "http://localhost:8090/sensors/registration";
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("name", name);
        makePostRequestWithJSONData(url, jsonData);
    }

    private static void sendMeasurement(double value, boolean raining, String name ) {
        final String url = "http://localhost:8090/measurements/add";
        Map <String, Object> jsonData = new HashMap<>();
        jsonData.put("value", value);
        jsonData.put("raining", raining);
        jsonData.put("sensor", Map.of("name" , name));


        makePostRequestWithJSONData(url, jsonData);
    }


    private static void makePostRequestWithJSONData(String url, Map<String, Object> jsonData) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);


        try{
            restTemplate.postForObject(url, request, String.class);
            System.out.println("ok");
        }
        catch(HttpClientErrorException e){
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
    }
}