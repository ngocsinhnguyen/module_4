package com.example.module4_s2_hien_thi_gia_vi.model;

public class Sandwich {
    private String[] condiments;

    public Sandwich() {
    }

    public Sandwich(String[] condiments) {
        this.condiments = condiments;
    }

    public String[] getCondiments() {
        return condiments;
    }

    public void setCondiments(String[] condiments) {
        this.condiments = condiments;
    }
}
