package com.alura.Libroslitera.Repositorio;
import com.alura.Libroslitera.Modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByIdioma(String idioma);
    List<Libro> findByAutor_Nombre(String nombreAutor);

    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    List<Libro> findByIdiomaIgnoreCase(String idioma);

    Optional<Libro> findByTitulo(String titulo);
}

