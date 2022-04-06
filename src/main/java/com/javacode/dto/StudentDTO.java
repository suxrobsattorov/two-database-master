package com.javacode.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private Integer id;
    private String name;
    private String surname;
    private String level;
    private Integer groupId;
}
