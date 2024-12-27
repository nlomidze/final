package com.example.FinalAssignment.model.dto;

import com.example.FinalAssignment.model.enums.AlbumStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlbumDto {
    private String albumName;
    private AlbumStatus albumStatus;
    private String albumOwner;
}
