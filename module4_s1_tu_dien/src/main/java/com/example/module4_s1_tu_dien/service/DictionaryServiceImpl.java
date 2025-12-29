package com.example.module4_s1_tu_dien.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class DictionaryServiceImpl implements DictionaryService {
    private Map<String, String> dictionary;

    public DictionaryServiceImpl() {

        dictionary = new HashMap<>();
        dictionary.put("hello", "xin chào");
        dictionary.put("world", "thế giới");
        dictionary.put("computer", "máy tính");
        dictionary.put("book", "sách");
        dictionary.put("student", "học sinh, sinh viên");
        dictionary.put("teacher", "giáo viên");
        dictionary.put("school", "trường học");
        dictionary.put("house", "ngôi nhà");
        dictionary.put("car", "xe hơi, ô tô");
        dictionary.put("phone", "điện thoại");
        dictionary.put("water", "nước");
        dictionary.put("food", "thức ăn");
        dictionary.put("friend", "bạn bè");
        dictionary.put("family", "gia đình");
        dictionary.put("love", "yêu, tình yêu");
    }

    @Override
    public String findWord(String english) {
        if (english == null || english.trim().isEmpty()) {
            return null;
        }

        return dictionary.get(english.toLowerCase().trim());
    }

    @Override
    public Map<String, String> getAllWords() {
        return new HashMap<>(dictionary);
    }
}
