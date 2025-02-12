package com.example.SchedulingApp.Developed.domain.comment.entity;

import com.example.SchedulingApp.Developed.config.BaseEntity;
import com.example.SchedulingApp.Developed.domain.member.entity.Member;
import com.example.SchedulingApp.Developed.domain.schedule.entity.Schedule;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotBlank(message = "Comment Must Be Entered")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Comment() {
    }

    public Comment(Long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public Comment(String comment, Member member, Schedule schedule) {
        this.comment = comment;
        this.member = member;
        this.schedule = schedule;
    }
}
