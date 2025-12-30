package com.example.module4_s4_cau_hinh_hom_thu_dien_tu.service;

import com.example.module4_s4_cau_hinh_hom_thu_dien_tu.model.MailSettings;
import com.example.module4_s4_cau_hinh_hom_thu_dien_tu.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {
    @Autowired
    private SettingsRepository repo;

    public MailSettings findSettings() {
        return repo.getSettings();
    }

    public void saveSettings(MailSettings settings) {
        repo.update(settings);
    }
}

