package com.alura.Libroslitera.Menu;


import com.alura.Libroslitera.Modelo.Libro;
import com.alura.Libroslitera.Servicio.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Application implements CommandLineRunner {

    @Autowired
    private LibroService libroService;  // Se inyecta el servicio de libros.

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            // Menú de opciones
            System.out.println("\n--- Catálogo de Libros ---");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Ver todos los libros");
            System.out.println("3. Ver todos los autores");
            System.out.println("4. Ver autores vivos");
            System.out.println("5. Ver libros por idioma");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();  // Capturamos la opción seleccionada

            // Limpiamos el buffer del scanner
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo(scanner);  // Buscar un libro por título
                    break;
                case 2:
                    verTodosLosLibros();  // Ver todos los libros
                    break;
                case 3:
                    verAutores();  // Ver autores de los libros registrados
                    break;
                case 4:
                    verAutoresVivos();  // Ver autores vivos
                    break;
                case 5:
                    verLibrosPorIdioma(scanner);  // Ver libros por idioma
                    break;
                case 6:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        } while (opcion != 6);  // El bucle se ejecuta hasta que el usuario seleccione la opción de salir
    }

    // Método para buscar un libro por título
    private void buscarLibroPorTitulo(Scanner scanner) {
        System.out.print("Ingresa el título del libro: ");
        String titulo = scanner.nextLine();  // Captura el título con espacios

        // Realizamos la búsqueda usando el servicio
        List<Libro> libros = libroService.buscarLibroPorTitulo(titulo);

        // Mostramos los resultados
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros con ese título.");
        } else {
            System.out.println("\nResultados de búsqueda:");
            for (Libro libro : libros) {
                // Formateamos la salida para hacerla más legible
                System.out.println("\n---------------------------------------------");
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor().getNombre());
                System.out.println("Idioma: " + libro.getIdioma());
                System.out.println("Número de Descargas: " + libro.getDescargas());
                System.out.println("---------------------------------------------");
            }
        }
    }

    // Método para mostrar todos los libros registrados
    private void verTodosLosLibros() {
        List<Libro> libros = libroService.obtenerTodosLosLibros();

        // Verificamos si hay libros registrados
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("\nLista de todos los libros:");
            for (Libro libro : libros) {
                // Formateamos la salida para hacerla más legible
                System.out.println("\n---------------------------------------------");
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor().getNombre());
                System.out.println("Idioma: " + libro.getIdioma());
                System.out.println("Número de Descargas: " + libro.getDescargas());
                System.out.println("---------------------------------------------");
            }
        }
    }

    // Método para mostrar todos los autores registrados
    private void verAutores() {
        List<String> autores = libroService.obtenerAutores();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            System.out.println("\nLista de autores:");
            for (String autor : autores) {
                System.out.println(autor);
            }
        }
    }

    // Método para mostrar autores vivos
    private void verAutoresVivos() {
        List<String> autoresVivos = libroService.obtenerAutoresVivos();
        if (autoresVivos.isEmpty()) {
            System.out.println("No hay autores vivos registrados.");
        } else {
            System.out.println("\nLista de autores vivos:");
            for (String autor : autoresVivos) {
                System.out.println(autor);
            }
        }
    }

    // Método para ver libros por idioma
    private void verLibrosPorIdioma(Scanner scanner) {
        System.out.print("Ingresa el idioma de los libros: ");
        String idioma = scanner.nextLine();  // Captura el idioma con espacios

        // Realizamos la búsqueda usando el servicio
        List<Libro> libros = libroService.buscarLibrosPorIdioma(idioma);

        // Mostramos los resultados
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma " + idioma);
        } else {
            System.out.println("\nLibros en el idioma " + idioma + ":");
            for (Libro libro : libros) {
                // Formateamos la salida para hacerla más legible
                System.out.println("\n---------------------------------------------");
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor().getNombre());
                System.out.println("Idioma: " + libro.getIdioma());
                System.out.println("Número de Descargas: " + libro.getDescargas());
                System.out.println("---------------------------------------------");
            }
        }
    }
    public void agregarLibro(Libro libro) {
        libroService.insertarLibro(libro);
    }
}

