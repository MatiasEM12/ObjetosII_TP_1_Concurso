package Entities;

import java.util.ArrayList;

public interface  GestionArchivo<T> { //Esto le dice a Java que T es un tipo genérico.


    public  void crear(T dato);
    public  void modificar(T dato);
    public  void eliminar(String id);
    public  T buscar(String id);
    public ArrayList <String> listar();


}
