package patron.observer;

/**
 *
 * @author JorgeGorrito
 */

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.JViewport;

public class VistaTablaGUI extends JFrame implements IObservador {
    private GestorCambios gestorCambios;
    private JTable tablaSuperior, tablaInferior;
    
    public VistaTablaGUI(){
        // Configuración de la ventana principal
        this.gestorCambios = GestorCambios.obtenerGestorCambios();
        this.gestorCambios.registrarObservable(this);
        
        this.setTitle("Vista Tablas");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 450);
        this.setLocationRelativeTo(null);
        
        // Crear paneles para las tablas con títulos
        JPanel panelSuperior = new JPanel();
        JPanel panelInferior = new JPanel();
        
        // Crear etiquetas (títulos) para las tablas
        JLabel labelSuperior = new JLabel("Tabla Superior");
        JLabel labelInferior = new JLabel("Tabla Inferior");
        
        // Crear modelos de tabla
        DefaultTableModel modeloSuperior = new DefaultTableModel();
        DefaultTableModel modeloInferior = new DefaultTableModel();
        
        // "Fecha","Hora", "CO2", "NO2", "Opacimetro", "Anenometro", "Luxometro", "Ubicacion", "Sensor"
        modeloSuperior.addColumn("Fecha");
        modeloSuperior.addColumn("Hora");
        modeloSuperior.addColumn("CO2");
        modeloSuperior.addColumn("NO2");
        modeloSuperior.addColumn("Opacimetro");
        modeloSuperior.addColumn("Anenometro");
        modeloSuperior.addColumn("Luxometro");
        modeloSuperior.addColumn("Ubicacion");
        modeloSuperior.addColumn("Sensor");
        
        modeloInferior.addColumn("Fecha");
        modeloInferior.addColumn("Hora");
        modeloInferior.addColumn("CO2");
        modeloInferior.addColumn("NO2");
        modeloInferior.addColumn("Opacimetro");
        modeloInferior.addColumn("Anenometro");
        modeloInferior.addColumn("Luxometro");
        modeloInferior.addColumn("Ubicacion");
        modeloInferior.addColumn("Sensor");
        
        // Crear las tablas con sus respectivos modelos
        tablaSuperior = new JTable(modeloSuperior);
        tablaInferior = new JTable(modeloInferior);
        
        // Agregar las tablas a paneles con títulos
        panelSuperior.setLayout(new GridLayout(2, 1)); // Para el título y la tabla
        panelSuperior.add(labelSuperior);
        panelSuperior.add(new JScrollPane(tablaSuperior));
        
        panelInferior.setLayout(new GridLayout(2, 1)); // Para el título y la tabla
        panelInferior.add(labelInferior);
        panelInferior.add(new JScrollPane(tablaInferior));
        
        // Agregar los paneles a la ventana
        getContentPane().setLayout(new GridLayout(2, 1));
        getContentPane().add(panelSuperior);
        getContentPane().add(panelInferior);
        
        // Hacer la ventana visible
        setVisible(true);
    }
    
    @Override
    public void notificar(){
        // Actualizar datos de las tablas
        DefaultTableModel modeloSuperior = (DefaultTableModel) tablaSuperior.getModel();
        modeloSuperior.setRowCount(0);
        for (DatosSensorDTO registro: gestorCambios.cargarPromedioLecturasSensores())
            modeloSuperior.addRow(new Object[]{
                registro.obtenerFecha(), 
                registro.obtenerHora(),
                String.format("%.2f", registro.obtenerCO2()),
                String.format("%.2f", registro.obtenerNO2()),
                String.format("%.2f", registro.obtenerOpacimetro()),
                String.format("%.2f", registro.obtenerAnenometro()),
                String.format("%.2f", registro.obtenerLuxometro()),
                registro.obtenerUbicacion(),
                registro.obtenerNombre()
            });
        
        DefaultTableModel modeloInferior = (DefaultTableModel) tablaInferior.getModel();
        
        modeloInferior.setRowCount(0);
        for (DatosSensorDTO registro: gestorCambios.cargarLecturasSensores())
            modeloInferior.addRow(new Object[]{
                registro.obtenerFecha(), 
                registro.obtenerHora(),
                String.format("%.2f", registro.obtenerCO2()),
                String.format("%.2f", registro.obtenerNO2()),
                String.format("%.2f", registro.obtenerOpacimetro()),
                String.format("%.2f", registro.obtenerAnenometro()),
                String.format("%.2f", registro.obtenerLuxometro()),
                registro.obtenerUbicacion(),
                registro.obtenerNombre()
            });
    }
}
