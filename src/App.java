import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * @author Evelia Gil Paredes
 * @author Maria Ceballos Mesías
 */

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean seguir = true;
        SeleccionCarpeta seleccionCarpeta = new SeleccionCarpeta();
        FicherosJson lecturaJson = new FicherosJson();
        File carpeta = null;
        ArrayList<LinkedHashMap<String, String>> contenidoFichero = new ArrayList<>();
        while (seguir) {

            System.out.println("Introduce una opción:" + "\n1.Seleccionar Carpeta" + "\n2.Lectura de Fichero"
                    + "\n3.Conversión Fichero" + "\nCualquier otra opción te sacará del programa");
            int opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1:
                    System.out.println("Escribe la ruta de la carpeta");
                    String ruta = sc.nextLine();

                    try {
                        carpeta = seleccionCarpeta.seleccionCarpeta(new File(ruta));
                        System.out.println("Ruta: " + carpeta.getPath());
                        System.out.println(" ");
                        System.out.println("Contenido de la Carpeta ");
                        seleccionCarpeta.verContenidoCarpeta(carpeta);
                        System.out.println(" ");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 2:
                    System.out.println("Introduce el nombre del fichero");
                    String nombrefichero = sc.nextLine();
                    String extension = nombrefichero.substring(nombrefichero.lastIndexOf(".") + 1).toLowerCase();

                    if (carpeta == null) {
                        System.out.println("Introduzca una ruta de carpeta válida, opción 1");
                        return;

                    }

                    File ficheroSeleccionado = new File(carpeta.getPath() + "\\" + nombrefichero);
                    if (ficheroSeleccionado.isFile()) {
                        System.out
                                .println("El fichero seleccionado es " + nombrefichero + "\nSu extensión es "
                                        + extension);
                        switch (extension) {
                            case "csv":
                                break;

                            case "json":

                                contenidoFichero = lecturaJson.leerJson(ficheroSeleccionado);
                                break;

                            case "xml":
                                break;

                            default:
                                System.out.println("El fichero no es de tipo csv, json o xml");
                                break;
                        }

                    } else {

                        System.out.println("El fichero seleccionado es incorrecto");
                    }

                    break;

                case 3:

                    break;

                default:
                    seguir = false;
                    break;
            }
        }

        sc.close();
    }
}

/*
 * o Fichero seleccionado.
 */