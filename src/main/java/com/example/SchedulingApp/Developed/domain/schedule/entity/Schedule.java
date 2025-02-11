package com.example.SchedulingApp.Developed.domain.schedule.entity;

import com.example.SchedulingApp.Developed.config.BaseEntity;
import com.example.SchedulingApp.Developed.domain.comment.entity.Comment;
import com.example.SchedulingApp.Developed.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longText")
    private String content;
    
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")//외래 키
    private Member member;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public Schedule() {
    }

    public Schedule(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
