package com.humuson.huboard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class MemberLineDto {
    private String yearMonth;
    private int cntMember;
}
