package com.hanghae.baedalfriend.domain;

import com.hanghae.baedalfriend.dto.requestdto.EventRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //제목
    @Column(nullable = false)
    private String title;

    //내용
    @Column(nullable = false)
    private String content;

    //멤버(관리자)
    @ManyToOne
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    public void update(EventRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public boolean validateMember(Member member) {
        return !this.member.equals(member);
    }

}

