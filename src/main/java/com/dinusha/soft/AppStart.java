package com.dinusha.soft;

import com.dinusha.soft.http.client.WebClient;

public interface AppStart {

    static void main(String[] args) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"cpu\":\"10\",");
        json.append("\"instance\":\"Server1\"");
        json.append("}");
        WebClient.POST.accept("http://localhost:8888/v1/cpu", json);
    }
}
