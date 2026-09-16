package clases;

import interfaces.Descargable;
import interfaces.Reservable;

public class Audiolibro extends MaterialBiblioteca implements Descargable, Reservable {
    private String narrador;
    private int duracion;
    private String formato;
    private long tamaño;
    private boolean reservado;
    private String usuarioReserva;
    
    public Audiolibro(String titulo, String narrador, int duracion, String formato, long tamaño) {
        super(titulo);
        this.narrador = narrador;
        this.duracion = duracion;
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
        
        System.out.println("Audiolibro '" + getTitulo() + "' descargado por: " + usuario);
        
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
            System.out.println("Error: El audiolibro ya está reservado para " + usuarioReserva);
            return;
        }
        
        this.reservado = true;
        this.usuarioReserva = usuario;
        System.out.println("Audiolibro '" + getTitulo() + "' reservado para: " + usuario);
    }
    
    @Override
    public void cancelarReserva() {
        if (!reservado) {
            System.out.println("Error: El audiolibro no tiene una reserva activa");
            return;
        }
        
        System.out.println("Reserva del audiolibro '" + getTitulo() + "' cancelada");
        this.reservado = false;
        this.usuarioReserva = null;
    }
    
    @Override
    public boolean estaReservado() {
        return reservado;
    }
    
    @Override
    public void mostrarInformacion() {
        System.out.println("\n=== AUDIOLIBRO ===");
        System.out.println("Título: " + getTitulo());
        System.out.println("Narrador: " + narrador);
        System.out.println("Duración: " + duracion + " minutos");
        System.out.println("Formato: " + formato);
        System.out.println("Tamaño: " + tamaño + " MB");
        System.out.println("Reservado: " + (reservado ? "Sí (" + usuarioReserva + ")" : "No"));
    }
    
    public String getUsuarioReserva() { return usuarioReserva; }
}
