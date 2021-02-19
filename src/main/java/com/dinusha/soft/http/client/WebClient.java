package com.dinusha.soft.http.client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.function.BiConsumer;

public interface WebClient {

    Logger LOGGER = Logger.getLogger(WebClient.class);
    BiConsumer<String, StringBuilder> POST = (url, data) -> {
        HttpPost post = new HttpPost(url);
        // send a JSON data
        try {
            post.setEntity(new StringEntity(data.toString()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(post)) {
                int statusCode = response.getStatusLine().getStatusCode();
                LOGGER.debug("POST : " + url + " | data : " + data);
                LOGGER.debug("POST STATUS :" + statusCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
}
