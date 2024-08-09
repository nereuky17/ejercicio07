package com.cic.ejercicio07.service;

import org.springframework.data.domain.Page;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.cic.ejercicio07.model.Coche;
import com.cic.ejercicio07.repository.CocheRepository;

public class CocheService {

@Autowired
    private CocheRepository cocheRepository;

    /**
     * Añade un nuevo coche al sistema.
     *
     * @param coche Los detalles del coche a añadir.
     * @return El coche que ha sido guardado en el repositorio.
     */
    public Coche añadirCoche(Coche coche) {
        return cocheRepository.save(coche);
    }

    /**
     * Lee los detalles de un coche específico por su ID.
     *
     * @param id El ID del coche que se desea leer.
     * @return Un Optional que contiene el coche si se encuentra.
     */
    public Optional<Coche> leerCoche(Long id) {
        return cocheRepository.findById(id);
    }

    /**
     * Elimina un coche del sistema por su ID.
     *
     * @param id El ID del coche que se desea eliminar.
     */
    public void eliminarCoche(Long id) {
        cocheRepository.deleteById(id);
    }

    /**
     * Lista todos los coches en una página específica.
     *
     * @param page El número de página que se desea obtener.
     * @param size El tamaño de la página (número de elementos por página).
     * @return Una página de coches.
     */
    public Page<Coche> listarCoches(int page, int size) {
        return cocheRepository.findAll(PageRequest.of(page, size));
    }
}
