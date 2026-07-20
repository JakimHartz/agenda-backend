package com.agenda.contacts.controller;

import com.agenda.contacts.model.Contact;
import com.agenda.contacts.repository.ContactRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    // Preferible en lugar de @Autowired en el atributo:
//    private final ContactRepository contactRepository;
//
//    public ContactController(ContactRepository contactRepository) {
//        this.contactRepository = contactRepository;
//    }

    // Obtener todos los contactos
    @GetMapping
    public List<Contact> getAll(@RequestParam(required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return contactRepository.findByNameContainingIgnoreCase(name);
        }
        return contactRepository.findAll();
    }

    // Buscar un contacto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getById(@PathVariable Long id) {
        Optional<Contact> contactoOptional = contactRepository.findById(id);
        if (contactoOptional.isPresent()) {
            Contact contact = contactoOptional.get();
            return ResponseEntity.ok(contact);
        } else {
            return ResponseEntity.notFound().build();
        }
        //return contactoRepository.findById(id).map(contacto -> ResponseEntity.ok(contacto)).orElse(ResponseEntity.notFound().build());
    }

    // Agregar contacto
    @PostMapping
    public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact) {
        Contact newContact = contactRepository.save(contact);
        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    // Editar un contacto existente
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @Valid @RequestBody Contact detalleContact) {
        return contactRepository.findById(id).map(contactExisting -> {
            contactExisting.setName(detalleContact.getName());
            contactExisting.setTelephone(detalleContact.getTelephone());
            contactExisting.setEmail(detalleContact.getEmail());
            contactExisting.setAddress(detalleContact.getAddress());
            Contact contactUpdated = contactRepository.save(contactExisting);
            return ResponseEntity.ok(contactUpdated);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un contacto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        return contactRepository.findById(id).map(contact -> {
            contactRepository.delete(contact);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElse(ResponseEntity.notFound().build());
    }

}
