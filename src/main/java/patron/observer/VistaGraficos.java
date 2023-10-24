package patron.observer;

/**
 *
 * @author JorgeGorrito
 */


import javax.swing.JFrame;
import javax.swing.JSplitPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class VistaGraficos extends JFrame implements IObservador {
    private GestorCambios gestorCambios;
    private JFreeChart chart1, chart2;
    private ChartPanel panel1, panel2;
    public VistaGraficos(){
        this.gestorCambios = GestorCambios.obtenerGestorCambios();
        this.gestorCambios.registrarObservable(this);

        // Configuracion de la ventana
        this.setTitle("Vista Gráficos"); // Título de la ventana
        this.setSize(700, 450); // Tamaño de la ventana
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Acción al cerrar la ventana

        // Crear el panel con el gráfico 1 de barras
        this.chart1 = crearGraficoDatosPorSensor(); // Llamar al método que crea el gráfico
        this.panel1 = new ChartPanel(chart1); // Crear el panel a partir del gráfico
        
        // Crear el panel con el gráfico 2 de barras
        this.chart2 = crearGraficoDatosPromedios(); // Llamar al método que crea el gráfico
        this.panel2 = new ChartPanel(chart2); // Crear el panel a partir del gráfico

        // Crear un JSplitPane para dividir la ventana en dos paneles
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, panel2);
        splitPane.setResizeWeight(0.5); // Distribuir el espacio por igual

        // Agregar el JSplitPane a la ventana
        this.add(splitPane);

        // Hacer visible la ventana
        this.setVisible(true);
    }   
    public JFreeChart crearGraficoDatosPromedios() {
        // Crear el conjunto de datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        
        for (DatosSensorDTO datoPromedio: this.gestorCambios.cargarPromedioLecturasSensores()){
            dataset.addValue(datoPromedio.obtenerCO2(), datoPromedio.obtenerNombre(), "CO2");
            dataset.addValue(datoPromedio.obtenerNO2(), datoPromedio.obtenerNombre(), "NO2");
        }

        // Crear el gráfico
        JFreeChart chart = ChartFactory.createBarChart(
            "Datos Promedios por Sensor", // Título del gráfico
            "Sensores", // Etiqueta del eje X
            "Medicion", // Etiqueta del eje Y
            dataset, // Datos
            PlotOrientation.VERTICAL, // Orientación
            true, // Incluir leyenda
            true, // Incluir tooltips
            true // Incluir URLs
        );

        return chart;
    }
  
    public JFreeChart crearGraficoDatosPorSensor() {
        // Crear el conjunto de datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        
        for (RegistroDatosSensores registro: this.gestorCambios.cargarRegistroDatosSensores()){
            dataset.addValue(registro.obtenerCantidad(), registro.obtenerNombre(), "Sensores");
        }

        // Crear el gráfico
        JFreeChart chart = ChartFactory.createBarChart(
            "Datos enviados por Sensor", // Título del gráfico
            "Sensores", // Etiqueta del eje X
            "Cantidad", // Etiqueta del eje Y
            dataset, // Datos
            PlotOrientation.VERTICAL, // Orientación
            true, // Incluir leyenda
            true, // Incluir tooltips
            true // Incluir URLs
        );

        return chart;
    }

    
    
    @Override
    public void notificar() {
        // Actualizar los datos en el conjunto de datos existente
        DefaultCategoryDataset dataset = (DefaultCategoryDataset) chart1.getCategoryPlot().getDataset();
        dataset.clear(); // Limpia los datos existentes
        
        DefaultCategoryDataset dataset2 = (DefaultCategoryDataset) chart2.getCategoryPlot().getDataset();
        dataset2.clear(); // Limpia los datos existentes
        

        for (RegistroDatosSensores registro: this.gestorCambios.cargarRegistroDatosSensores()) {
            dataset.addValue(registro.obtenerCantidad(), registro.obtenerNombre(), "Sensores");
        }
        
        for (DatosSensorDTO promedioSensor: this.gestorCambios.cargarPromedioLecturasSensores()) {
            dataset2.addValue(promedioSensor.obtenerCO2(), promedioSensor.obtenerNombre(), "CO2");
            dataset2.addValue(promedioSensor.obtenerNO2(), promedioSensor.obtenerNombre(), "NO2");
        }

        // Llamar a repaint en el ChartPanel para que se actualice la gráfica
        panel1.repaint();
        panel2.repaint();
    }

}
