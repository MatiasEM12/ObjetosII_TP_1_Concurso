package Persistence;

import Entities.Inscripcion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArchivoInscriptos implements  GestionArchivo<Inscripcion> {

    private File archivo;


    public ArchivoInscriptos(String archivo) {
        this.archivo = new File(archivo);
    }


    @Override
    public void crear(Inscripcion dato) {
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            FileWriter writer = new FileWriter(archivo, true);
            writer.write(dato.toString() + System.lineSeparator());
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar inscripción", e);
        }

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
