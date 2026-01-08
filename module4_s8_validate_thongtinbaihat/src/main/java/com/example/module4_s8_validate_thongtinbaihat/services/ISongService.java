package com.example.module4_s8_validate_thongtinbaihat.services;

import com.example.module4_s8_validate_thongtinbaihat.dto.SongDTO;
import java.util.List;

public interface ISongService {
    List<SongDTO> findAll();
    SongDTO findById(Long id);
    void save(SongDTO songDTO);
}
