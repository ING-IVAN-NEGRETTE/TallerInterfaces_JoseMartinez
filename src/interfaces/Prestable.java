package interfaces;

// Interfaz que define las operaciones de préstamo para materiales de la biblioteca
public interface Prestable {
    
    // Registra el préstamo del material a un usuario
    void prestar(String usuario);
    
    // Registra la devolución del material
    void devolver();
    
    // Retorna true si el material está actualmente prestado
    boolean estaPrestado();
    
    // Retorna el número de días permitidos para el préstamo
    int diasPrestamo();
}
