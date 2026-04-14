package org.postwork.er.madingamotion;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postwork.er.madingamotion.domain.StudentActivity;
import org.postwork.er.madingamotion.domain.StudentAggregate;
import org.postwork.er.madingamotion.repository.StudentAggregateRepository;
import org.postwork.er.madingamotion.service.AggregationService;
import org.postwork.er.madingamotion.service.NotificationService;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

// test XP calculation logic
public class XPServiceTest {

    private AggregationService service;
    private StudentAggregateRepository repo;

    @BeforeEach
    void setup() {
        repo = new StudentAggregateRepository();
        service = new AggregationService(repo,  new NotificationService(null, null));
    }

    @Test
    void textXPFromBooking() {
        StudentActivity activity = new StudentActivity();
        activity.studentId = "1";
        activity.type = "BOOKING";
        activity.eventType = "RODA";
        activity.timestamp = LocalDateTime.now();

        service.process(activity);

        StudentAggregate agg = repo.get("1");
        assertEquals(10, agg.xp);
        assertTrue(agg.attendedRoda);
    }

    @Test
    void testXPFromBioTrack() {
        StudentActivity activity = new StudentActivity();
        activity.studentId = "1";
        activity.type = "BIOTRACK";
        activity.intensity = 85.0;
        activity.timestamp = LocalDateTime.now();

        service.process(activity);

        StudentAggregate agg = repo.get("1");
        assertTrue(agg.highIntensity);
        assertEquals(85, agg.xp);
    }

    @Test
    void testXPFromRhythm() {
        StudentActivity activity = new StudentActivity();
        activity.studentId = "1";
        activity.type = "RHYTHM";
        activity.duration = 30;
        activity.timestamp = LocalDateTime.now();

        service.process(activity);

        StudentAggregate agg = repo.get("1");
        assertTrue(agg.rhythmMaintained);
        assertEquals(30, agg.xp);
    }

    @Test
    void testFullEligibiltyXP() {
        StudentActivity booking = new StudentActivity();
        booking.studentId = "1";
        booking.type = "BOOKING";
        booking.eventType = "RODA";
        booking.timestamp = LocalDateTime.now();

        StudentActivity bio = new StudentActivity();
        bio.studentId = "1";
        bio.type = "BIOTRACK";
        bio.intensity = 85.0;
        bio.timestamp = LocalDateTime.now();

        StudentActivity rhythm = new StudentActivity();
        rhythm.studentId = "1";
        rhythm.type = "RHYTHM";
        rhythm.duration = 25;
        rhythm.timestamp = LocalDateTime.now();

        service.process(booking);
        service.process(bio);
        service.process(rhythm);

        StudentAggregate agg = repo.get("1");

        assertTrue(agg.attendedRoda);
        assertTrue(agg.highIntensity);
        assertTrue(agg.rhythmMaintained);

        assertEquals(10 + 85 + 25, agg.xp);
    }
}
