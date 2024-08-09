package com.cic.ejercicio07.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cic.ejercicio07.model.Coche;
import com.cic.ejercicio07.service.CocheService;

public class CocheController {

@Autowired
    private CocheService cocheService;

    /**
     * Endpoint para añadir un nuevo coche.
     * Maneja solicitudes POST a la ruta /coches.
     *
     * @param coche Los detalles del coche a añadir.
     * @return Un ResponseEntity con el coche añadido y el estado HTTP 201 Created.
     */
    @PostMapping
    public ResponseEntity<Coche> añadirCoche(@RequestBody Coche coche) {
        Coche cocheAñadido = cocheService.añadirCoche(coche);
        return ResponseEntity.status(HttpStatus.CREATED).body(cocheAñadido);
    }

    /**
     * Endpoint para leer los detalles de un coche específico.
     * Maneja solicitudes GET a la ruta /coches/{id}.
     *
     * @param id El ID del coche que se desea leer.
     * @return Un Optional que contiene el coche si se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Coche> leerCoche(@PathVariable Long id) {
        Optional<Coche> coche = cocheService.leerCoche(id);
        return coche.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Endpoint para eliminar un coche específico.
     * Maneja solicitudes DELETE a la ruta /coches/{id}.
     *
     * @param id El ID del coche que se desea eliminar.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCoche(@PathVariable Long id) {
        cocheService.eliminarCoche(id);
    }

    /**
     * Endpoint para listar todos los coches.
     * Maneja solicitudes GET a la ruta /coches.
     *
     * @param page El número de página que se desea obtener.
     * @param size El tamaño de la página (número de elementos por página).
     * @return Una página de coches.
     */
    @GetMapping
    public ResponseEntity<Page<Coche>> listarCoches(@RequestParam int page, @RequestParam int size) {
        Page<Coche> coches = cocheService.listarCoches(page, size);
        return ResponseEntity.ok(coches);
    }
}
