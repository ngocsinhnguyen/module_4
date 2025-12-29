package com.example.chuyen_doi_tien_te.service;

import com.example.chuyen_doi_tien_te.exception.InvalidAmountException;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {
    
    public double convertUsdToVnd(double rate, double usd) throws InvalidAmountException {

        if (rate <= 0) {
            throw new InvalidAmountException("Tỉ giá phải lớn hơn 0!");
        }

        if (usd <= 0) {
            throw new InvalidAmountException("Số tiền USD phải lớn hơn 0!");
        }
        return rate * usd;
    }
}
