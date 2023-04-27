package main;

import net.minidev.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    final String uri = "http://localhost:8080";


    public static void main(String[] args){
        Server s = new Server();

        s.getAllProducts();
    }
    public Server() {

    }
    public  void getAllProducts() {
        final String uri = "http://localhost:8080";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
    }

    public void getProductbyID(String ID) {

    }

    public void create(String name, float price, int stock  ) {
        String createProductUri = uri + "/create";
        RestTemplate restTemplate = new RestTemplate();
        JSONObject productJson = new JSONObject();

        Map<String, List<String>> headersMap = new HashMap<>();
        List<String> contentTypeList = new ArrayList<>();
        contentTypeList.add("application/json");
        headersMap.put("Content-Type", contentTypeList);
        HttpHeaders headers = HttpHeaders.of(headersMap);
        headers.setContentType(MediaType.Application_JSON);
        productJson.put("name", name);
        productJson.put("price", price);
        productJson.put("stockLeft", stock);

            HttpEntity<String> request = new HttpEntity<String>(productJson.toString(), headers);
            String responde = restTemplate.postForObject(createProductUri, request, String.class);


    }
//    public static void updateProduct(Integer id, Product product) {
//
//    }
}