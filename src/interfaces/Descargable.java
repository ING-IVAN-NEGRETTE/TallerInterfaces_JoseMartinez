package interfaces;

// Interfaz que define las operaciones de descarga para materiales digitales
public interface Descargable {
    
    // Registra la descarga del material por un usuario
    void descargar(String usuario);
    
    // Retorna el tamaño del archivo en megabytes
    long tamañoArchivo();
    
    // Retorna el formato del archivo (PDF, EPUB, MP3, AAC, etc.)
    String formatoArchivo();
}
