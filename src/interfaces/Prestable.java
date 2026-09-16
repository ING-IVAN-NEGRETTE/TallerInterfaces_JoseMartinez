package interfaces;

public interface Prestable {
    void prestar(String usuario);
    void devolver();
    boolean estaPrestado();
    int diasPrestamo();
}
