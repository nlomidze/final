package com.example.FinalAssignment.controller;

import com.example.FinalAssignment.facade.MusicFacade;
import com.example.FinalAssignment.model.dto.MusicDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musics")
@RequiredArgsConstructor
public class MusicController {

    private final MusicFacade musicFacade;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearer-token"))
    public ResponseEntity<List<MusicDto>> getAllByName(@RequestParam String name) {
        return new ResponseEntity<>(musicFacade.getMusicListByName(name), HttpStatus.OK);
    }

    @GetMapping("/{musicId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearer-token"))
    public ResponseEntity<MusicDto> getById(HttpServletRequest request, @PathVariable Long musicId) {
        return new ResponseEntity<>(musicFacade.getMusicById(musicId,request), HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearer-token"))
    public ResponseEntity<MusicDto> addMusic(HttpServletRequest request,@RequestBody MusicDto dto) {
        return new ResponseEntity<>(musicFacade.createMusic(dto,request), HttpStatus.OK);
    }

    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearer-token"))
    public ResponseEntity<MusicDto> saveMusic(HttpServletRequest request,@RequestBody MusicDto dto) {
        return new ResponseEntity<>(musicFacade.saveMusic(dto,request), HttpStatus.OK);
    }

    @DeleteMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearer-token"))
    public ResponseEntity<MusicDto> deleteMusic(HttpServletRequest request,@PathVariable Long musicId) {
        musicFacade.deleteMusic(musicId,request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
