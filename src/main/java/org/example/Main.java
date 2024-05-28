package org.example;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        API api = new API(15);
        RateLimiter rateLimiter = new RateLimiter(api, 15);

        while (true) {
            Tuple<Integer, String> response = rateLimiter.call("Hi, my friend!");
            System.out.println(response.first + " " + response.second);
            if (response.first == -1) {
                System.out.println("Sleeping for " + api.getPenalty());
                TimeUnit.SECONDS.sleep(api.getPenalty());
            }
        }
    }
}