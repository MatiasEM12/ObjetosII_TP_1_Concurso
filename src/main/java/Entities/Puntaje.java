package Entities;

public class Puntaje {
    private static int cont=0;

    private final String id;
    private Concurso concurso;
    private Integer puntos;

    public Puntaje(Concurso concurso, Integer puntos) {

        validarConcurso(concurso);
        validarPuntos(puntos);

        cont++;
        this.id= "PJ" + String.format("%05d", cont);

        this.concurso= concurso;
        this.puntos = puntos;
    }
    public Puntaje(String id,Concurso concurso, Integer puntos) {

        validarConcurso(concurso);
        validarPuntos(puntos);

        this.id=id;
        this.concurso= concurso;
        this.puntos = puntos;
    }


    public void agregarPuntos(Integer puntos) {

        validarPuntos(puntos);
        this.puntos += puntos;
    }

    public Boolean perteneceA(Concurso consurso){

        return this.concurso.equals(concurso);

    }

    public Integer getPuntos() {
        return puntos;
    }

    //VALIDACIONES
    private void validarConcurso(Concurso concurso) {
        if (concurso == null ) throw new IllegalArgumentException("El concurso no puede ser nulo.");
    }

    private void validarPuntos(Integer puntos) {
        if (puntos < 0 || puntos == null) throw new IllegalArgumentException("Los puntos no pueden ser negativos o null.");
    }

    private void validarID(String id){
        if( id == null || id.trim().isEmpty()) throw new RuntimeException("El ID del Puntaje no puede ser nulo o vacío.");
    }
}