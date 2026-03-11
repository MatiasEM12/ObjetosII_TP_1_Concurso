package Entities;

public class Puntaje {

    private String nombreConcurso;
    private int puntos;

    public Puntaje(String nombreConcurso, int puntos) {

        validarNombreConcurso( nombreConcurso);
        validarPuntos(puntos);
        this.nombreConcurso = nombreConcurso;
        this.puntos = puntos;
    }

    public String getNombreConcurso() {
        return nombreConcurso;
    }

    public int getPuntos() {
        return puntos;
    }

     public void agregarPuntos(int puntos) {

        validarPuntos(puntos);
        this.puntos += puntos;
    }

    //VALIDACIONES
        private void validarNombreConcurso(String nombreConcurso) {
            if (nombreConcurso == null || nombreConcurso.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre del concurso no puede ser nulo o vacío.");
            }
        }

        private void validarPuntos(Integer puntos) {
            if (puntos < 0 || puntos == null) {
                throw new IllegalArgumentException("Los puntos no pueden ser negativos o null.");
            }
        }
}
