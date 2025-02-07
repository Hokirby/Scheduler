package com.example.SchedulingApp.Developed.schedule.repository;

import com.example.SchedulingApp.Developed.schedule.entity.Schedule;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findScheduleById(Long id);
    default Schedule findScheduleByIdOrElseThrow(Long id) {
        return findScheduleById(id)
                .orElseThrow(() -> new EntityNotFoundException("Does Not exist id =" + id));
    }
}
