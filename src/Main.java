import clases.*;
import interfaces.*;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE BIBLIOTECA ===\n");
        
        // ============================================
        // PARTE 1: CREAR OBJETOS DE CADA TIPO
        // ============================================
        System.out.println("--- Creando objetos de la biblioteca ---");
        
        LibroFisico libro1 = new LibroFisico("Clean Code", "Robert Martin", "978-0132350884", "Estante A-12");
        LibroDigital ebook1 = new LibroDigital("Effective Java", "Joshua Bloch", "978-0134685991", "PDF", 5);
        Revista revista1 = new Revista("National Geographic", 245, "2024-01", "NG Partners");
        DVD dvd1 = new DVD("Inception", "Christopher Nolan", 148, "Ciencia Ficción");
        Audiolibro audio1 = new Audiolibro("Sapiens", "Derek Perkins", 450, "MP3", 250);
        
        System.out.println("\n✓ Todos los objetos creados exitosamente\n");
        
        // ============================================
        // PARTE 2: DEMOSTRAR POLIMORFISMO
        // ============================================
        System.out.println("--- Demostrando polimorfismo mediante interfaces ---");
        
        // Variables de tipo interfaz
        Prestable materialPrestable = libro1;
        Descargable materialDescargable = ebook1;
        Renovable materialRenovable = revista1;
        Reservable materialReservable = dvd1;
        
        System.out.println("\n--- Polimorfismo mediante la clase padre ---");
        System.out.println("Mostrando información de todos los materiales:");
        
        MaterialBiblioteca[] materiales = { libro1, ebook1, revista1, dvd1, audio1 };
        for (MaterialBiblioteca material : materiales) {
            material.mostrarInformacion();
        }
        
        // ============================================
        // PARTE 3: PROBAR FUNCIONALIDAD DE PRÉSTAMO
        // ============================================
        System.out.println("\n--- Prueba de Libro Físico - Préstamo ---");
        materialPrestable.prestar("Juan Pérez");
        System.out.println("¿Libro prestado? " + materialPrestable.estaPrestado());
        System.out.println("Días de préstamo: " + materialPrestable.diasPrestamo());
        materialPrestable.devolver();
        System.out.println("¿Libro prestado? " + materialPrestable.estaPrestado());
        
        // ============================================
        // PARTE 4: PROBAR FUNCIONALIDAD DE DESCARGA
        // ============================================
        System.out.println("\n--- Prueba de Libro Digital - Descarga ---");
        materialDescargable.descargar("María García");
        System.out.println("Formato: " + materialDescargable.formatoArchivo());
        System.out.println("Tamaño: " + materialDescargable.tamañoArchivo() + " MB");
        
        // ============================================
        // PARTE 5: PROBAR FUNCIONALIDAD DE RENOVACIÓN
        // ============================================
        System.out.println("\n--- Prueba de Revista - Renovación ---");
        revista1.prestar("Pedro López");
        System.out.println("¿Puede renovarse? " + materialRenovable.puedeRenovarse());
        materialRenovable.renovar();
        System.out.println("Veces renovado: " + materialRenovable.vecesRenovado());
        materialRenovable.renovar(); // Intentar renovar de nuevo (debe fallar)
        System.out.println("¿Puede renovarse? " + materialRenovable.puedeRenovarse());
        
        // ============================================
        // PARTE 6: PROBAR FUNCIONALIDAD DE RESERVA
        // ============================================
        System.out.println("\n--- Prueba de DVD - Reserva ---");
        materialReservable.reservar("Ana Martínez");
        System.out.println("¿DVD reservado? " + materialReservable.estaReservado());
        materialReservable.cancelarReserva();
        System.out.println("¿DVD reservado? " + materialReservable.estaReservado());
        
        // ============================================
        // PARTE 7: PROBAR OPERACIONES INVÁLIDAS
        // ============================================
        System.out.println("\n--- Pruebas de operaciones inválidas ---");
        
        // 7a: Préstamo duplicado y devolución
        System.out.println("\n7a) Préstamo duplicado y devolución:");
        LibroFisico libro2 = new LibroFisico("The Pragmatic Programmer", "David Thomas", "978-0135957059", "Estante B-5");
        libro2.prestar("Ana García");
        System.out.println("Estado: Usuario prestamo = " + libro2.getUsuarioPrestamo());
        libro2.prestar("Luis Martínez"); // Intentar prestar a otro usuario
        System.out.println("Estado: Usuario prestamo = " + libro2.getUsuarioPrestamo() + " (debe ser Ana García)");
        libro2.devolver();
        System.out.println("Después de devolver - ¿Prestado? " + libro2.estaPrestado());
        libro2.devolver(); // Intentar devolver de nuevo
        System.out.println("Después de segundo devolver - Usuario: " + libro2.getUsuarioPrestamo());
        
        // 7b: Reserva duplicada y préstamo reservado
        System.out.println("\n7b) Reserva duplicada y préstamo reservado:");
        DVD dvd2 = new DVD("The Matrix", "Lana Wachowski", 136, "Ciencia Ficción");
        dvd2.reservar("Ana Martínez");
        System.out.println("Reservado para: " + dvd2.getUsuarioReserva());
        dvd2.reservar("Luis Pérez"); // Intentar reservar para otro usuario
        System.out.println("Reservado para: " + dvd2.getUsuarioReserva() + " (debe ser Ana Martínez)");
        dvd2.prestar("Luis Pérez"); // Intentar prestar a usuario distinto
        System.out.println("Prestado: " + dvd2.estaPrestado() + ", Reservado: " + dvd2.estaReservado());
        dvd2.prestar("Ana Martínez"); // Prestar al usuario que lo reservó
        System.out.println("Prestado: " + dvd2.estaPrestado() + ", Reservado: " + dvd2.estaReservado());
        dvd2.reservar("Carlos López"); // Intentar reservar mientras está prestado
        
        // 7c: Cancelación
        System.out.println("\n7c) Cancelación de reservas:");
        LibroDigital ebook2 = new LibroDigital("Design Patterns", "Gang of Four", "978-0201633610", "EPUB", 8);
        ebook2.reservar("María López");
        System.out.println("Reservado: " + ebook2.estaReservado());
        ebook2.cancelarReserva();
        System.out.println("Después de cancelar - Reservado: " + ebook2.estaReservado());
        ebook2.cancelarReserva(); // Intentar cancelar de nuevo
        
        // 7d: Renovaciones
        System.out.println("\n7d) Límites de renovación:");
        Revista revista2 = new Revista("Science Magazine", 100, "2024-02", "Science Inc");
        revista2.renovar(); // Intentar renovar sin préstamo
        revista2.prestar("Carlos López");
        System.out.println("Renovaciones: " + revista2.vecesRenovado() + ", ¿Puede renovarse? " + revista2.puedeRenovarse());
        revista2.renovar();
        System.out.println("Renovaciones: " + revista2.vecesRenovado() + ", ¿Puede renovarse? " + revista2.puedeRenovarse());
        revista2.renovar(); // Intentar renovar más del límite
        System.out.println("Renovaciones: " + revista2.vecesRenovado() + " (debe ser 1)");
        revista2.devolver();
        revista2.prestar("Carlos López"); // Nuevo préstamo
        System.out.println("Después de nuevo préstamo - Renovaciones: " + revista2.vecesRenovado());
        
        // Prueba de LibroFisico con límite de 2 renovaciones
        System.out.println("\nPrueba de LibroFisico con 2 renovaciones:");
        LibroFisico libro3 = new LibroFisico("Code Complete", "Steve McConnell", "978-0735619678", "Estante C-3");
        libro3.prestar("Diana Ruiz");
        libro3.renovar();
        System.out.println("Renovación 1: " + libro3.vecesRenovado());
        libro3.renovar();
        System.out.println("Renovación 2: " + libro3.vecesRenovado());
        libro3.renovar(); // Intentar tercera renovación
        System.out.println("Intentar tercera renovación: " + libro3.vecesRenovado() + " (debe ser 2)");
        
        // 7e: Descarga y reserva en digitales
        System.out.println("\n7e) Descarga y reserva en LibroDigital:");
        LibroDigital ebook3 = new LibroDigital("Refactoring", "Martin Fowler", "978-0201485677", "PDF", 12);
        ebook3.reservar("Ana Rodríguez");
        System.out.println("Reservado para: " + ebook3.getUsuarioReserva());
        ebook3.descargar("Luis Fernández");
        System.out.println("Después descarga Luis - Reservado: " + ebook3.estaReservado() + " (debe ser Sí)");
        ebook3.descargar("Ana Rodríguez");
        System.out.println("Después descarga Ana - Reservado: " + ebook3.estaReservado() + " (debe ser No)");
        
        System.out.println("\n7e) Descarga y reserva en Audiolibro:");
        Audiolibro audio2 = new Audiolibro("Thinking, Fast and Slow", "Kahneman", 720, "MP3", 300);
        audio2.reservar("Elena García");
        System.out.println("Reservado para: " + audio2.getUsuarioReserva());
        audio2.descargar("Roberto López");
        System.out.println("Después descarga Roberto - Reservado: " + audio2.estaReservado() + " (debe ser Sí)");
        audio2.descargar("Elena García");
        System.out.println("Después descarga Elena - Reservado: " + audio2.estaReservado() + " (debe ser No)");
        
        // 7f: Usuarios inválidos
        System.out.println("\n7f) Usuarios inválidos (null, vacío, espacios):");
        LibroFisico libro4 = new LibroFisico("Java Concurrency", "Brian Goetz", "978-0321349606", "Estante D-1");
        LibroDigital ebook4 = new LibroDigital("Spring in Action", "Craig Walls", "978-1617294945", "EPUB", 10);
        DVD dvd3 = new DVD("Interstellar", "Christopher Nolan", 169, "Ciencia Ficción");
        
        System.out.println("  Pruebas con null:");
        libro4.prestar(null);
        ebook4.descargar(null);
        dvd3.reservar(null);
        
        System.out.println("  Pruebas con cadena vacía:");
        libro4.prestar("");
        ebook4.descargar("");
        dvd3.reservar("");
        
        System.out.println("  Pruebas con solo espacios:");
        libro4.prestar("   ");
        ebook4.descargar("   ");
        dvd3.reservar("   ");
        
        System.out.println("\n=== FIN DE PRUEBAS ===");
    }
}
