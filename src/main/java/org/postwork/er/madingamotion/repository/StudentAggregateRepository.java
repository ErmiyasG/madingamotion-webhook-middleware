package org.postwork.er.madingamotion.repository;

import org.postwork.er.madingamotion.domain.StudentAggregate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class StudentAggregateRepository {

    private final ConcurrentHashMap<String, StudentAggregate> store = new ConcurrentHashMap<>();

    public StudentAggregate get(String studentId) {
        return store.computeIfAbsent(studentId, id -> {
            StudentAggregate agg = new StudentAggregate();
            agg.studentId = id;
            return agg;
        });
    }

    public void save(StudentAggregate agg) {
        store.put(agg.studentId, agg);
    }

}
