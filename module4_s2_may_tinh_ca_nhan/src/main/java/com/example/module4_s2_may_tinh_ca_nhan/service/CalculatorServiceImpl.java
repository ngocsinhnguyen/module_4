package com.example.module4_s2_may_tinh_ca_nhan.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public double calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "Addition(+)":
                return num1 + num2;
            case "Subtraction(-)":
                return num1 - num2;
            case "Multiplication(X)":
                return num1 * num2;
            case "Division(/)":
                if (num2 == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
