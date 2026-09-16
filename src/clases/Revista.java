package clases;

import interfaces.Prestable;
import interfaces.Renovable;

public class Revista extends MaterialBiblioteca implements Prestable, Renovable {
    private int numero;
    private String fechaPublicacion;
    private String editorial;
    private boolean prestado;
    private String usuarioPrestamo;
    private int renovaciones;
    
    private static final int DIAS_PRESTAMO = 7;
    private static final int MAX_RENOVACIONES = 1;
    
    public Revista(String titulo, int numero, String fechaPublicacion, String editorial) {
        super(titulo);
        this.numero = numero;
        this.fechaPublicacion = fechaPublicacion;
        this.editorial = editorial;
        this.prestado = false;
        this.usuarioPrestamo = null;
        this.renovaciones = 0;
    }
    
    @Override
    public void prestar(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            System.out.println("Error: El nombre de usuario no puede estar vacío");
            return;
        }
        
        if (prestado) {
            System.out.println("Error: La revista ya está prestada a " + usuarioPrestamo);
            return;
        }
        
        this.prestado = true;
        this.usuarioPrestamo = usuario;
        this.renovaciones = 0;
        System.out.println("Revista '" + getTitulo() + "' prestada a: " + usuario);
    }
    
    @Override
    public void devolver() {
        if (!prestado) {
            System.out.println("Error: La revista no está prestada");
            return;
        }
        
        System.out.println("Revista '" + getTitulo() + "' devuelta por: " + usuarioPrestamo);
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
            System.out.println("Error: No se puede renovar esta revista");
            return;
        }
        this.renovaciones++;
        System.out.println("Revista '" + getTitulo() + "' renovada. Renovaciones: " + renovaciones);
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
    public void mostrarInformacion() {
        System.out.println("\n=== REVISTA ===");
        System.out.println("Título: " + getTitulo());
        System.out.println("Número: " + numero);
        System.out.println("Fecha: " + fechaPublicacion);
        System.out.println("Editorial: " + editorial);
        System.out.println("Prestada: " + (prestado ? "Sí (" + usuarioPrestamo + ")" : "No"));
    }
    
    public String getUsuarioPrestamo() { return usuarioPrestamo; }
    public int getRenovaciones() { return renovaciones; }
}
