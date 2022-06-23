package Modelo;

public class ArchivoCarpeta {
    protected String nombre;
    protected String nombreCodificado;
    protected String tipo;
    protected String tamaño;

    public ArchivoCarpeta() {
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
