package com.agenda.contactos.controller;

import com.agenda.contactos.model.Contacto;
import com.agenda.contactos.repository.ContactoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contactos")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactoController {

    @Autowired
    private ContactoRepository contactoRepository;

    // Obtener todos los contactos
    @GetMapping
    public List<Contacto> obtenerTodos(@RequestParam(required = false) String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            return contactoRepository.findByNombreContainingIgnoreCase(nombre);
        }
        return contactoRepository.findAll();
    }

    // Buscar un contacto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Contacto> obtenerPorId(@PathVariable Long id) {
        Optional<Contacto> contactoOptional = contactoRepository.findById(id);
        if (contactoOptional.isPresent()) {
            Contacto contacto = contactoOptional.get();
            return ResponseEntity.ok(contacto);
        } else {
            return ResponseEntity.notFound().build();
        }
        //return contactoRepository.findById(id).map(contacto -> ResponseEntity.ok(contacto)).orElse(ResponseEntity.notFound().build());
    }

    // Agregar contacto
    @PostMapping
    public ResponseEntity<Contacto> crearContacto(@Valid @RequestBody Contacto contacto) {
        Contacto nuevoContacto = contactoRepository.save(contacto);
        return new ResponseEntity<>(nuevoContacto, HttpStatus.CREATED);
    }

    // Editar un contacto existente
    @PutMapping("/{id}")
    public ResponseEntity<Contacto> actualizarContacto(@PathVariable Long id, @Valid @RequestBody Contacto detalleContacto) {
        return contactoRepository.findById(id).map(contactoExistente -> {
            contactoExistente.setNombre(detalleContacto.getNombre());
            contactoExistente.setTelefono(detalleContacto.getTelefono());
            contactoExistente.setCorreo(detalleContacto.getCorreo());
            contactoExistente.setDireccion(detalleContacto.getDireccion());
            Contacto contactoActualizado = contactoRepository.save(contactoExistente);
            return ResponseEntity.ok(contactoActualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un contacto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContacto(@PathVariable Long id) {
        return contactoRepository.findById(id).map(contacto -> {
            contactoRepository.delete(contacto);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElse(ResponseEntity.notFound().build());
    }

}
