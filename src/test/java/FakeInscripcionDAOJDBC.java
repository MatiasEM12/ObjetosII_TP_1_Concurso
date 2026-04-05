import Entities.Inscripcion;
import backend.InscripcionDAO;

import java.util.ArrayList;
import java.util.List;

public class FakeInscripcionDAOJDBC implements InscripcionDAO {

    List<String>inscriptos;

    public FakeInscripcionDAOJDBC(){
        this.inscriptos= new ArrayList<>();
    }


    @Override
    public void create(Inscripcion inscripcion) {

        if(inscripcion==null) throw new IllegalArgumentException("Inscripcion no puede ser nula");
        inscriptos.add(inscripcion.toStringInscripto());
    }

    @Override
    public void update(Inscripcion inscripcion) {

    }

    @Override
    public void remove(String id) {

    }

    @Override
    public void remove(Inscripcion inscripcion) {

    }

    @Override
    public Inscripcion find(String codigo) {
        return null;
    }

    @Override
    public List<Inscripcion> findAll() {
        return List.of();
    }

    @Override
    public List<String> findAllInscriptos() {
        return this.inscriptos;
    }

    @Override
    public void truncateTabla() {
        this.inscriptos.clear();
    }
}