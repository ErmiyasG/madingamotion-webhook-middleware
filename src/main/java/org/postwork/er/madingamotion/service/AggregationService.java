package org.postwork.er.madingamotion.service;

import org.postwork.er.madingamotion.domain.StudentAggregate;
import org.postwork.er.madingamotion.repository.StudentAggregateRepository;
import org.springframework.stereotype.Service;
import org.postwork.er.madingamotion.domain.StudentActivity;

@Service
public class AggregationService {

    private final Integer HIGH_INTENSITY_BENCHMARK = 80; // eighty percent
    private final StudentAggregateRepository repo;
    private final NotificationService notificationService;

    public AggregationService(StudentAggregateRepository repo, NotificationService notificationService) {

        this.repo = repo;
        this.notificationService = notificationService;
    }

    public void process(StudentActivity activity) {
        StudentAggregate agg = repo.get(activity.studentId);

        agg.activities.add(activity);

        if("BOOKING".equals(activity.type) && "RODA".equals(activity.eventType)) {
            agg.attendedRoda = true;
            agg.xp += 10;
        }

        if("BIOTRACK".equals(activity.type) && activity.intensity > HIGH_INTENSITY_BENCHMARK) {
            agg.highIntensity = true;
            agg.xp += activity.intensity;
        }

        if("RHYTHM".equals(activity.type) && activity.duration >= 20) {
            agg.rhythmMaintained = true;
            agg.xp += activity.duration;
        }

        if(isEligible(agg)) {
            notificationService.send(agg.studentId);
        }

        repo.save(agg);

    }

    private boolean isEligible(StudentAggregate agg) {
        return agg.attendedRoda && agg.highIntensity && agg.rhythmMaintained;
    }
}
