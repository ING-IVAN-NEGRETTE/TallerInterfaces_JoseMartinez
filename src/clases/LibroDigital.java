package clases;

import interfaces.Descargable;
import interfaces.Reservable;

public class LibroDigital extends MaterialBiblioteca implements Descargable, Reservable {
    private String autor;
    private String isbn;
    private String formato;
    private long tamaño;
    private boolean reservado;
    private String usuarioReserva;
    
    public LibroDigital(String titulo, String autor, String isbn, String formato, long tamaño) {
        super(titulo);
        this.autor = autor;
        this.isbn = isbn;
        this.formato = formato;
        this.tamaño = tamaño;
        this.reservado = false;
        this.usuarioReserva = null;
    }
    
    @Override
    public void descargar(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            System.out.println("Error: El nombre de usuario no puede estar vacío");
            return;
        }
        
        System.out.println("eBook '" + getTitulo() + "' descargado por: " + usuario);
        
        if (reservado && usuario.equals(usuarioReserva)) {
            System.out.println("Reserva eliminada para: " + usuarioReserva);
            this.reservado = false;
            this.usuarioReserva = null;
        }
    }
    
    @Override
    public long tamañoArchivo() {
        return tamaño;
    }
    
    @Override
    public String formatoArchivo() {
        return formato;
    }
    
    @Override
    public void reservar(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            System.out.println("Error: El nombre de usuario no puede estar vacío");
            return;
        }
        
        if (reservado) {
            System.out.println("Error: El libro digital ya está reservado para " + usuarioReserva);
            return;
        }
        
        this.reservado = true;
        this.usuarioReserva = usuario;
        System.out.println("eBook '" + getTitulo() + "' reservado para: " + usuario);
    }
    
    @Override
    public void cancelarReserva() {
        if (!reservado) {
            System.out.println("Error: El libro digital no tiene una reserva activa");
            return;
        }
        
        System.out.println("Reserva del eBook '" + getTitulo() + "' cancelada");
        this.reservado = false;
        this.usuarioReserva = null;
    }
    
    @Override
    public boolean estaReservado() {
        return reservado;
    }
    
    @Override
    public void mostrarInformacion() {
        System.out.println("\n=== LIBRO DIGITAL ===");
        System.out.println("Título: " + getTitulo());
        System.out.println("Autor: " + autor);
        System.out.println("ISBN: " + isbn);
        System.out.println("Formato: " + formato);
        System.out.println("Tamaño: " + tamaño + " MB");
        System.out.println("Reservado: " + (reservado ? "Sí (" + usuarioReserva + ")" : "No"));
    }
    
    public String getUsuarioReserva() { return usuarioReserva; }
}
