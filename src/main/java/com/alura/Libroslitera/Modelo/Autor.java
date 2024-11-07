package com.alura.Libroslitera.Modelo;




import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer anoNacimiento;
    private Integer anoFallecimiento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAñoNacimiento() {
        return anoNacimiento;
    }

    public void setAñoNacimiento(Integer añoNacimiento) {
        this.anoNacimiento = añoNacimiento;
    }

    public Integer getAñoFallecimiento() {
        return anoFallecimiento;
    }

    public void setAñoFallecimiento(Integer añoFallecimiento) {
        this.anoFallecimiento = añoFallecimiento;
    }

    public Integer getAnoFallecimiento() {
        return anoFallecimiento;
    }
}
