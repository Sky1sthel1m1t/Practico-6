package Modelo;

public final class Configuracion {
    private static Configuracion instancia;
    private String path;

    private Configuracion() {

    }

    public static Configuracion getInstance() {
        if (instancia == null) {
            instancia = new Configuracion();
        }
        return instancia;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
