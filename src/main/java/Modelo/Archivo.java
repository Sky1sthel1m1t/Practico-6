package Modelo;

import java.text.DecimalFormat;

public class Archivo extends ArchivoCarpeta{

    private String pathArchivoModificado;

    public Archivo(String nombreSucio, long tamaño) {
        obtenerTipo(nombreSucio);
        calcularTamaño(tamaño);
        nombreArchivoCodificado();
    }

    public void obtenerTipo(String nombreSucio){
        String extension = "";

        for (int i = nombreSucio.length() - 1; i > 0; i--) {
            char letra = nombreSucio.charAt(i);

            if (letra != '.'){
                extension = letra + extension;
            } else {
                nombreSucio = nombreSucio.substring(0, i);
                break;
            }

        }

        super.nombre = nombreSucio;
        super.tipo = extension;
    }

    public void calcularTamaño(long tamaño){
        double valor;
        DecimalFormat formato = new DecimalFormat("#.00");

        if ((tamaño > 0) && (tamaño < Math.pow(2,10))){

            super.tamaño = tamaño + " B";

        } else if ((tamaño > Math.pow(2,10)) && (tamaño < Math.pow(2,20))){

            valor = tamaño / Math.pow(2,10);
            super.tamaño = formato.format(valor) + " KB";

        } else if ((tamaño > Math.pow(2,20)) && (tamaño < Math.pow(2,30))){

            valor = tamaño / Math.pow(2,20);
            super.tamaño = formato.format(valor) + " MB";

        }

    }

    private void nombreArchivoCodificado(){
        String[] alfabeto = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","u","v",
                "w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","U",
                "V","W","X","Y","Z"};

        String codigo = "";

        for (int i = 0; i < 6; i++) {
            int num = (int) (Math.random() * alfabeto.length);
            codigo += alfabeto[num];
        }

        codigo += ".";

        for (int i = 0; i < 3; i++) {
            int num = (int) (Math.random() * alfabeto.length);
            codigo += alfabeto[num];
        }

        super.nombreCodificado = codigo;
    }

    public String[] getDatos(){
        return new String[]{this.getNombre(), this.getTipo(), this.getTamaño(), this.getNombreCodificado()};
    }

    public String getNombreExtension(){
        return super.nombre + "." + super.tipo;
    }

    public String getPathArchivoModificado() {
        return pathArchivoModificado;
    }

    public void setPathArchivoModificado(String pathArchivoModificado) {
        this.pathArchivoModificado = pathArchivoModificado;
    }
}
