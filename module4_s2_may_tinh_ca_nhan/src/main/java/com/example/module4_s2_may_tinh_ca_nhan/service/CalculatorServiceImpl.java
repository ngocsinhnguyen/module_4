package com.example.module4_s2_may_tinh_ca_nhan.service;

import com.example.module4_s2_may_tinh_ca_nhan.model.Calculator;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public void calculate(Calculator calculator) {
        double num1 = calculator.getNum1();
        double num2 = calculator.getNum2();
        String operator = calculator.getOperator();

        try {
            switch (operator) {
                case "Addition(+)":
                    calculator.setResult(num1 + num2);
                    break;
                case "Subtraction(-)":
                    calculator.setResult(num1 - num2);
                    break;
                case "Multiplication(X)":
                    calculator.setResult(num1 * num2);
                    break;
                case "Division(/)":
                    if (num2 == 0) {
                        calculator.setError("Cannot divide by zero");
                    } else {
                        calculator.setResult(num1 / num2);
                    }
                    break;
                default:
                    calculator.setError("Invalid operator: " + operator);
            }
        } catch (Exception e) {
            calculator.setError("Calculation error: " + e.getMessage());
        }
    }
}
