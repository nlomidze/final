package com.example.FinalAssignment.service;

import com.example.FinalAssignment.model.domain.database.AlbumEntity;
import com.example.FinalAssignment.model.domain.database.UserEntity;
import com.example.FinalAssignment.model.enums.AlbumStatus;
import com.example.FinalAssignment.model.enums.UserRole;
import com.example.FinalAssignment.model.param.UpdateAlbumParam;
import com.example.FinalAssignment.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;

    @Transactional
    public AlbumEntity saveAlbum(AlbumEntity album) {
        return albumRepository.saveAndFlush(album);
    }

    public AlbumEntity updateAlbum(UpdateAlbumParam param,UserEntity loggedUser){
        AlbumEntity album = getById(param.getAlbumId());
        if(!album.getUserEntity().getId().equals(loggedUser.getId()) && !loggedUser.getRole().equals(UserRole.ROLE_ADMIN)){
            throw new RuntimeException("insufficient permissions");
        }

        album.setAlbumStatus(AlbumStatus.valueOf(param.getAlbumStatus()));
        album.setAlbumName(param.getAlbumName());
        albumRepository.save(album);
        return  album;
    }

    public List<AlbumEntity> getAll(){
        return albumRepository.findAll();
    }

    public List<AlbumEntity> getUserPlaylists(UserEntity user){
        return albumRepository.findAllByUserEntityId(user.getId());
    }

    @Transactional
    public void deleteAlbum(Long albumId,UserEntity loggedUser){
        AlbumEntity album = getById(albumId);
        if(!album.getUserEntity().getId().equals(loggedUser.getId()) && !loggedUser.getRole().equals(UserRole.ROLE_ADMIN)){
            throw new RuntimeException("insufficient permissions");
        }
        albumRepository.delete(album);
    }

    public AlbumEntity getById(Long albumId){
        return albumRepository.findById(albumId).orElseThrow(()->new RuntimeException("albumId not found"));
    }
}
