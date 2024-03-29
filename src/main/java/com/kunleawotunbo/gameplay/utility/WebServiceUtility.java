/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kunleawotunbo.gameplay.bean.SMSConfigBean;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Olakunle Awotunbo
 */
public class WebServiceUtility {

    @Autowired
    private SMSConfigBean smsConfigBean;
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    public String getRestClient3(String resource, String jsonstring) {

        final String uri = "http://localhost:8080/springrestexample/employees.xml";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
        return result;
    }

    @Async
    public void getRestClient(SMSConfigBean smsConfigBean, String recepientPhone, String message) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("About to send sms");
        /*
        try {
            Thread.sleep(60000);
            System.out.println("I just want to delay this ni");
            // return new AsyncResult<String>("hello world !!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         */

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("username", smsConfigBean.getUsername());
        params.add("password", smsConfigBean.getPassword());
        params.add("sender", smsConfigBean.getSender());
        params.add("to", recepientPhone);
        params.add("message", message);
        params.add("reqid", smsConfigBean.getReqid());
        params.add("format", smsConfigBean.getFormat());
        // params.add("route_id", smsConfigBean.getRoute_id());
        params.add("callback", smsConfigBean.getCallback());
        params.add("unique", smsConfigBean.getUnique());
        params.add("sendondate", smsConfigBean.getUnique());
        params.add("msgtype", smsConfigBean.getUnique());

        //UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("http://spsenthil.com/order").queryParams(params).build();
        // ListenableFuture<ResponseEntity<String>> responseFuture = restTemplate.getForEntity(uriComponents.toUriString(), String.class);
        System.out.println("params :: " + params);
        // Add the Jackson message converter
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity<String> response = restTemplate.postForEntity(smsConfigBean.getUri(), params, String.class);
        //response.g
        // restTemplate.

        // Object result = restTemplate.postForObject(smsConfigBean.getUri(), obj, String.class);
        System.out.println("response :: " + response);
        System.out.println("response.getBody() :: " + response.getBody());
        // System.out.println("result :: " + result);

        System.out.println("SMS sent");

    }

    /**
     * Get country details by ipaddress using http://ip-api.com/
     * @param resourceUrl
     * @param ipAddress
     * @return 
     */
    public JsonNode getIPObjectRestClient(String resourceUrl, String ipAddress) {
        RestTemplate restTemplate = new RestTemplate();
        
        //UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("http://spsenthil.com/order").queryParams(params).build();
        // ListenableFuture<ResponseEntity<String>> responseFuture = restTemplate.getForEntity(uriComponents.toUriString(), String.class);
        //System.out.println("params :: " + params);
        // Add the Jackson message converter
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        // ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, String.class);

        String a = resourceUrl + ipAddress;

        String fooResourceUrl
                = "http://localhost:8080/spring-rest/foos";
        ResponseEntity<String> response
                = restTemplate.getForEntity(resourceUrl + ipAddress, String.class);
        //assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = mapper.readTree(response.getBody());
            logger.info("root :: " + root);
        } catch (IOException ex) {
            Logger.getLogger(WebServiceUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        JsonNode name = root.path("name");

        // Object result = restTemplate.postForObject(smsConfigBean.getUri(), obj, String.class);
        //System.out.println("response :: " + response);
        //System.out.println("response.getBody() :: " + response.getBody());
        // System.out.println("result :: " + result);
        System.out.println("SMS sent");

        return root;

    }

}
