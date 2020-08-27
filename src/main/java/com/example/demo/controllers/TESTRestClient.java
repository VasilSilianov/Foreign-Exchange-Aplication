package com.example.demo.controllers;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class TESTRestClient {

    public static final String URL = "http://apilayer.net/api/live?access_key=2fedc6669786f9ad25c52df8e231f8c4&currencies=EUR,GBP,CAD,PLN&source=USD&format=1";

    static RestTemplate restTemplate = new RestTemplate();


//    private class QuotesObj {
//        public double USDEUR;
//        public double USDGBP;
//        public double USDCAD;
//        public double USDPLN;
//    }
    public static void main(String[] args)  {
      String result=  callCurrencyLayerRestAPI().getBody();
        if ( result ==null || result.isEmpty()){
            throw new IllegalArgumentException(" sdsad");
        }
        JsonObject propertiesWrapper = new JsonParser().parse(result).getAsJsonObject();
        propertiesWrapper = propertiesWrapper.getAsJsonObject("quotes");
        double value = propertiesWrapper.getAsJsonPrimitive("USDEUR").getAsDouble();
        System.out.println(value);



      //
   //     ObjectMapper mapper = new ObjectMapper();
//        mapper.readValue();
//        JSONParser parser = new JSONParser();
//        JSONObject json = (JSONObject) parser.parse(result);

//        System.out.println("USDEUR");
//        System.out.println(json.get("q..).get("USDEUR"));
//
// e dade ama kak
// nemojah da go naglssq ok
        /*
        koi da izbera probvah  vsichkite
        apacheto beshe posledniq  zashto s nego stana bez da podavam argumenti ujsega pishti za exeption
        sega neznam kvo se sluchi
        prosto govori
        sek sega shte go potyrsq

         */


//        System.out.println(getCurrencyRate2(result,"USDEUR"));
//
//        System.out.println( result);
//
//        System.out.println("tova e resultata");
//
//         //double newReulst = getCurrencyRate(result,"USDEUR");
//        System.out.println("Exchange rate is:");
//        System.out.println(getCurrencyRate(result,"USDPLN"));
    }


    private static ResponseEntity<String>  callCurrencyLayerRestAPI() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> result = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

        return result;
    }

    private static double getCurrencyRate(String JASONText,String currency){
        double result = 0;
        String newCurrency = "\""+currency+"\"";
       // System.out.println(currency);
        //System.out.println(newCurrency);
        String arr = JASONText.split(" \"quotes\":\\{" )[1];
        String[] arr1 =arr.split(",");

        String[][] arr2  = new String[arr1.length][2];
        for (int i = 0; i <arr2.length ; i++) {
            arr2[i][0] = arr1[i].split(":")[0].trim();
//            System.out.println(arr2[i][0]);
          //  double buffer = Double.parseDouble(arr1[i].split(":")[1]);
            arr2[i][1] = arr1[i].split(":")[1].replaceAll("}","").trim();
//            System.out.println(arr2[i][1]);
        }

        for (int i = 0; i <arr2.length; i++) {
//            System.out.println(arr2[i][0]);
            if (arr2[i][0].equalsIgnoreCase(newCurrency)){
                result=  Double.parseDouble(arr2[i][1]);
            }
        }

        return result;
    }

    private static double getCurrencyRate2(String JASONText,String currency){
      //  JSONParser parser = new JSONParser();
       // JSONObject json = (JSONObject) parser.parse(JASONText);



        double result =0;
//        String newCurrency = "\""+currency+"\"";
//        System.out.println("we start here");
//        for (int i = 25; i<json.length() ; i++) {
//       //     System.out.println(parser.getToken(i).toString());
//            if (json.get().equalsIgnoreCase(newCurrency)){
//                result = Double.parseDouble(parser.getToken(i+2).toString());
//                break;
//            }
//        }
        return result;
    }
}
