package Vista;

import Modelo.Archivo;
import Modelo.Carpeta;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ventana extends JFrame {

    Panel panel;

    public Ventana(){
        init1();
    }

    private void init1() {
        this.setSize(800,600);
        this.setPreferredSize(this.getSize());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Crear");
        JMenuItem subirArchivo = new JMenuItem("Subir Archivo");
        JMenuItem crearCarpeta = new JMenuItem("Crear Carpeta");
        JMenuItem configuracion = new JMenuItem("Ruta de Archivos");

        subirArchivo.addActionListener(e -> {

            if (panel.getConfiguracion().getPath() == null){
                JOptionPane.showMessageDialog(null ,"No se ha configurado una carpeta para guardar " +
                        "los archivos temporales, por favor configure una");
                cambiarRutaArchivosTemp();
            }

            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("."));

            int respuesta = fc.showOpenDialog(null);

            if (respuesta == JFileChooser.APPROVE_OPTION){
                File archivofc = new File(fc.getSelectedFile().getPath());

                String nombre = archivofc.getName();
                long tamaño = archivofc.length();

                Archivo archivo = new Archivo(nombre, tamaño);

                String pathModificado = panel.getConfiguracion().getPath()+"\\"+archivo.getNombreCodificado();

                File archivoRaro = new File(pathModificado);

                try {
                    FileUtils.copyFile(archivofc,archivoRaro);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "No se pudo subir el archivo correctamente");
                    throw new RuntimeException(ex);
                }

                archivo.setPathArchivoModificado(pathModificado);
                panel.agregarArchivo(archivo);
            }
        });

        crearCarpeta.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(null,"Escriba el nombre de la carpeta");
            String regex = "^\s*$";

            Pattern patron = Pattern.compile(regex);
            if (nombre == null){
                return;
            }
            Matcher m = patron.matcher(nombre);

            if (m.find()){
                JOptionPane.showMessageDialog(null, "Ese no es un nombre valido");
            } else {
                nombre = nombre.trim();
                Carpeta carpeta = new Carpeta(nombre);
                panel.agregarCarpeta(carpeta);
            }
        });

        configuracion.addActionListener(e -> {
            if (panel.getConfiguracion().getPath() == null){
                cambiarRutaArchivosTemp();
            } else {
                int respuesta = JOptionPane.showConfirmDialog(null, "Desea cambiar la ruta de guardado?");

                if (respuesta == JOptionPane.YES_OPTION){
                    JFileChooser fc = new JFileChooser(".");
                    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int aux = fc.showDialog(null, "Seleccionar");

                    if (aux == JFileChooser.APPROVE_OPTION){
                        String path = fc.getSelectedFile().getPath();
                        panel.getConfiguracion().setPath(path);
                        JOptionPane.showMessageDialog(null, "Se ha configurado la ruta con exito");
                    }
                }
            }

        });

        panel = new Panel(this.getWidth(), this.getHeight());
        menu.add(subirArchivo);
        menu.add(crearCarpeta);
        menu.add(configuracion);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
        this.add(panel, BorderLayout.CENTER);
        this.pack();
    }

    public void cambiarRutaArchivosTemp(){
        JFileChooser fc = new JFileChooser(".");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int respuesta = fc.showDialog(null, "Seleccionar");

        if (respuesta == JFileChooser.APPROVE_OPTION){
            String path = fc.getSelectedFile().getPath();
            panel.getConfiguracion().setPath(path);
            JOptionPane.showMessageDialog(null, "Se ha configurado la ruta con exito");
        }
    }

}
