import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class FicherosJson {

    public ArrayList<LinkedHashMap<String, String>> leerJson(File fichero) {

        ArrayList<LinkedHashMap<String, String>> contenidoJson = new ArrayList<>();

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
                LinkedHashMap<String, String> mapa = new LinkedHashMap<>();
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

        for (HashMap<String, String> mapa : contenidoJson) {

            for (Map.Entry<String, String> entry : mapa.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
            System.out.println(" ");

        }

        return contenidoJson;

    }

    public void convertirAJson(ArrayList<LinkedHashMap<String, String>> contenido, File ficheroSalida) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSalida))) {

            int numelemtnosArrayL = contenido.size();
            int contadorArray = 0;
            bw.write("[" + "\n");

            for (int i = 0; i < contenido.size(); i++) {

                LinkedHashMap<String, String> bloqueTexto = contenido.get(i);

                int numEntradas = bloqueTexto.size();
                int contador = 0;

                bw.write("{" + "\n");

                for (Entry<String, String> entry : bloqueTexto.entrySet()) {

                    if (contador >= numEntradas - 1)
                        bw.write("\"" + entry.getKey() + "\": \"" + entry.getValue() + "\"\n");

                    else
                        bw.write("\"" + entry.getKey() + "\": \"" + entry.getValue() + "\",\n");

                    contador++;

                }

                if (contadorArray >= numelemtnosArrayL - 1)
                    bw.write("}" + "\n");

                else
                    bw.write("}," + "\n");

                contadorArray++;
            }

            bw.write("]" + "\n");

        } catch (IOException e) {
            System.err.println("Error al escribir " + e.getMessage());
        }

    }

}
