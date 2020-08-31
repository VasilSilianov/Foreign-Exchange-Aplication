package com.example.demo.services;

import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.services.contracts.ClientService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@Service
public class ClientServiceImpl implements ClientService {

    public static final String URL = "http://apilayer.net/api/live?access_key=2fedc6669786f9ad25c52df8e231f8c4&currencies=EUR,GBP,CAD,PLN&source=USD&format=1";
    public  static  final String NOT_EXISTING_CURRENCY =    "Not existing currency";
    private RestTemplate restTemplate = new RestTemplate();

    public ClientServiceImpl() {
    }


    /**
     *
     * @param currencyFrom String parameter that represents source currency
     * @param currencyTo String parameter that represents target currency
     * @return This method returns double value that represents the the exchange rate between the two currencies
     */
    @Override
    public double exchangeRate(String currencyFrom, String currencyTo){
        String  jsonBody= callCurrencyLayerRestAPI().getBody();
        String concatenationOfTheTwoStrings = currencyFrom+currencyTo;
        if (jsonBody==null|| jsonBody.isEmpty()){
            throw  new IllegalArgumentException("The body of the json should not be empty");
        }
        try{

        JsonObject jsonObject = new JsonParser().parse(jsonBody).getAsJsonObject();
        jsonObject=jsonObject.getAsJsonObject("quotes");
         return jsonObject.getAsJsonPrimitive(concatenationOfTheTwoStrings).getAsDouble();
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(NOT_EXISTING_CURRENCY));
        }
    }

    /**
     *  This method Calls the  REST Service of a web site, where we can get the exchange services
     * @return Object of type  ResponseEntity<String> that is the body of the JSON that we call from the REST Service of the site
     */
    private  ResponseEntity<String> callCurrencyLayerRestAPI() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> result = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
        return result;
    }
}
