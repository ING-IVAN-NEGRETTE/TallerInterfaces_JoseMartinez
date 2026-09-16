package clases;

import interfaces.Prestable;
import interfaces.Reservable;

public class DVD extends MaterialBiblioteca implements Prestable, Reservable {
    private String director;
    private int duracion;
    private String genero;
    private boolean prestado;
    private String usuarioPrestamo;
    private boolean reservado;
    private String usuarioReserva;
    
    private static final int DIAS_PRESTAMO = 5;
    
    public DVD(String titulo, String director, int duracion, String genero) {
        super(titulo);
        this.director = director;
        this.duracion = duracion;
        this.genero = genero;
        this.prestado = false;
        this.usuarioPrestamo = null;
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
            System.out.println("Error: El DVD ya está prestado a " + usuarioPrestamo);
            return;
        }
        
        if (reservado && !usuario.equals(usuarioReserva)) {
            System.out.println("Error: El DVD está reservado para " + usuarioReserva);
            return;
        }
        
        this.prestado = true;
        this.usuarioPrestamo = usuario;
        
        if (reservado) {
            this.reservado = false;
            this.usuarioReserva = null;
        }
        
        System.out.println("DVD '" + getTitulo() + "' prestado a: " + usuario);
    }
    
    @Override
    public void devolver() {
        if (!prestado) {
            System.out.println("Error: El DVD no está prestado");
            return;
        }
        
        System.out.println("DVD '" + getTitulo() + "' devuelto por: " + usuarioPrestamo);
        this.prestado = false;
        this.usuarioPrestamo = null;
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
    public void reservar(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            System.out.println("Error: El nombre de usuario no puede estar vacío");
            return;
        }
        
        if (prestado) {
            System.out.println("Error: No se puede reservar un DVD que está prestado");
            return;
        }
        
        if (reservado) {
            System.out.println("Error: El DVD ya está reservado para " + usuarioReserva);
            return;
        }
        
        this.reservado = true;
        this.usuarioReserva = usuario;
        System.out.println("DVD '" + getTitulo() + "' reservado para: " + usuario);
    }
    
    @Override
    public void cancelarReserva() {
        if (!reservado) {
            System.out.println("Error: El DVD no tiene una reserva activa");
            return;
        }
        
        System.out.println("Reserva del DVD '" + getTitulo() + "' cancelada");
        this.reservado = false;
        this.usuarioReserva = null;
    }
    
    @Override
    public boolean estaReservado() {
        return reservado;
    }
    
    @Override
    public void mostrarInformacion() {
        System.out.println("\n=== DVD ===");
        System.out.println("Título: " + getTitulo());
        System.out.println("Director: " + director);
        System.out.println("Duración: " + duracion + " minutos");
        System.out.println("Género: " + genero);
        System.out.println("Prestado: " + (prestado ? "Sí (" + usuarioPrestamo + ")" : "No"));
        System.out.println("Reservado: " + (reservado ? "Sí (" + usuarioReserva + ")" : "No"));
    }
    
    public String getUsuarioPrestamo() { return usuarioPrestamo; }
    public String getUsuarioReserva() { return usuarioReserva; }
}
