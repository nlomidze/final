package com.example.FinalAssignment.repository;

import com.example.FinalAssignment.model.domain.database.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = """
        delete from UserEntity u 
        where u.username = :username
        """, nativeQuery = true)
    void deleteUser(@Param("username") String username);
}
