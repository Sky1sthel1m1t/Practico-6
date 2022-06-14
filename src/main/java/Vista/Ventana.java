package Vista;

import javax.swing.*;

public class Ventana extends JFrame {

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

        menu.add(subirArchivo);
        menu.add(crearCarpeta);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
        this.pack();
    }

}
