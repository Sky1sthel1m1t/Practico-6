package Modelo;

import java.text.DecimalFormat;

public class Archivo extends ArchivoCarpeta{

    private String pathOriginal;

    public  Archivo(String nombreSucio, long tamaño, String pathOriginal) {
        super();
        obtenerTipo(nombreSucio);
        calcularTamaño(tamaño);
        this.pathOriginal = pathOriginal;
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

        if ((tamaño > Math.pow(2,0)) && (tamaño < Math.pow(2,10))){

            super.tamaño = tamaño + " B";

        } else if ((tamaño > Math.pow(2,10)) && (tamaño < Math.pow(2,20))){

            valor = tamaño / Math.pow(2,10);
            super.tamaño = formato.format(valor) + " KB";

        } else if ((tamaño > Math.pow(2,20)) && (tamaño < Math.pow(2,30))){

            valor = tamaño / Math.pow(2,20);
            super.tamaño = formato.format(valor) + " MB";

        }

    }

    public String[] getDatos(){
        return new String[]{this.getNombre(), this.getTipo(), this.getTamaño(), this.getNombreCodificado()};
    }

    public String getPathOriginal() {
        return pathOriginal;
    }

    public void setPathOriginal(String pathOriginal) {
        this.pathOriginal = pathOriginal;
    }
}
