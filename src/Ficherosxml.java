import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class Ficherosxml {

    public ArrayList<LinkedHashMap<String, String>> leerxml(File fichero) throws Exception {
        ArrayList<LinkedHashMap<String, String>> contenidoxml = new ArrayList<>();
        LinkedHashMap<String, String> mapa = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            boolean leyendoBloque = false;
            String nodoHijo = "";
            if ((linea = br.readLine()) == null)
                throw new Exception("El archivo está vacío");
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                if (linea.startsWith("<") && linea.endsWith(">")) {
                    String etiqueta = linea.substring(1, linea.length() - 1);

                    if (!leyendoBloque && !etiqueta.startsWith("/")) {
                        nodoHijo = etiqueta;
                        mapa = new LinkedHashMap<>();
                        leyendoBloque = true;
                    } else if (leyendoBloque && etiqueta.equals("/" + nodoHijo)) {
                        if (mapa != null) {
                            contenidoxml.add(mapa);
                        }
                        leyendoBloque = false;
                    } else if (leyendoBloque && !etiqueta.startsWith("/")) {
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contenidoxml;
    }

    public void escribirxml(ArrayList<LinkedHashMap<String, String>> contenidoXml, String nombreNodoPadre,
            String nodoHijo, File ficherosalida) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficherosalida))) {
            bw.write("<" + nombreNodoPadre + ">\n");
            for (LinkedHashMap<String, String> elementos : contenidoXml) {
                bw.write("<" + nodoHijo + ">\n");
                for (Entry<String, String> entrada : elementos.entrySet()) {
                    bw.write("<" + entrada.getKey() + ">" + entrada.getValue() + "</" + entrada.getKey() + ">\n");
                }
                bw.write("</" + nodoHijo + ">\n");
            }
            bw.write("</" + nombreNodoPadre + ">\n");
        } catch (IOException e) {
            System.err.println("Error al escribir " + e.getMessage());
        }
    }
}
