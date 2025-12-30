package com.example.module4_s4_cau_hinh_hom_thu_dien_tu.repository;

import com.example.module4_s4_cau_hinh_hom_thu_dien_tu.model.MailSettings;
import org.springframework.stereotype.Repository;

@Repository
public class SettingsRepository {
    private static MailSettings settings = new MailSettings();

    static {
        settings.setLanguage("English");
        settings.setPageSize(25);
        settings.setSpamFilter(false);
        settings.setSignature("Thor\nKing, Asgard");
    }

    public MailSettings getSettings() {
        return settings;
    }

    public void update(MailSettings newSettings) {
        settings = newSettings;
    }
}
