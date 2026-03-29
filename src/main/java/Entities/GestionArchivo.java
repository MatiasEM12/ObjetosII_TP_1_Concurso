package Entities;

import java.util.ArrayList;

public abstract class GestionArchivo<T> { //Esto le dice a Java que T es un tipo genérico.

    private String ruta;

    protected GestionArchivo(String ruta){
        validarRuta(ruta);
        this.ruta=ruta;
    }
    public abstract void crear(T dato);
    public abstract void modificar(T dato);
    public abstract void eliminar(String id);
    public abstract T buscar(String id);
    public abstract ArrayList <String> listar();

    private void validarRuta(String ruta){
        if(ruta==null || ruta.isEmpty())throw new IllegalArgumentException("La ruta del archivo no puede ser nula o vacía.");

    }
}
