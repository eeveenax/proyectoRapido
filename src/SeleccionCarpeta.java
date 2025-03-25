import java.io.File;

public class SeleccionCarpeta {

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
}
