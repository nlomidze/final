package com.example.FinalAssignment.facade;

import com.example.FinalAssignment.model.domain.database.MusicEntity;
import com.example.FinalAssignment.model.domain.database.UserEntity;
import com.example.FinalAssignment.model.dto.MusicDto;
import com.example.FinalAssignment.model.enums.UserRole;
import com.example.FinalAssignment.model.mapper.MusicMapper;
import com.example.FinalAssignment.repository.AlbumRepository;
import com.example.FinalAssignment.security.JwtService;
import com.example.FinalAssignment.service.AlbumService;
import com.example.FinalAssignment.service.MusicLoggerService;
import com.example.FinalAssignment.service.MusicService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MusicFacade {
    private final MusicService musicService;
    private final MusicLoggerService musicLoggerService;
    private final JwtService jwtService;
    private final AlbumRepository albumRepository;

    private MusicMapper musicMapper;

    public MusicDto getMusicById(Long id, HttpServletRequest request){
        MusicEntity music =  musicService.getById(id);
        musicLoggerService.saveMusicPlayLog(music,jwtService.getUser(request));
        return musicMapper.mapToDto(music);

    }

    public List<MusicDto> getMusicListByName(String name){
        return musicService.getMusicsByName(name).stream()
                .map(musicMapper::mapToDto).collect(Collectors.toList());
    }

    public MusicDto createMusic(MusicDto dto, HttpServletRequest request) {
        return musicMapper.mapToDto(musicService.saveMusic(
                musicMapper.mapToNewEntity(dto, albumRepository.findById(dto.getAlbumId()).orElseThrow(() -> new RuntimeException("album not found")))
        ));
    }

    public MusicDto saveMusic(MusicDto dto, HttpServletRequest request) {
        MusicEntity music = musicService.getById(dto.getId());
        UserEntity loggedUser = jwtService.getUser(request);
        if(!music.getAlbum().getUserEntity().getId().equals(loggedUser.getId()) && !loggedUser.getRole().equals(UserRole.ROLE_ADMIN)){
            throw new RuntimeException("insufficient permissions");
        }

        return musicMapper.mapToDto(musicService.saveMusic(musicMapper.mapToEntity(music,dto)));
    }

    public void deleteMusic(Long musicId, HttpServletRequest request) {
        MusicEntity music = musicService.getById(musicId);
        UserEntity loggedUser = jwtService.getUser(request);
        if(!music.getAlbum().getUserEntity().getId().equals(loggedUser.getId()) && !loggedUser.getRole().equals(UserRole.ROLE_ADMIN)){
            throw new RuntimeException("insufficient permissions");
        }

        musicService.deleteMusic(music);
    }
}
