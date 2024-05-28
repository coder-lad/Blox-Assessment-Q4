package org.example;

import java.util.concurrent.TimeUnit;

public class RateLimiter {
    private final API api;
    private final int callsPerMin;
    private final int throttle;

    public RateLimiter(API api, int callsPerMin) {
        this.api = api;
        this.callsPerMin = callsPerMin;
        this.throttle = (int) Math.ceil(60.0 / callsPerMin);
    }

    public Tuple<Integer, String> call(String input) throws InterruptedException {
        TimeUnit.SECONDS.sleep(throttle);
        return api.callMe(input);
    }
}
