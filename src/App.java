import java.util.Scanner;

/**
 * @author Evelia Gil Paredes
 * @author Maria Ceballos Mesías
 */

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean seguir = true;

        while (seguir) {

            System.out.println("Introduce una opción:" + "\n1.Seleccionar Carpeta" + "\n2.Lectura de Fichero"
                    + "\n3.Conversión Fichero" + "\nCulaquir otra opción te sacará del programa");
            int opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1:

                    break;

                case 2:

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
