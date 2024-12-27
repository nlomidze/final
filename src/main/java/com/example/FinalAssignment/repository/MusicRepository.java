package com.example.FinalAssignment.repository;

import com.example.FinalAssignment.model.domain.database.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<MusicEntity,Long> {
    @Query("select m from MusicEntity m where m.musicName like %:name%")
    List<MusicEntity> findAllByMusicName(@Param("name") String name);
}
