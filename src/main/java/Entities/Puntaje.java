package Entities;

public class Puntaje {

    private Concurso concurso;
    private Integer puntos;

    public Puntaje(Concurso concurso, Integer puntos) {

        validarConcurso(concurso);
        validarPuntos(puntos);
        this.concurso= concurso;
        this.puntos = puntos;
    }


    public Integer getPuntos() {
        return puntos;
    }

    public Concurso getConcurso() {
        return concurso;

    }
     public void agregarPuntos(Integer puntos) {

        validarPuntos(puntos);
        this.puntos += puntos;
    }

    //VALIDACIONES
        private void validarConcurso(Concurso concurso) {
            if (concurso == null ) {
                throw new IllegalArgumentException("El concurso no puede ser nulo.");
            }
        }

        private void validarPuntos(Integer puntos) {
            if (puntos < 0 || puntos == null) {
                throw new IllegalArgumentException("Los puntos no pueden ser negativos o null.");
            }
        }
}
