package com.example.FinalAssignment.model.domain.database;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "logger")
@Setter
@Getter
@RequiredArgsConstructor
public class MusicLogger {

    @Id
    @Column(name = "logger_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "music_id")
    private MusicEntity music;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column
    private Long listenCount;


}
