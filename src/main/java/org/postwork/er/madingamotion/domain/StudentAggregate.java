package org.postwork.er.madingamotion.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentAggregate {
    public String studentId;
    public boolean attendedRoda;
    public boolean highIntensity;
    public boolean rhythmMaintained;
    public int xp;
    public List<StudentActivity> activities = new ArrayList<>();
    public LocalDateTime createdAt = LocalDateTime.now();
}
