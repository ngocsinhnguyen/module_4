package com.example.module4_s8_validate_form.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Tên không được bỏ trống")
    @Size(min = 5, max = 45, message = "Tên phải có độ dài từ 5 đến 45 kí tự")
    private String firstname;

    @NotBlank(message = "Họ không được bỏ trống")
    @Size(min = 5, max = 45, message = "Họ phải có độ dài từ 5 đến 45 kí tự")
    private String lastname;

    @Pattern(regexp = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$", 
             message = "Số điện thoại không hợp lệ")
    private String phonenumber;

    @Min(value = 18, message = "Tuổi phải >= 18")
    private int age;

    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được bỏ trống")
    private String email;
}
