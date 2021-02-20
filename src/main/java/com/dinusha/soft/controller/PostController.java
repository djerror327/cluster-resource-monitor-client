package com.dinusha.soft.controller;

import com.dinusha.soft.http.client.WebClient;
import com.dinusha.soft.utills.CPU;
import com.dinusha.soft.utills.PropertyReader;
import org.apache.log4j.Logger;

import java.util.Properties;

public interface PostController {

    Logger LOGGER = Logger.getLogger(PostController.class);

    static void Post() throws InterruptedException {

        Properties properties = PropertyReader.READ.get();
        String instanceName = properties.getProperty("instance.name");
        int interval = Integer.parseInt(properties.getProperty("send.data.interval"));
        String url = properties.getProperty("server.url");

        while (true) {
            LOGGER.debug("Interval set to : " + interval + " milliseconds");
            Thread.sleep(interval);
            StringBuilder json = new StringBuilder();
            json.append("{");
            json.append("\"cpu\":\"").append(CPU.USAGE.getAsInt()).append("\",");
            json.append("\"instance\":\"" + instanceName + "\"");
            json.append("}");
            WebClient.POST.accept(url, json);
        }


    }

}
