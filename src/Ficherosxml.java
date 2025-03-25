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
            String nodoHijo = "";
            boolean segundaLinea = true;
            boolean leyendoBloque = false;

            if ((linea = br.readLine()) == null)
                throw new Exception("Texto vacío");

            while ((linea = br.readLine()) != null) {

                if (segundaLinea) {
                    nodoHijo = linea.substring(1, linea.indexOf(">"));
                    segundaLinea = false;
                }

                if (linea.contains("<" + nodoHijo + ">")) {
                    mapa = new LinkedHashMap<>();
                    leyendoBloque = true;
                }

                else if (linea.contains("</" + nodoHijo + ">")) {
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
