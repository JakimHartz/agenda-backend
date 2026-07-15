package com.agenda.contactos.repository;

import com.agenda.contactos.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {

    List<Contacto> findByNombreContainingIgnoreCase(String nombre);
}
