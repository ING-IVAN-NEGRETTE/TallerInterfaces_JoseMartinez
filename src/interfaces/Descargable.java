package interfaces;

public interface Descargable {
    void descargar(String usuario);
    long tamañoArchivo();
    String formatoArchivo();
}
