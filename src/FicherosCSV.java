import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FicherosCSV {

    public ArrayList<LinkedHashMap<String, String>> leerCSV(File fichero) {
        ArrayList<LinkedHashMap<String, String>> contenidoCSV = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            String[] cabeceras = br.readLine().split(",");

            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                LinkedHashMap<String, String> mapa = new LinkedHashMap<>();
                for (int i = 0; i < cabeceras.length; i++) {
                    mapa.put(cabeceras[i], valores[i]);
                }
                contenidoCSV.add(mapa);
            }
        } catch (IOException e) {
            System.err.println("Error de lectura del fichero CSV: " + e.getMessage());
        }

        return contenidoCSV;
    }
}


