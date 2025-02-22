package com.example.SchedulingApp.Developed.common.consts;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

//private 접근 제한자로 인스턴스화 방지
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class Const {
    public static final String LOGIN_MEMBER = "loginMember";
}