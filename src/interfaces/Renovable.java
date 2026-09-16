package interfaces;

// Interfaz que define las operaciones de renovación para préstamos
public interface Renovable {
    
    // Renueva el préstamo del material
    void renovar();
    
    // Retorna cuántas veces se ha renovado el material en el préstamo actual
    int vecesRenovado();
    
    // Retorna true si el material aún puede renovarse
    boolean puedeRenovarse();
}
