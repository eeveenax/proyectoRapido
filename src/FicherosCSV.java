import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class FicherosCSV {

    public ArrayList<LinkedHashMap<String, String>> leerCSV(File ficheroEntrada) throws Exception {
        ArrayList<LinkedHashMap<String, String>> contenidoCSV = new ArrayList<>();
        ArrayList<String> encabezados = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ficheroEntrada))) {
            String linea;

            if ((linea = br.readLine()) == null)
                throw new Exception("Texto vacío");

            String[] encabezado = linea.split(",");
            for (String encabezadoActual : encabezado) {
                encabezados.add(encabezadoActual);
            }

            while ((linea = br.readLine()) != null) {

                LinkedHashMap<String, String> fila = new LinkedHashMap<>();
                String[] datos = linea.split(",");
                for (int i = 0; i < datos.length; i++) {
                    fila.put(encabezados.get(i), datos[i]);
                }

                contenidoCSV.add(fila);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return contenidoCSV;
    }

    public void escribirCSV(File ficherosalida, ArrayList<LinkedHashMap<String, String>> contenidoCSV) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficherosalida))) {
            ArrayList<String> encabezados = new ArrayList<>();
            LinkedHashMap<String, String> primerElemento = contenidoCSV.get(0);

            for (Entry<String, String> entrada : primerElemento.entrySet()) {
                encabezados.add(entrada.getKey());
            }

            for (String encabezado : encabezados) {
                bw.write(encabezado + ",");
            }
            bw.write("\n");

            for (LinkedHashMap<String, String> elementos : contenidoCSV) {

                for (Entry<String, String> entrada : elementos.entrySet()) {
                    bw.write(entrada.getValue() + ",");
                }

                bw.write("\n");
            }

        } catch (IOException e) {
            System.err.println("Error al escribir " + e.getMessage());
        }

    }
}
