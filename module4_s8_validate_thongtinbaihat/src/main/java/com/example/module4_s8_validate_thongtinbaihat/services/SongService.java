package com.example.module4_s8_validate_thongtinbaihat.services;

import com.example.module4_s8_validate_thongtinbaihat.dto.SongDTO;
import com.example.module4_s8_validate_thongtinbaihat.entity.Song;
import com.example.module4_s8_validate_thongtinbaihat.repository.SongRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongService implements ISongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public List<SongDTO> findAll() {
        return songRepository.findAll().stream().map(song -> {
            SongDTO dto = new SongDTO();
            BeanUtils.copyProperties(song, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public SongDTO findById(Long id) {
        Song song = songRepository.findById(id).orElse(null);
        if (song == null) return null;
        SongDTO dto = new SongDTO();
        BeanUtils.copyProperties(song, dto);
        return dto;
    }

    @Override
    public void save(SongDTO songDTO) {
        Song song = new Song();
        if (songDTO.getId() != null) {
            song = songRepository.findById(songDTO.getId()).orElse(new Song());
        }
        BeanUtils.copyProperties(songDTO, song);
        songRepository.save(song);
    }
}
