import clases.*;
import interfaces.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE BIBLIOTECA ===");
        
        // Crear objetos
        LibroFisico libro1 = new LibroFisico("Clean Code", "Robert Martin", "978-0132350884", "Estante A-12");
        LibroDigital ebook1 = new LibroDigital("Effective Java", "Joshua Bloch", "978-0134685991", "PDF", 5);
        Revista revista1 = new Revista("National Geographic", 245, "2024-01", "NG Partners");
        DVD dvd1 = new DVD("Inception", "Christopher Nolan", 148, "Ciencia Ficción");
        Audiolibro audio1 = new Audiolibro("Sapiens", "Derek Perkins", 450, "MP3", 250);
        
        // Variables de interfaz
        Prestable materialPrestable = libro1;
        Descargable materialDescargable = ebook1;
        Renovable materialRenovable = revista1;
        Reservable materialReservable = dvd1;
        
        // Mostrar información
        System.out.println("\n--- Mostrando información de todos los materiales ---");
        MaterialBiblioteca[] materiales = { libro1, ebook1, revista1, dvd1, audio1 };
        for (MaterialBiblioteca material : materiales) {
            material.mostrarInformacion();
        }
        
        // Prueba 3: Préstamo
        System.out.println("\n--- Prueba de Libro Físico ---");
        materialPrestable.prestar("Juan Pérez");
        System.out.println("¿Libro prestado? " + materialPrestable.estaPrestado());
        System.out.println("Días de préstamo: " + materialPrestable.diasPrestamo());
        materialPrestable.devolver();
        System.out.println("¿Libro prestado? " + materialPrestable.estaPrestado());
        
        // Prueba 4: Descarga
        System.out.println("\n--- Prueba de Libro Digital ---");
        materialDescargable.descargar("María García");
        System.out.println("Formato: " + materialDescargable.formatoArchivo());
        System.out.println("Tamaño: " + materialDescargable.tamañoArchivo() + " MB");
        
        // Prueba 5: Renovación
        System.out.println("\n--- Prueba de Revista ---");
        revista1.prestar("Pedro López");
        System.out.println("¿Puede renovarse? " + materialRenovable.puedeRenovarse());
        materialRenovable.renovar();
        System.out.println("Veces renovado: " + materialRenovable.vecesRenovado());
        materialRenovable.renovar();
        System.out.println("¿Puede renovarse? " + materialRenovable.puedeRenovarse());
        
        // Prueba 6: Reserva
        System.out.println("\n--- Prueba de DVD ---");
        materialReservable.reservar("Ana Martínez");
        System.out.println("¿DVD reservado? " + materialReservable.estaReservado());
        materialReservable.cancelarReserva();
        System.out.println("¿DVD reservado? " + materialReservable.estaReservado());
        
        // Prueba 7a: Préstamo duplicado
        System.out.println("\n--- Prueba 7a: Préstamo duplicado y devolución ---");
        LibroFisico libro2 = new LibroFisico("The Pragmatic Programmer", "David Thomas", "978-0135957059", "Estante B-5");
        libro2.prestar("Ana García");
        System.out.println("Usuario: " + libro2.getUsuarioPrestamo());
        libro2.prestar("Luis Martínez");
        System.out.println("Usuario (debe ser Ana): " + libro2.getUsuarioPrestamo());
        libro2.devolver();
        System.out.println("Después devolver - Prestado: " + libro2.estaPrestado());
        libro2.devolver();
        
        // Prueba 7b: Reserva duplicada
        System.out.println("\n--- Prueba 7b: Reserva duplicada y préstamo ---");
        DVD dvd2 = new DVD("The Matrix", "Lana Wachowski", 136, "Ciencia Ficción");
        dvd2.reservar("Ana Martínez");
        System.out.println("Reservado: " + dvd2.getUsuarioReserva());
        dvd2.reservar("Luis Pérez");
        System.out.println("Reservado (debe ser Ana): " + dvd2.getUsuarioReserva());
        dvd2.prestar("Luis Pérez");
        System.out.println("Prestado: " + dvd2.estaPrestado());
        dvd2.prestar("Ana Martínez");
        System.out.println("Prestado: " + dvd2.estaPrestado() + ", Reservado: " + dvd2.estaReservado());
        dvd2.reservar("Carlos López");
        
        // Prueba 7c: Cancelación
        System.out.println("\n--- Prueba 7c: Cancelación ---");
        LibroDigital ebook2 = new LibroDigital("Design Patterns", "Gang of Four", "978-0201633610", "EPUB", 8);
        ebook2.reservar("María López");
        System.out.println("Reservado: " + ebook2.estaReservado());
        ebook2.cancelarReserva();
        System.out.println("Reservado: " + ebook2.estaReservado());
        ebook2.cancelarReserva();
        
        // Prueba 7d: Renovaciones
        System.out.println("\n--- Prueba 7d: Renovaciones ---");
        Revista revista2 = new Revista("Science Magazine", 100, "2024-02", "Science Inc");
        revista2.renovar();
        revista2.prestar("Carlos López");
        revista2.renovar();
        System.out.println("Renovaciones: " + revista2.getRenovaciones());
        revista2.renovar();
        System.out.println("Renovaciones (debe ser 1): " + revista2.getRenovaciones());
        revista2.devolver();
        revista2.prestar("Carlos López");
        System.out.println("Después nuevo préstamo: " + revista2.getRenovaciones());
        
        // Prueba 7e: Descarga y reserva
        System.out.println("\n--- Prueba 7e: Descarga y reserva ---");
        LibroDigital ebook3 = new LibroDigital("Refactoring", "Martin Fowler", "978-0201485677", "PDF", 12);
        ebook3.reservar("Ana Rodríguez");
        ebook3.descargar("Luis Fernández");
        System.out.println("Reservado (debe ser Sí): " + ebook3.estaReservado());
        ebook3.descargar("Ana Rodríguez");
        System.out.println("Reservado (debe ser No): " + ebook3.estaReservado());
        
        // Prueba 7f: Usuarios inválidos
        System.out.println("\n--- Prueba 7f: Usuarios inválidos ---");
        LibroFisico libro3 = new LibroFisico("Java Concurrency", "Brian Goetz", "978-0321349606", "Estante D-1");
        libro3.prestar(null);
        libro3.prestar("");
        libro3.prestar("   ");
        
        System.out.println("\n=== FIN DE PRUEBAS ===");
    }
}
