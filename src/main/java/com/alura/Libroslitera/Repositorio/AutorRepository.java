package com.alura.Libroslitera.Repositorio;

// Repositorio AutorRepository
import com.alura.Libroslitera.Modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByAnoNacimientoLessThanEqualAndAnoFallecimientoGreaterThanEqual(int anoNacimiento, int anoFallecimiento);
}


