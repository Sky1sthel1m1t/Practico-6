package Modelo;

public class Carpeta extends ArchivoCarpeta{

    private Carpeta carpetaPadre;
    private String path;

    public Carpeta(String nombre){
        super();
        super.nombre = nombre;
        super.tipo = "Carpeta";
    }

    public void setCarpetaPadre(Carpeta carpetaPadre) {
        this.carpetaPadre = carpetaPadre;
        this.path = carpetaPadre.getPath() + "\\" + nombre;
    }

    public String[] getDatos(){
        return new String[]{nombre, tipo, "",super.nombreCodificado};
    }

    public Carpeta getCarpetaPadre() {
        return carpetaPadre;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
