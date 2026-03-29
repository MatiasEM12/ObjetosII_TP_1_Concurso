import Entities.Inscripcion;
import backend.InscripcionDAO;

import java.util.List;

public class FakeInscripcionDAOJDBC extends InscripcionDAO {



    @Override
    public void create(Inscripcion inscripcion) {

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
        return List.of();
    }
}
