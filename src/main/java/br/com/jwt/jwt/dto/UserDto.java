package br.com.jwt.jwt.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
public class UserDto {

    @NotBlank(message = "O campo nome não pode estar em branco")
    private String name;
    
    @NotBlank(message = "O campo nome não pode estar em branco")
    private String email;

    @Size(min = 8, max = 16)
    private String password;
}
