package Persistence;

import Entities.GestionArchivo;
import Entities.Inscripcion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArchivoInscriptos implements GestionArchivo<Inscripcion> {

    private File archivo;


    public ArchivoInscriptos(String ruta) {
        validarDato(ruta);
        this.archivo = new File(ruta);
    }


    @Override
    public void crear(Inscripcion dato) {
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            String nuevaInscripcion = dato.toStringInscripto();

            ArrayList<String> existentes = listar();
            if (existentes.contains(nuevaInscripcion)) {
                throw new  RuntimeException("La inscripción ya existe en el archivo");
            }

            FileWriter writer = new FileWriter(archivo, true);
            writer.write(nuevaInscripcion + System.lineSeparator());
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar inscripción", e);
        }

    }

    @Override
    public void modificar(Inscripcion dato) {
        throw new RuntimeException("modificar Inscripcion no implementado");
    }

    @Override
    public void eliminar(String id) {
        throw new RuntimeException("eliminar por Id de Inscripcion no implementado");
    }

    @Override
    public void eliminarArchivo(String ruta) {
        validarDato(ruta);
        File archivoAEliminar = new File(ruta);
        if (archivoAEliminar.exists()) {
            if (!archivoAEliminar.delete()) {
                throw new RuntimeException("No se pudo eliminar el archivo: " + ruta);
            }
        } else {
            throw new RuntimeException("El archivo no existe: " + ruta);
        }
    }
    @Override
    public Inscripcion buscar(String id) {
        throw new RuntimeException("Buscar por Id de Inscripcion no implementado");
    }

    @Override
    public ArrayList<String> listar() {
        ArrayList<String> inscripciones = new ArrayList<>();
        
        if (!archivo.exists()) {
            return inscripciones;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                inscripciones.add(linea);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer inscripciones", e);
        }
        
        return inscripciones;
    }

    private void validarDato(String dato){
        if(dato==null || dato.isEmpty())throw new IllegalArgumentException("El dato no puede ser nulo o vacío.");
    }

}
