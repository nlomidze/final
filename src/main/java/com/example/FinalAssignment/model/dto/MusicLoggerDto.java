package com.example.FinalAssignment.model.dto;

import lombok.Data;

@Data
public class MusicLoggerDto {
    private Long id;
    private Long userId;
    private String username;
    private MusicDto musicDto;
}
