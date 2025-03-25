import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LecturaCSV {

    String rutaArchivocsv = "PROYECTORAPIDO/src/coches.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivocsv))) {
        String linea;
        
       
        while ((linea = br.readLine()) != null) {
          
            String[] valores = linea.split(",");
            
            
            for (String valor : valores) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    } catch (IOException e) {
        System.out.println("Error al leer el archivo: " + e.getMessage());
    }
}





