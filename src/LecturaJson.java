import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class LecturaJson {

    public ArrayList<HashMap<String, String>> leerJson(File fichero) {

        ArrayList<HashMap<String, String>> contenidoJson = new ArrayList<>();

        // try-with-resources: BufferedReader se cierra automáticamente al finalizar el
        // bloque try
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {

            StringBuilder sb = new StringBuilder();
            String linea;

            while ((linea = br.readLine()) != null) {
                sb.append(linea);
            }

            String jsonTexto = sb.toString().trim();

            if (jsonTexto.startsWith("[")) {
                jsonTexto = jsonTexto.substring(1, jsonTexto.length() - 1);
            }
            String[] jsonBloqueTexto = jsonTexto.split("},\\s*\\{");

            for (String jsonelemento : jsonBloqueTexto) {
                jsonelemento = jsonelemento.replace("{", "").replace("}", "");
                String[] claveValor = jsonelemento.split(",");
                HashMap<String, String> mapa = new HashMap<>();
                for (String pair : claveValor) {
                    String[] entrada = pair.split(":");
                    String calve = entrada[0].trim().replace("\"", "");
                    String valor = entrada[1].trim().replace("\"", "");
                    mapa.put(calve, valor);
                }

                contenidoJson.add(mapa);
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return contenidoJson;

    }

}
