import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Ficherosxml {

    public ArrayList<LinkedHashMap<String, String>> leerxml(File fichero) throws Exception {

        ArrayList<LinkedHashMap<String, String>> contenidoxml = new ArrayList<>();
        LinkedHashMap<String, String> mapa = null;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            String nodoInicio = null;
            String nodoFin = null;
            boolean segundaLinea = true;
            boolean leyendoBloque = false;

            if ((linea = br.readLine()) == null)
                throw new Exception("Texto vacío");

            while ((linea = br.readLine()) != null) {

                if (segundaLinea) {
                    nodoInicio = linea.trim();
                    nodoFin = "</" + nodoInicio.substring(1, nodoInicio.length() - 1) + ">";

                    segundaLinea = false;
                }

                if (linea.contains(nodoInicio)) {
                    mapa = new LinkedHashMap<>();
                    leyendoBloque = true;
                }

                else if (linea.contains(nodoFin)) {
                    if (mapa != null) {
                        contenidoxml.add(mapa);
                    }
                    leyendoBloque = false;
                }

                if (leyendoBloque && linea.contains("<") && linea.contains(">")) {
                    int iniClave = linea.indexOf("<") + 1;
                    int finClave = linea.indexOf(">");
                    int iniValor = finClave + 1;
                    int finValor = linea.lastIndexOf("<");

                    if (iniClave < finClave && iniValor < finValor) {
                        String clave = linea.substring(iniClave, finClave);
                        String valor = linea.substring(iniValor, finValor);
                        mapa.put(clave, valor);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * for (LinkedHashMap<String, String> map : contenidoxml) {
         * for (Map.Entry<String, String> entry : map.entrySet()) {
         * System.out.println(entry.getKey() + ": " + entry.getValue());
         * }
         * System.out.println(" ");
         * }
         */

        return contenidoxml;
    }
}
