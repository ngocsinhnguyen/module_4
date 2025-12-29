package com.example.module4_s1_tu_dien.service;

import com.example.module4_s1_tu_dien.model.Word;
import java.util.Map;

public interface DictionaryService {

    String findWord(String english);

    Map<String, String> getAllWords();
}
