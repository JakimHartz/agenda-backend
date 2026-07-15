package com.agenda.contactos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "contactos")
@Data
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^(\\+52)?\\d{10}$", message = "El número de teléfono debe tener exactamente 10 dígitos y el código de país +52 es opcional")
    private String telefono;

    @Email(message = "El  formato del correo no es válido")
    private String correo;

    private String direccion;

}
