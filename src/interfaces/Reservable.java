package interfaces;

public interface Reservable {
    void reservar(String usuario);
    void cancelarReserva();
    boolean estaReservado();
}
