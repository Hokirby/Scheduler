package com.example.SchedulingApp.Developed.domain.schedule.repository;

import com.example.SchedulingApp.Developed.domain.schedule.entity.Schedule;
import com.example.SchedulingApp.Developed.exception.ApplicationException;
import com.example.SchedulingApp.Developed.exception.ErrorMessageCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findScheduleById(Long id);
    default Schedule findScheduleByIdOrElseThrow(Long id) {
        return findScheduleById(id)
                .orElseThrow(() -> new ApplicationException(ErrorMessageCode.NOT_FOUND, "Does Not Exist Id =" + id));
    }
    Page<Schedule> findAllByOrderByModifiedAtDesc(Pageable pageable);
}
