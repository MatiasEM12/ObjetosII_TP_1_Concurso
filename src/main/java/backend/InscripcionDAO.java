package backend;

import Entities.Inscripcion;

import java.util.List;

public abstract class InscripcionDAO {

    protected InscripcionDAO(){

    }

    public abstract void create(Inscripcion inscripcion);

    public abstract void update(Inscripcion inscripcion);

    public abstract void remove(String id);

    public abstract void remove(Inscripcion inscripcion);

    public abstract Inscripcion find(String id);

    public abstract List<Inscripcion> findAll();

    public abstract List<String> findAllInscriptos();
}
