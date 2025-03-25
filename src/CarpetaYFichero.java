import java.io.File;
import java.io.IOException;

public class CarpetaYFichero {

    public File seleccionCarpeta(File carpeta) throws Exception {

        if (!carpeta.isDirectory()) {
            throw new Exception("Esta carpeta no existe o es nula");
        }

        return carpeta;

    }

    public void verContenidoCarpeta(File carpeta) {

        File[] contenido = carpeta.listFiles();
        for (File f : contenido) {
            System.out.println(f.getName());
        }

    }

    public File crearFichero(File ficheroCrear) {

        // Verifica si el archivo ya existe, si no, lo crea
        if (!ficheroCrear.exists()) {
            try {
                ficheroCrear.createNewFile();

                System.out.println("Fichero creado en " + ficheroCrear.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El fichero ya existe");
        }

        return ficheroCrear;

    }

    public boolean comprobarExtension(String extension) {

        return (extension.equals("json") || extension.equals("xml") || extension.equals("csv"));
    }
}
