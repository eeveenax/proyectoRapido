import java.io.File;
import java.security.KeyStore.Entry;
import java.util.*;

/**
 * @author Evelia Gil Paredes
 * @author Maria Ceballos Mesías
 */

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean seguir = true;
        CarpetaYFichero carpetaFichero = new CarpetaYFichero();
        FicherosJson ficheroJson = new FicherosJson();
        Ficherosxml ficheroXml = new Ficherosxml();
        FicherosCSV ficheroCSV = new FicherosCSV();

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
                        carpeta = carpetaFichero.seleccionCarpeta(new File(ruta));
                        System.out.println("Ruta: " + carpeta.getPath());
                        System.out.println(" ");
                        System.out.println("Contenido de la Carpeta ");
                        carpetaFichero.verContenidoCarpeta(carpeta);
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
                                FicherosCSV ficheroCSV = new FicherosCSV();
                                // contenidoFichero = ficheroCSV.leerCSV(ficheroSeleccionado);
                                break;

                            case "json":
                                contenidoFichero = ficheroJson.leerJson(ficheroSeleccionado);
                                break;

                            case "xml":
                                try {

                                    contenidoFichero = ficheroXml.leerxml(ficheroSeleccionado);

                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }

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

                    System.out.println("Introduce el tipo de Extensión");
                    String extensionSalida = sc.nextLine().toLowerCase();
                    System.out.println("Introduce el nombre del fichero");
                    String nombreFicheroSalida = sc.nextLine();
                    File ficheroSalida = null;

                    if (carpetaFichero.comprobarExtension(extensionSalida)) {
                        ficheroSalida = carpetaFichero
                                .crearFichero(
                                        new File(carpeta.getPath() + "\\" + nombreFicheroSalida + "."
                                                + extensionSalida));
                    } else {
                        System.out.println("Este tipo de ficheros no existe");
                    }

                    switch (extensionSalida) {
                        case "csv":

                            break;
                        case "json":
                            ficheroJson.convertirAJson(contenidoFichero, ficheroSalida);
                            break;

                        default:
                            System.out.println("Introduce la etiqeuta raiz ");
                            String nodopadre = sc.nextLine();
                            System.out.println("Introduce la etiqueta hija ");
                            String nodoHijo = sc.nextLine();
                            ficheroXml.escribirxml(contenidoFichero, nodopadre, nodoHijo,
                                    ficheroSalida);
                            break;
                    }

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
 * 
 * o Conversión a: Finalmente, exportará la
 * información contenida en las estructuras de datos mencionadas en el apartado
 * anterior en el formato seleccionado. Esta opción solo se permitirá tras haber
 * seleccionado un fichero.
 */