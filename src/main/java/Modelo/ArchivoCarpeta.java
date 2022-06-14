package Modelo;

public class ArchivoCarpeta {
    private String path;
    private String nombre;
    private String nombreCodificado;
    private int tamano;
    private boolean tipo;

    public ArchivoCarpeta(String path, String nombre, int tamano, boolean tipo) {
        this.path = path;
        this.nombre = nombre;
        this.tamano = tamano;
        this.tipo = tipo;
        codificar();
    }

    private void codificar(){
        String[] alfabeto = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","u","v",
                "w","x","y","z","0","1","2","3","4","5","6","7","8","9"};

        String codigo = "";

        for (int i = 0; i < 6; i++) {
            int num = (int) (Math.random() * alfabeto.length);
            codigo += alfabeto[num];
        }

        this.nombreCodificado = codigo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }
}
