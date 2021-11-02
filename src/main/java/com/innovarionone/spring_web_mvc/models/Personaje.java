package com.innovarionone.spring_web_mvc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//validacion https://spring.io/guides/gs/validating-form-input/
//https://dev.to/reytech-lesson/using-lombok-in-spring-boot-i2b
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personaje {

    private int id;

    @NotBlank(message = "El nombre no puede debe estar vacio")
    @Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
    private String name;

    @NotBlank(message = "El apellido no puede debe estar vacio")
    @Size(min = 3, max = 20, message = "El apellido debe tener entre 3 y 20 caracteres")
    private String lastName;
}
