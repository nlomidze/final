package com.example.FinalAssignment.repository;

import com.example.FinalAssignment.model.domain.database.MusicLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicLoggerRepository extends JpaRepository<MusicLogger,Long> {
    Optional<MusicLogger> findByUserIdAndMusicId(Long userId,Long musicId);

}
