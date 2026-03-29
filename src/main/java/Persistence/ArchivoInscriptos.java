package Persistence;

import Entities.GestionArchivo;
import Entities.Inscripcion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArchivoInscriptos extends GestionArchivo<Inscripcion> {

    private File archivo;


    public ArchivoInscriptos(String ruta) {
        super(ruta);
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

}
