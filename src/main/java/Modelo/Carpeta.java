package Modelo;

public class Carpeta extends ArchivoCarpeta{

    private Carpeta carpetaPadre;
    private String path;

    public Carpeta(String nombre){
        super.nombre = nombre;
        super.tipo = "Carpeta";
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

        super.nombreCodificado = codigo;
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
