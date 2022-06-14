package Modelo;

public final class Configuracion {
    private static Configuracion instancia;
    public String path;

    private Configuracion(String path) {
        this.path = path;
    }

    public static Configuracion getInstance(String path){
        if (instancia == null){
            instancia = new Configuracion(path);
        }
        return instancia;
    }
}
