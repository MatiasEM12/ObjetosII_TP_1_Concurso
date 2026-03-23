package Persistence;

public class ArchivoConcursoInscriptos implements  GestionArchivo<String> {

    private String nombre;


    public ArchivoConcursoInscriptos(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void crear(String dato) {

    }

    @Override
    public void modificar(String dato) {

    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public String buscar(int id) {
        return "";
    }
}
