import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SistemaAviones {
    public static void main(String[] args) {
        GrafoEtiquetadoLista grafoCiudades = new GrafoEtiquetadoLista();
        Avion[] arrAviones = new Avion[10];
        GestorAviones gestor;

        cargarCiudades(grafoCiudades);
        cargarRutas(grafoCiudades);
        cargarAviones(arrAviones, grafoCiudades);

        gestor = new GestorAviones(arrAviones);

        gestor.iniciar();
}

    public static void cargarCiudades(GrafoEtiquetadoLista elGrafoCiudades) {
        /*Realiza la carga de las ciudades en el grafo etiquetado..*/
        String rutaArchivo = "datos/Ciudades.txt";
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                elGrafoCiudades.insertarVertice(linea);
            }
        } catch (IOException exc) {
            System.err.println("Error al leer el archivo: " + exc.getMessage());
        }

    }

    public static void cargarRutas(GrafoEtiquetadoLista elGrafoCiudades) {
        /*Realiza la carga de rutas entre ciudades previamente cargadas.
        Estas rutas son las que los aviones siguen.*/
        String rutaArchivo = "datos/ArcosCiudades.txt";
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            String[] arrCiudadesAux;
            while ((linea = br.readLine()) != null) {
                arrCiudadesAux = linea.split(";");
                elGrafoCiudades.insertarArco(arrCiudadesAux[0], arrCiudadesAux[1], 1);
            }
        } catch (IOException exc) {
            System.err.println("Error al leer el archivo: " + exc.getMessage());
        }
        
    }

    public static void cargarAviones(Avion[] arrAviones, GrafoEtiquetadoLista elGrafoCiudades) {
        //Realiza la carga de los aviones (solamente id)
        String rutaArchivo = "datos/Aviones.txt";
        int i = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            String[] arrDatosAvion;
            while ((linea = br.readLine()) != null) {
                arrDatosAvion = linea.split(";");
                arrAviones[i] = new Avion(arrDatosAvion[0], elGrafoCiudades.caminoMasCorto(arrDatosAvion[1], arrDatosAvion[2]));
                i++;          
            }
        } catch (IOException exc) {
            System.err.println("Error al leer el archivo: " + exc.getMessage());
        }

    }
}
