package org.postwork.er.madingamotion.service;

import org.springframework.stereotype.Service;


@Service
public class NotificationService {

//    private final WebClient

    public  void send(String studentId) {
        System.out.println("Promotion Eligible: " + studentId);
    }
}

