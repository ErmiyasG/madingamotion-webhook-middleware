package org.postwork.er.madingamotion.controller;

import org.postwork.er.madingamotion.domain.StudentActivity;
import org.postwork.er.madingamotion.dto.BioTrackPayload;
import org.postwork.er.madingamotion.dto.BookingPayload;
import org.postwork.er.madingamotion.dto.RhythmPayload;
import org.postwork.er.madingamotion.service.AggregationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final AggregationService service;

    public WebhookController(AggregationService service) {
        this.service = service;

    }

    @PostMapping("/booking")
    public void booking(@RequestBody BookingPayload payload) {
        StudentActivity a = new StudentActivity();
        a.studentId = payload.studentId;
        a.type = "BOOKING";
        a.eventType = payload.eventType;
        a.timestamp = LocalDateTime.now();
        service.process(a);
    }

    @PostMapping("/biotrack")
    public void bitrack(@RequestBody BioTrackPayload payload) {
        StudentActivity a = new StudentActivity();
        a.studentId = payload.studentId;
        a.type = "BIOTRACK";
        a.intensity = payload.intesity;
        a.timestamp = LocalDateTime.now();

        service.process(a);
    }

    @PostMapping("/rhythm")
    public void rhythm(@RequestBody RhythmPayload payload) {
        StudentActivity a = new StudentActivity();
        a.studentId = payload.studentId;
        a.type = "RHYTHM";
        a.duration = payload.duration;
        a.timestamp = LocalDateTime.now();

        service.process(a);
    }
}
