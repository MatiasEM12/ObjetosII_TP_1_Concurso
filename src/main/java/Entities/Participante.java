package Entities;

import java.util.ArrayList;
import java.util.Objects;

public class Participante {

    private String nombre;
    private String dni;

    private ArrayList<Puntaje> puntajes;
    private ArrayList<Inscripcion> inscripciones;

    public Participante( String nombre, String dni) {

        validarNombre(nombre);
        validarDni(dni);

        this.nombre = nombre;
        this.dni = dni;
        this.puntajes = new ArrayList<>();
        this.inscripciones=new ArrayList<>();
    }

    public void agregarPuntos(Integer puntos, Concurso concurso) {

        Puntaje puntajeExistente = puntajes.stream()
            .filter(p -> p.getConcurso().equals(concurso))
            .findFirst()
            .orElse(null);

        if (puntajeExistente != null) {
            puntajeExistente.agregarPuntos(puntos);
        } else {

            if( !estaInscripto(concurso)) throw new RuntimeException("El participante no está inscripto en el concurso.");
            Puntaje nuevoPuntaje = new Puntaje(concurso, puntos);
            puntajes.add(nuevoPuntaje);
        }


    }

    public boolean estaInscripto(Concurso concurso) {
        return concurso.estaInscripto(this);
    }

    //VALIDACIONES
    private void validarNombre(String nombre){
        if(nombre == null || nombre.isEmpty()) throw new RuntimeException("El nombre del participante no puede ser nulo o vacío.");
    }

    private void validarDni(String dni){
        if(dni == null || dni.isEmpty()) throw new RuntimeException("El DNI del participante no puede ser nulo o vacío.");

        if(dni.length() != 8 ) throw new RuntimeException("El DNI del participante debe tener 8 dígitos numéricos.");
    }

    private void validarPuntos(Integer puntos){
        if(puntos == null )throw new RuntimeException("Los puntos no pueden ser null.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participante that)) return false;
        return Objects.equals(dni, that.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}
