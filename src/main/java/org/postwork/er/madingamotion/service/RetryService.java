package org.postwork.er.madingamotion.service;

import org.springframework.stereotype.Service;

import java.io.InterruptedIOException;

@Service
public class RetryService {

    public void execute(Runnable action) {
        int attempts = 3; // only allow retrying three times

        for (int i = 0; i < attempts; i++) {
            try {
                action.run();
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        }
        throw new RuntimeException("Failed after retries");

    }
}
