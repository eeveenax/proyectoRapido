import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

        for (LinkedHashMap<String, String> fila : contenidoCSV) {
            for (Entry<String, String> dato : fila.entrySet()) {
                System.out.println(dato.getKey() + " : " + dato.getValue());
            }
        }

        return contenidoCSV;
    }
}
