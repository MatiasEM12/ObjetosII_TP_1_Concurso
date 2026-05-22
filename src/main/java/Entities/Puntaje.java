package Entities;

public class Puntaje {
    private static int cont=0;

    private final String id;
    private ConcursoBase concursoBase;
    private Integer puntos;

    public Puntaje(ConcursoBase concursoBase, Integer puntos) {

        validarConcurso(concursoBase);
        validarPuntos(puntos);

        cont++;
        this.id= "PTJ" + String.format("%05d", cont);

        this.concursoBase = concursoBase;
        this.puntos = puntos;
    }
    public Puntaje(String id, ConcursoBase concursoBase, Integer puntos) {

        validarConcurso(concursoBase);
        validarPuntos(puntos);

        this.id=id;
        this.concursoBase = concursoBase;
        this.puntos = puntos;
    }


    public void agregarPuntos(Integer puntos) {

        validarPuntos(puntos);
        this.puntos += puntos;
    }

    public Boolean perteneceA(ConcursoBase consurso){

        return this.concursoBase.equals(concursoBase);

    }

    public Integer puntos() {
        return puntos;
    }

    //VALIDACIONES
    private void validarConcurso(ConcursoBase concursoBase) {
        if (concursoBase == null ) throw new IllegalArgumentException("El concurso no puede ser nulo.");
    }

    private void validarPuntos(Integer puntos) {
        if (puntos < 0 || puntos == null) throw new IllegalArgumentException("Los puntos no pueden ser negativos o null.");
    }

    private void validarID(String id){
        if( id == null || id.trim().isEmpty()) throw new RuntimeException("El ID del Puntaje no puede ser nulo o vacío.");
    }
}