import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FicherosCSV {

    public void procesarCSV(File ficheroEntrada, String ficheroSalida) {
        ArrayList<LinkedHashMap<String, String>> contenidoCSV = new ArrayList<>();
        ArrayList<String> encabezados = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ficheroEntrada));
             FileWriter writer = new FileWriter(ficheroSalida)) {

            String[] claves = br.readLine().split(",");
            for (String clave : claves) {
                encabezados.add(clave.trim());
            }

            writer.write(String.join(",", encabezados) + "\n");

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                LinkedHashMap<String, String> filaMapa = new LinkedHashMap<>();

                for (int i = 0; i < encabezados.size(); i++) {
                    String clave = encabezados.get(i);
                    String valor = i < valores.length ? valores[i].trim() : "";
                    filaMapa.put(clave, valor);
                }

                contenidoCSV.add(filaMapa);

                List<String> fila = new ArrayList<>();
                for (String clave : encabezados) {
                    fila.add(filaMapa.getOrDefault(clave, ""));
                }
                writer.write(String.join(",", fila) + "\n");
            }

        } catch (IOException e) {
            System.err.println("Error al procesar el fichero CSV: " + e.getMessage());
        }
    }
}

