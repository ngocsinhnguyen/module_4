package com.example.module4_s8_validate_thongtinbaihat.repository;

import com.example.module4_s8_validate_thongtinbaihat.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
}
