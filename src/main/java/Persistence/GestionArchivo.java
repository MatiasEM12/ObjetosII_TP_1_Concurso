package Persistence;

public interface GestionArchivo<T> { //Esto le dice a Java que T es un tipo genérico.

    void crear(T dato);
    void modificar(T dato);
    void eliminar(int id);
    T buscar(int id);

}
