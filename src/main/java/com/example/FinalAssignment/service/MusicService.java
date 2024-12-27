package com.example.FinalAssignment.service;

import com.example.FinalAssignment.model.domain.database.MusicEntity;
import com.example.FinalAssignment.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {

    private MusicRepository musicRepository;

    public List<MusicEntity> getMusicsByName(String name){
        if(name==null || name.isEmpty()){
            return musicRepository.findAll();
        }
        return musicRepository.findAllByMusicName(name);
    }

    public MusicEntity getById(Long id){
        return musicRepository.findById(id).orElseThrow(()-> new RuntimeException("music not found"));
    }

    public MusicEntity saveMusic(MusicEntity music){
        return musicRepository.save(music);
    }

    public void deleteMusic(MusicEntity music){
        musicRepository.delete(music);
    }
}
