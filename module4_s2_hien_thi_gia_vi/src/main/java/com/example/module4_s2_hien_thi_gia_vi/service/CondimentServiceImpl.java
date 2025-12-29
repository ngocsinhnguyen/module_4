package com.example.module4_s2_hien_thi_gia_vi.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CondimentServiceImpl implements CondimentService {
    @Override
    public List<String> getAllCondiments() {
        return Arrays.asList("Lettuce", "Tomato", "Mustard", "Sprouts");
    }
}
