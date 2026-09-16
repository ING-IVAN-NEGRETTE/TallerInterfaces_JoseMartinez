package clases;

public abstract class MaterialBiblioteca {
    private String titulo;
    
    public MaterialBiblioteca(String titulo) {
        this.titulo = titulo;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public abstract void mostrarInformacion();
}
