package com.alura.Libroslitera.Servicio;


import com.alura.Libroslitera.Modelo.Libro;
import com.alura.Libroslitera.Repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;  // Repositorio de libros

    // Buscar libros por título
    public List<Libro> buscarLibroPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);  // Usamos 'Containing' para que funcione con espacios
    }

    // Obtener todos los libros
    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    // Obtener autores
    public List<String> obtenerAutores() {
        return libroRepository.findAll().stream()
                .map(libro -> libro.getAutor().getNombre())
                .distinct()
                .toList();
    }

    // Obtener autores vivos
    public List<String> obtenerAutoresVivos() {
        return libroRepository.findAll().stream()
                .filter(libro -> libro.getAutor().getAnoFallecimiento() == null)  // Filtramos autores vivos
                .map(libro -> libro.getAutor().getNombre())
                .distinct()
                .toList();
    }

    // Buscar libros por idioma
    public List<Libro> buscarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdiomaIgnoreCase(idioma);  // Busca libros por idioma
    }

    public void insertarLibro(Libro libro) {
        // Verifica si el libro ya existe en la base de datos
        Optional<Libro> libroExistente = libroRepository.findByTitulo(libro.getTitulo());

        if (libroExistente.isPresent()) {
            // Si el libro ya existe, se muestra un mensaje
            System.out.println("El libro ya se encuentra en la base de datos.");
        } else {
            // Si el libro no existe, se guarda en la base de datos
            libroRepository.save(libro);
            System.out.println("Libro guardado exitosamente.");
        }


    }
}
