package com.dinusha.soft;

import com.dinusha.soft.http.client.WebClient;
import com.dinusha.soft.utills.CPU;

public interface AppStart {

    static void main(String[] args) {

        for (int i = 0; i < 100000000; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(CPU.USEAGE.get());

            StringBuilder json = new StringBuilder();
            json.append("{");
            json.append("\"cpu\":\"").append(CPU.USEAGE.get()).append("\",");
            json.append("\"instance\":\"Server1\"");
            json.append("}");
            WebClient.POST.accept("http://localhost:8888/v1/cpu", json);
        }
    }
}
