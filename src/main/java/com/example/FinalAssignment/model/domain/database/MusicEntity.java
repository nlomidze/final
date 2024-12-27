package com.example.FinalAssignment.model.domain.database;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "musics")
@Setter
@Getter
@RequiredArgsConstructor
public class MusicEntity {

    @Id
    @Column(name = "music_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String musicName;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_Id")
    private AlbumEntity album;
}