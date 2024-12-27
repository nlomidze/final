package com.example.FinalAssignment.service;

import com.example.FinalAssignment.model.domain.database.MusicEntity;
import com.example.FinalAssignment.model.domain.database.MusicLogger;
import com.example.FinalAssignment.model.domain.database.UserEntity;
import com.example.FinalAssignment.model.mapper.MusicLoggerMapper;
import com.example.FinalAssignment.repository.MusicLoggerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicLoggerService {
    private final MusicLoggerRepository musicLoggerRepository;

    private MusicLoggerMapper musicLoggerMapper;

    public void saveMusicPlayLog(MusicEntity music, UserEntity user){
        MusicLogger musicLogger = musicLoggerRepository.findByUserIdAndMusicId(user.getId(),music.getId())
                .orElse(musicLoggerMapper.mapToNewEntity(music,user));
        musicLogger.setListenCount(musicLogger.getListenCount()+1);
        musicLoggerRepository.save(musicLogger);
    }

    public List<MusicLogger> getAll(){
        return musicLoggerRepository.findAll();
    }

}
