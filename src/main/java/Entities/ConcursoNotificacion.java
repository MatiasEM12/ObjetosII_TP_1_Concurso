package Entities;

public class ConcursoNotificacion implements Concurso{

    private Concurso concurso;


    public ConcursoNotificacion(Concurso concurso) {
        this.concurso = concurso;
    }


    @Override
    public void nuevaInscripcion(Inscripcion inscripcion) {

        concurso.nuevaInscripcion( inscripcion);
        inscripcion.notificarInscripcion(this.concurso.baseMensaje());

    }

    @Override
    public String baseMensaje() {
        return this.concurso.baseMensaje();
    }
}
