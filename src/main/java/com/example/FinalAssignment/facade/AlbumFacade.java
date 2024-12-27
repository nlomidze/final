package com.example.FinalAssignment.facade;

import com.example.FinalAssignment.model.domain.database.AlbumEntity;
import com.example.FinalAssignment.model.domain.database.UserEntity;
import com.example.FinalAssignment.model.dto.AlbumDto;
import com.example.FinalAssignment.model.enums.AlbumStatus;
import com.example.FinalAssignment.model.mapper.AlbumMapper;
import com.example.FinalAssignment.model.param.AddAlbumParam;
import com.example.FinalAssignment.model.param.UpdateAlbumParam;
import com.example.FinalAssignment.security.JwtService;
import com.example.FinalAssignment.service.AlbumService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlbumFacade {

    private final AlbumService albumService;
    private final JwtService jwtService;
    private AlbumMapper albumMapper;

    // ------------------------------- Add Album --------------------------------
    public AlbumDto addAlbum(AddAlbumParam addAlbumParam, HttpServletRequest request) {
        AlbumEntity album = new AlbumEntity();
        album.setAlbumName(addAlbumParam.getAlbumName());
        album.setAlbumStatus(AlbumStatus.ACTIVE);
        album.setUserEntity(jwtService.getUser(request));
        albumService.saveAlbum(album);
        return albumMapper.toAlbumDto(album);
    }

    public AlbumDto updateAlbum(UpdateAlbumParam param, HttpServletRequest request) {
        return albumMapper.toAlbumDto(albumService.updateAlbum(param,jwtService.getUser(request)));
    }

    public List<AlbumDto> getAll(){
        return albumService.getAll().stream()
                .map(albumMapper::toAlbumDto)
                .collect(Collectors.toList());
    }

    public List<AlbumDto> getUserPlaylists(HttpServletRequest request){
        return albumService.getUserPlaylists(jwtService.getUser(request)).stream()
                .map(albumMapper::toAlbumDto)
                .collect(Collectors.toList());
    }

    public void delete(Long albumId,HttpServletRequest request){
        albumService.deleteAlbum(albumId,jwtService.getUser(request));
    }
}
