package Vista;

import Modelo.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Panel extends JPanel {

    private Arbol<ArchivoCarpeta> arbol = new Arbol<>();
    private JTextField directorioActual = new JTextField("Root");
    private JLabel lbdirectorioActual = new JLabel("Carpeta: ");
    private JButton btnSubirDirectorio = new JButton("<--");
    private DefaultTableModel tableModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable jTable;
    private JScrollPane jScrollPane;
    private Carpeta carpetaActual;
    private Logger logger = LogManager.getRootLogger();
    private Configuracion configuracion = Configuracion.getInstance();

    public Panel(int ancho, int alto) {
        setSize(ancho, alto);
        init1();
        carpetaActual = new Carpeta("Root");
        carpetaActual.setPath("Root");
        arbol.insertar(carpetaActual, carpetaActual.getNombreCodificado(), null);
    }

    private void init1() {
        this.setLayout(null);
        int y = 0;
        int x = 0;
        btnSubirDirectorio.setBounds(x, y, 50, 30);
        x += 50;
        lbdirectorioActual.setBounds(x, y, 55, 30);
        x += 55;
        directorioActual.setBounds(x, y, this.getWidth() - x - 15, 30);
        directorioActual.setEditable(false);
        y += 30;
        iniciarTabla();
        jScrollPane.setBounds(0, y, this.getWidth() - 15, this.getHeight() - 90);

        btnSubirDirectorio.addActionListener(e -> {
            salirCarpeta();
        });

        this.add(btnSubirDirectorio);
        this.add(lbdirectorioActual);
        this.add(directorioActual);
        this.add(jScrollPane);
    }

    private void iniciarTabla() {
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Tipo");
        tableModel.addColumn("Tamaño");
        tableModel.addColumn("NombreFisico/ID");
        jTable = new JTable(tableModel);

        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = jTable.getSelectedRow();
                    if (tableModel.getValueAt(fila, 1) == "Carpeta") {
                        entrarCarpeta((String) tableModel.getValueAt(fila, 3));
                    } else {
                        System.out.println("Archivo");
                    }
                }
            }
        });

        jScrollPane = new JScrollPane(jTable);
    }

    private void entrarCarpeta(String id) {
        Arbol.Nodo<ArchivoCarpeta> nodo = arbol.buscar(id);
        Carpeta nuevaCarpetaActual = (Carpeta) nodo.getContenido();

        tableModel.setRowCount(0);
        leerCarpeta(nodo, nuevaCarpetaActual);
        jScrollPane.updateUI();
        directorioActual.setText(carpetaActual.getPath());
    }

    private void salirCarpeta() {
        if (carpetaActual.getCarpetaPadre() == null) {
            return;
        }

        Arbol.Nodo<ArchivoCarpeta> nodo = arbol.buscar(carpetaActual.getCarpetaPadre().getNombreCodificado());
        Carpeta nuevaCarpetaActual = (Carpeta) nodo.getContenido();

        tableModel.setRowCount(0);
        leerCarpeta(nodo, nuevaCarpetaActual);
        jScrollPane.updateUI();
        if (carpetaActual.getPath() != null) {
            directorioActual.setText(carpetaActual.getPath());
        } else {
            directorioActual.setText(carpetaActual.getNombre());
        }
    }

    public void guardarArchivo(int fila){
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int respuesta = fc.showOpenDialog(null);

        if (respuesta == JFileChooser.APPROVE_OPTION){
            File
        }
    }

    private void leerCarpeta(Arbol.Nodo<ArchivoCarpeta> nodo, Carpeta nuevaCarpetaActual) {
        Lista<Arbol.Nodo<ArchivoCarpeta>> hijos = nodo.getHijos();

        for (Arbol.Nodo<ArchivoCarpeta> nodoHijo : hijos) {
            ArchivoCarpeta elemento = nodoHijo.getContenido();
            if (elemento instanceof Carpeta) {
                Carpeta aux = (Carpeta) elemento;
                tableModel.addRow(aux.getDatos());
            } else {
                Archivo aux = (Archivo) elemento;
                tableModel.addRow(aux.getDatos());
            }
        }

        this.carpetaActual = nuevaCarpetaActual;
    }

    public void agregarArchivo(Archivo archivo) {
        tableModel.addRow(archivo.getDatos());
        arbol.insertar(archivo, archivo.getNombreCodificado(), carpetaActual.getNombreCodificado());
        jScrollPane.updateUI();
        logger.info("Se subió el archivo " + archivo.getNombre() + " en la carpeta " + carpetaActual.getNombre());
    }

    public void agregarCarpeta(Carpeta carpeta) {
        tableModel.addRow(carpeta.getDatos());
        arbol.insertar(carpeta, carpeta.getNombreCodificado(), carpetaActual.getNombreCodificado());
        carpeta.setCarpetaPadre(carpetaActual);
        jScrollPane.updateUI();
    }

    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }
}
