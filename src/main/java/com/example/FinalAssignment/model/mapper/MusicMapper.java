package com.example.FinalAssignment.model.mapper;


import com.example.FinalAssignment.model.domain.database.AlbumEntity;
import com.example.FinalAssignment.model.domain.database.MusicEntity;
import com.example.FinalAssignment.model.dto.MusicDto;
import com.example.FinalAssignment.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class MusicMapper {



    public MusicDto mapToDto(MusicEntity entity){
        MusicDto dto = new MusicDto();
        dto.setId(entity.getId());
        dto.setGenre(entity.getGenre());
        dto.setAuthor(entity.getAuthor());
        dto.setMusicName(entity.getMusicName());
        dto.setAlbumName(entity.getAlbum().getAlbumName());
        return dto;
    }

    public MusicEntity mapToNewEntity(MusicDto dto, AlbumEntity album){
        MusicEntity entity = new MusicEntity();
        entity.setAuthor(dto.getAuthor());
        entity.setMusicName(dto.getMusicName());
        entity.setGenre(dto.getGenre());
        entity.setAlbum(album);
        return entity;
    }

    public MusicEntity mapToEntity(MusicEntity entity,MusicDto dto){
        entity.setAuthor(dto.getAuthor());
        entity.setMusicName(dto.getMusicName());
        entity.setGenre(dto.getGenre());
        return entity;
    }
}
