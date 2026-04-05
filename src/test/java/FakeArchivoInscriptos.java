import Entities.GestionArchivo;
import Entities.Inscripcion;

import java.util.ArrayList;

public class FakeArchivoInscriptos implements GestionArchivo<Inscripcion>  {

    String ruta;
    ArrayList<String> inscriptos;

    public FakeArchivoInscriptos(String ruta){
        this.ruta=ruta;
        inscriptos=new ArrayList<>();
    }

    @Override
    public void crear(Inscripcion dato) {
        if(dato==null) throw new IllegalArgumentException("dato nulo, no sepued epersistir");
        inscriptos.add(dato.toStringInscripto());
    }

    @Override
    public void modificar(Inscripcion dato) {
        throw new IllegalArgumentException("modificar fakeArchivoInscriptos no implementado");
    }

    @Override
    public void eliminar(String id) {
        throw new IllegalArgumentException("eliminar fakeArchivoInscriptos no implementado");
    }

    @Override
    public Inscripcion buscar(String id) {
        throw new IllegalArgumentException("buscar por id fakeArchivoInscriptos no implementado");
    }

    @Override
    public ArrayList<String> listar() {
        return this.inscriptos;
    }
}
