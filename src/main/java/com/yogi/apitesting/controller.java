package com.yogi.apitesting;

import com.yogi.apitesting.Model.MovieData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class controller {

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private MovieData moviedata;


    @GetMapping("")
    public ResponseEntity<Map> func()
    {
        String API_URL = "https://skip-tracing-working-api.p.rapidapi.com/search/byphone?phoneno=(214)349-3972";
        String API_KEY = "8c5cdf7b7dmshb0ed6912443df2cp1877d4jsn966277b6762d";  // Replace with your API key

        HttpHeaders headers = new HttpHeaders();

        headers.set("x-rapidapi-key", API_KEY);
//        headers.set("x-rapidapi-host", "imdb236.p.rapidapi.com");
        System.out.println("Content-Type: " + headers.getContentType());

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(API_URL, HttpMethod.GET, requestEntity, Map.class);
        Map<String,Object> mp= response.getBody();
//        System.out.println("orngriong   " + mp);

        List<Map<String,Object>> rawdata = (List<Map<String, Object>>) mp.get("PeopleDetails");
        for (Map<String, Object> map : rawdata) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }
            System.out.println("  next object      ");
        }
        return response;
    }



    @GetMapping("movies")
    public ResponseEntity<List> func2()
    {
        String API_URL = "https://imdb236.p.rapidapi.com/imdb/lowest-rated-movies";
        String API_KEY = "8c5cdf7b7dmshb0ed6912443df2cp1877d4jsn966277b6762d";  // Replace with your API key

        HttpHeaders headers = new HttpHeaders();

        headers.set("x-rapidapi-key", API_KEY);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List> response = restTemplate.exchange(API_URL, HttpMethod.GET, requestEntity, List.class);
        List lst= response.getBody();
        Map<String, Object> mp = (Map<String, Object>) lst.get(0);


        for (Map.Entry<String, Object> entry : mp.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
        }



        System.out.println(" yogi data is " + mp.keySet());

        return response;
    }



   //usage of Map<String,Object>  , for simple json object use Map<String,Object> , for lisr of json use List<Map<String,Object>>
    @GetMapping("move")
    public ResponseEntity<List> func3()
    {
        String API_URL = "https://imdb236.p.rapidapi.com/imdb/lowest-rated-movies";
        String API_KEY = "8c5cdf7b7dmshb0ed6912443df2cp1877d4jsn966277b6762d";  // Replace with your API key

        HttpHeaders headers = new HttpHeaders();

        headers.set("x-rapidapi-key", API_KEY);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List> response = restTemplate.exchange(API_URL, HttpMethod.GET, requestEntity, List.class);
        List<Map<String, Object>> lst= response.getBody();

        List<MovieData> datalist = lst.stream().map(project -> {
            String id = (String) project.get("id");   //String id = project.get("id").toString()  also ok
            String url = (String) project.get("url");
            String primarytitle = (String) project.get("primaryTitle");
            String originaltitle = (String) project.get("originalTitle");
            Integer styear = (Integer) project.get("startYear");
            return new MovieData(id, url, primarytitle , originaltitle , styear);
        }).collect(Collectors.toList());

        datalist.forEach(data -> System.out.println("data is " + data.toPrint()));

        return response;
    }


   //usage of jsonobject and jsonarray
    @GetMapping("newmove")
    public ResponseEntity<String> func4()
    {
        String API_URL = "https://imdb236.p.rapidapi.com/imdb/lowest-rated-movies";
        String API_KEY = "8c5cdf7b7dmshb0ed6912443df2cp1877d4jsn966277b6762d";  // Replace with your API key

        HttpHeaders headers = new HttpHeaders();

        headers.set("x-rapidapi-key", API_KEY);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.GET, requestEntity, String.class);
        JSONArray responsearray = new JSONArray(response.getBody());
        List<MovieData> datalist = new ArrayList<>();
        for (int i = 0; i < responsearray.length(); i++) {
            JSONObject obj = responsearray.getJSONObject(i);
            String id = obj.getString("id");   //String id = project.get("id").toString()  also ok
            String url = obj.getString("url");
            String primarytitle = obj.getString("primaryTitle");
            String originaltitle = obj.getString("originalTitle");
            Integer styear = obj.getInt("startYear");

//            if there is an  jsonOBject then obj.getJsonObject() ,
//                    we cam use general method like
//                    Object newobject = obj.get("url");
//                    String id = (String) newobject
//
            MovieData data = new MovieData(id, url, primarytitle , originaltitle , styear);
            datalist.add(data);
        }
        datalist.forEach(data -> System.out.println("data is " + data.toPrint()));

        return response;
    }




}
