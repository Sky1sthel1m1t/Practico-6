package Modelo;

public class ArchivoCarpeta {
    protected String nombre;
    protected String nombreCodificado;
    protected String tipo;
    protected String tamaño;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCodificado() {
        return nombreCodificado;
    }

    public void setNombreCodificado(String nombreCodificado) {
        this.nombreCodificado = nombreCodificado;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
