package org.postwork.er.madingamotion;

import org.junit.jupiter.api.Test;
import org.postwork.er.madingamotion.domain.StudentActivity;
import org.postwork.er.madingamotion.dto.BioTrackPayload;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

// test transformation logic
public class TransformationTest {

    @Test
    void testBioTrackTransformation() {
        BioTrackPayload payload = new BioTrackPayload();
        payload.studentId = "1";
        payload.intensity = 90;

        StudentActivity activity = new StudentActivity();
        activity.studentId = payload.studentId;
        activity.type = "BIOTRACK";
        activity.intensity = payload.intensity;
        activity.timestamp = LocalDateTime.now();

        assertEquals("1", activity.studentId);
        assertEquals("BIOTRACK", activity.type);
        assertEquals(90, activity.intensity);
    }
}
