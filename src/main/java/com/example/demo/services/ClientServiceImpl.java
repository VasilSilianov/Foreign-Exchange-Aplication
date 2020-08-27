package com.example.demo.services;

import com.example.demo.services.contracts.ClientService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ClientServiceImpl implements ClientService {

    public static final String URL = "http://apilayer.net/api/live?access_key=2fedc6669786f9ad25c52df8e231f8c4&currencies=EUR,GBP,CAD,PLN&source=USD&format=1";
    private RestTemplate restTemplate = new RestTemplate();

    public ClientServiceImpl() {
    }

    public double exchangeRate(String currencyFrom, String currencyTo){
        String  jsonBody= callCurrencyLayerRestAPI().getBody();
        String concatenationOfTheTwoStrings = currencyFrom+currencyTo;
        if (jsonBody==null|| jsonBody.isEmpty()){
            throw  new IllegalArgumentException("The body of the json should not be empty");
        }
        JsonObject jsonObject = new JsonParser().parse(jsonBody).getAsJsonObject();
        jsonObject=jsonObject.getAsJsonObject("quotes");
        double result = jsonObject.getAsJsonPrimitive(concatenationOfTheTwoStrings).getAsDouble();//
        return result;
    }

    private  ResponseEntity<String> callCurrencyLayerRestAPI() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> result = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
        return result;
    }
}
