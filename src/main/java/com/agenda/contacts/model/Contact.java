package com.agenda.contacts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "contacts")
@Data
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
//    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
//    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo debe contener letras y espacios")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s'-]{2,50}$", message = "El nombre solo debe contener letras, espacios, acentos y caracteres como «-» o «'»")
    private String name;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^(\\+52)?\\d{10}$", message = "El número de teléfono debe tener exactamente 10 dígitos y el código de país +52 es opcional")
    private String telephone;

    @Email(message = "El  formato del correo no es válido")
    private String email;

    private String address;

}
