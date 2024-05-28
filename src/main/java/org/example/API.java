package org.example;

import java.util.Deque;
import java.util.LinkedList;


public class API {
    private int callNumber;
    private final int penalty;
    private final int callLimit;
    private final Deque<Long> timestamps;

    public int getPenalty(){
        return penalty;
    }

    public API(int callLimit) {
        this.callNumber = -1;
        this.penalty = 60;
        this.callLimit = callLimit;
        this.timestamps = new LinkedList<>();
        for (int i = 0; i < callLimit; i++) {
            timestamps.add(Long.MIN_VALUE);
        }
    }

    public Tuple<Integer, String> callMe(String message) {
        int status = 0;
        String response = "Passed: " + message;
        long curTime = System.currentTimeMillis() / 1000L;
        callNumber++;
        if (callNumber >= callLimit) {
            long prevTime = timestamps.getFirst();
            if (curTime - prevTime < 60) {
                System.out.println("Limit Breached!");
                timestamps.clear();
                for (int i = 0; i < callLimit; i++) {
                    timestamps.add(curTime);
                }
                timestamps.removeLast();
                timestamps.addLast(curTime + penalty);
                status = -1;
                response = "Limit Breached!, next call to be made after - " + timestamps.getLast();
            } else {
                timestamps.pollFirst();
                timestamps.addLast(curTime);
            }
        } else {
            timestamps.pollFirst();
            timestamps.addLast(curTime);
        }
        return new Tuple<>(status, response);
    }
}
