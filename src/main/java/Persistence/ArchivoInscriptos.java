package Persistence;

import Entities.Inscripcion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    public void modificar(Inscripcion dato) {

    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public Inscripcion buscar(int id) {
        return null;
    }
}
