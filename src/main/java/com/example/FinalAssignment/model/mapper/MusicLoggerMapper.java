package com.example.FinalAssignment.model.mapper;

import com.example.FinalAssignment.model.domain.database.MusicEntity;
import com.example.FinalAssignment.model.domain.database.MusicLogger;
import com.example.FinalAssignment.model.domain.database.UserEntity;
import com.example.FinalAssignment.model.dto.MusicDto;
import com.example.FinalAssignment.model.dto.MusicLoggerDto;

public class MusicLoggerMapper {

    private MusicMapper musicMapper;
    public MusicLoggerDto mapToDto(MusicLogger entity){
        MusicLoggerDto dto = new MusicLoggerDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setUsername(entity.getUser().getUsername());
        dto.setMusicDto(musicMapper.mapToDto(entity.getMusic()));
        return dto;
    }

    public MusicLogger mapToNewEntity(MusicEntity music, UserEntity user){
        MusicLogger musicLogger = new MusicLogger();
        musicLogger.setMusic(music);
        musicLogger.setUser(user);
        musicLogger.setListenCount(0L);
        return musicLogger;
    }



}
