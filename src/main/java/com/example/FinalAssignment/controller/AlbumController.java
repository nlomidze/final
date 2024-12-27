package com.example.FinalAssignment.controller;

import com.example.FinalAssignment.facade.AlbumFacade;
import com.example.FinalAssignment.model.dto.AlbumDto;
import com.example.FinalAssignment.model.dto.MusicDto;
import com.example.FinalAssignment.model.param.AddAlbumParam;
import com.example.FinalAssignment.model.param.UpdateAlbumParam;
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
@RequestMapping("/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumFacade albumFacade;

    @PostMapping("add-album")
    @PreAuthorize("hasRole('ARTIST')")
    @Operation(security = @SecurityRequirement(name = "bearer-token"))
    public ResponseEntity<AlbumDto> addAlbum(HttpServletRequest request,@RequestBody AddAlbumParam addAlbumParam) {
        return new ResponseEntity<>(albumFacade.addAlbum(addAlbumParam,request), HttpStatus.CREATED);
    }

    @PutMapping("")
    @PreAuthorize("hasRole('ARTIST') || hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearer-token"))
    public ResponseEntity<AlbumDto> putAlbum(HttpServletRequest request,@RequestBody UpdateAlbumParam updateAlbumParam) {
        return new ResponseEntity<>(albumFacade.updateAlbum(updateAlbumParam,request), HttpStatus.CREATED);
    }

    @GetMapping("")
    @Operation(security = @SecurityRequirement(name = "bearer-token"))
    public ResponseEntity<List<AlbumDto>> getAll() {
        return new ResponseEntity<>(albumFacade.getAll(), HttpStatus.OK);
    }


    @GetMapping("/getUserPlaylist")
    @PreAuthorize("hasRole('ARTIST')")
    @Operation(security = @SecurityRequirement(name = "bearer-token"))
    public ResponseEntity<List<AlbumDto>> getUserPlaylist(HttpServletRequest request) {
        return new ResponseEntity<>(albumFacade.getUserPlaylists(request), HttpStatus.OK);
    }

    @DeleteMapping("")
    @PreAuthorize("hasRole('ARTIST') || hasRole('ADMIN')")
    @Operation(security = @SecurityRequirement(name = "bearer-token"))
    public ResponseEntity<AlbumDto> deleteAlbum(HttpServletRequest request,
                                                @RequestParam Long albumId) {
        albumFacade.delete(albumId,request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
