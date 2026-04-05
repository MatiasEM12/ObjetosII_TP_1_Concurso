package backend;

import Entities.Inscripcion;

import java.util.List;

public interface InscripcionDAO {



    public  void create(Inscripcion inscripcion);

    public  void update(Inscripcion inscripcion);

    public  void remove(String id);

    public  void remove(Inscripcion inscripcion);

    public  Inscripcion find(String id);

    public  List<Inscripcion> findAll();

    public  List<String> findAllInscriptos();

    public void truncateTabla();
}
