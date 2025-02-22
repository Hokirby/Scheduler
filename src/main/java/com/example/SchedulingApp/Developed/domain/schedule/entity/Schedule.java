package com.example.SchedulingApp.Developed.domain.schedule.entity;

import com.example.SchedulingApp.Developed.common.entity.BaseEntity;
import com.example.SchedulingApp.Developed.domain.comment.entity.Comment;
import com.example.SchedulingApp.Developed.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    //대용량 문자 데이터 저장 형식 CLOB
    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")//외래 키
    private Member member;

    private int commentCount = 0;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public Schedule(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void setFoundMember(Member foundMember) {
        this.member = foundMember;
    }

    public void increaseCommentCount(int commentCount) {
        this.commentCount = commentCount + 1;
    }

    public void decreaseCommentCount(int commentCount) {
        this.commentCount = commentCount - 1;
    }

}
