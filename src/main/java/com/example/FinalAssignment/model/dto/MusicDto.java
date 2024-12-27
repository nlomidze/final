package com.example.FinalAssignment.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MusicDto {
    private Long id;
    private String musicName;
    private String genre;
    private String author;
    private String albumName;
    private Long albumId;
    private String userName;
}
