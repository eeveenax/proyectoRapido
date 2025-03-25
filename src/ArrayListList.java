import java.util.ArrayList;

public class ArrayListList<T> {
    private ArrayList<T> lista;

    public ArrayListList() {
        lista = new ArrayList<>();
    }

    public void agregar(T elemento) {
        lista.add(elemento);
    }

    public void eliminar(int indice) {
        if (indice >= 0 && indice < lista.size()) {
            lista.remove(indice);
        } else {
            System.out.println("Índice fuera de rango");
        }
    }

    public T obtener(int indice) {
        if (indice >= 0 && indice < lista.size()) {
            return lista.get(indice);
        } else {
            System.out.println("Índice fuera de rango");
            return null;
        }
    }

    public void mostrar() {
        for (T elemento : lista) {
            System.out.println(elemento);
        }
    }

    public int tamano() {
        return lista.size();
    }

}
