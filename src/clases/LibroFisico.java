package clases;

import interfaces.Prestable;
import interfaces.Renovable;
import interfaces.Reservable;

public class LibroFisico extends MaterialBiblioteca implements Prestable, Renovable, Reservable {
    private String autor;
    private String isbn;
    private String ubicacion;
    private boolean prestado;
    private String usuarioPrestamo;
    private int renovaciones;
    private boolean reservado;
    private String usuarioReserva;
    
    private static final int DIAS_PRESTAMO = 15;
    private static final int MAX_RENOVACIONES = 2;
    
    public LibroFisico(String titulo, String autor, String isbn, String ubicacion) {
        super(titulo);
        this.autor = autor;
        this.isbn = isbn;
        this.ubicacion = ubicacion;
        this.prestado = false;
        this.usuarioPrestamo = null;
        this.renovaciones = 0;
        this.reservado = false;
        this.usuarioReserva = null;
    }
    
    @Override
    public void prestar(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            System.out.println("Error: El nombre de usuario no puede estar vacío");
            return;
        }
        
        if (prestado) {
            System.out.println("Error: El libro ya está prestado a " + usuarioPrestamo);
            return;
        }
        
        if (reservado && !usuario.equals(usuarioReserva)) {
            System.out.println("Error: El libro está reservado para " + usuarioReserva);
            return;
        }
        
        this.prestado = true;
        this.usuarioPrestamo = usuario;
        this.renovaciones = 0;
        
        if (reservado) {
            this.reservado = false;
            this.usuarioReserva = null;
        }
        
        System.out.println("Libro '" + getTitulo() + "' prestado a: " + usuario);
    }
    
    @Override
    public void devolver() {
        if (!prestado) {
            System.out.println("Error: El libro no está prestado");
            return;
        }
        
        System.out.println("Libro '" + getTitulo() + "' devuelto por: " + usuarioPrestamo);
        this.prestado = false;
        this.usuarioPrestamo = null;
        this.renovaciones = 0;
    }
    
    @Override
    public boolean estaPrestado() {
        return prestado;
    }
    
    @Override
    public int diasPrestamo() {
        return DIAS_PRESTAMO;
    }
    
    @Override
    public void renovar() {
        if (!puedeRenovarse()) {
            System.out.println("Error: No se puede renovar este libro");
            return;
        }
        this.renovaciones++;
        System.out.println("Libro '" + getTitulo() + "' renovado. Renovaciones: " + renovaciones);
    }
    
    @Override
    public int vecesRenovado() {
        return renovaciones;
    }
    
    @Override
    public boolean puedeRenovarse() {
        return prestado && renovaciones < MAX_RENOVACIONES;
    }
    
    @Override
    public void reservar(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            System.out.println("Error: El nombre de usuario no puede estar vacío");
            return;
        }
        
        if (prestado) {
            System.out.println("Error: No se puede reservar un libro que está prestado");
            return;
        }
        
        if (reservado) {
            System.out.println("Error: El libro ya está reservado para " + usuarioReserva);
            return;
        }
        
        this.reservado = true;
        this.usuarioReserva = usuario;
        System.out.println("Libro '" + getTitulo() + "' reservado para: " + usuario);
    }
    
    @Override
    public void cancelarReserva() {
        if (!reservado) {
            System.out.println("Error: El libro no tiene una reserva activa");
            return;
        }
        
        System.out.println("Reserva del libro '" + getTitulo() + "' cancelada");
        this.reservado = false;
        this.usuarioReserva = null;
    }
    
    @Override
    public boolean estaReservado() {
        return reservado;
    }
    
    @Override
    public void mostrarInformacion() {
        System.out.println("\n=== LIBRO FÍSICO ===");
        System.out.println("Título: " + getTitulo());
        System.out.println("Autor: " + autor);
        System.out.println("ISBN: " + isbn);
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Prestado: " + (prestado ? "Sí (" + usuarioPrestamo + ")" : "No"));
        System.out.println("Reservado: " + (reservado ? "Sí (" + usuarioReserva + ")" : "No"));
    }
    
    public String getUsuarioPrestamo() { return usuarioPrestamo; }
    public String getUsuarioReserva() { return usuarioReserva; }
    public int getRenovaciones() { return renovaciones; }
}
