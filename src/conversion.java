import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Conversion  {

    public void convertirACSV(ArrayList<LinkedHashMap<String, String>> datos, String nombreFichero) {
        try (FileWriter writer = new FileWriter(nombreFichero)) {
            if (!datos.isEmpty()) {

                Set<String> cabeceras = datos.get(0).keySet();
                writer.write(String.join(",", cabeceras) + "\n");
                for (LinkedHashMap<String, String> fila : datos) {
                    List<String> valores = new ArrayList<>();
                    for (String clave : cabeceras) {
                        valores.add(fila.getOrDefault(clave, ""));
                    }
                    writer.write(String.join(",", valores) + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error al convertir a CSV: " + e.getMessage());
            }
        }


        //Aquí tenemos que hacer el de json


        //Aquí tenemos que hacer el metodo de xml
    }
