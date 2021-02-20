package com.dinusha.soft;

import com.dinusha.soft.controller.PostController;
import org.apache.log4j.Logger;

import java.io.IOException;

public interface AppStart {
    Logger LOGGER = Logger.getLogger(AppStart.class);

    static void main(String[] args) throws InterruptedException, IOException {
        LOGGER.info("cluster-resource-monitor-client App started");
        try {
            PostController.Post();
        } catch (InterruptedException e) {
            LOGGER.error(e.getStackTrace());
        }
    }
}
