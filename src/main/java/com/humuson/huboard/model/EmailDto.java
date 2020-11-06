package com.humuson.huboard.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailDto {
    private String email;
    private String title;
    private String message;
    private String name;
    private String code;
}
