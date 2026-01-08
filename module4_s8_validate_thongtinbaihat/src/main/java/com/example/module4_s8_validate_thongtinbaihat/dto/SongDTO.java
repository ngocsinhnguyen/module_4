package com.example.module4_s8_validate_thongtinbaihat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {
    private Long id;

    @NotBlank(message = "Tên bài hát không được để trống")
    @Size(max = 800, message = "Tên bài hát không vượt quá 800 ký tự")
    @Pattern(regexp = "^[^@;,.=\\-+]*$", message = "Tên bài hát không chứa các kí tự đặc biệt như @ ; , . = - +")
    private String name;

    @NotBlank(message = "Nghệ sĩ không được để trống")
    @Size(max = 300, message = "Nghệ sĩ không quá 300 ký tự")
    @Pattern(regexp = "^[^@;,.=\\+]*$", message = "Nghệ sĩ không chứa các kí tự đặc biệt như @ ; , . = - +")
    private String artist;

    @NotBlank(message = "Thể loại không được để trống")
    @Size(max = 1000, message = "Thể loại không vượt quá 1000 ký tự")
    @Pattern(regexp = "^[^@;.=\\-+]*$", message = "Thể loại không được chứa ký tự đặc biệt (ngoại trừ dấu phẩy) như @ ; . = - +")
    private String genre;
}
