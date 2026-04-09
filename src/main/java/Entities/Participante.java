package Entities;

import java.util.ArrayList;
import java.util.Objects;

public class Participante {

    private static int cont=0;

    private final String id;
    private String nombre;
    private String dni;

    private ArrayList<Puntaje> puntajes;
    private ArrayList<Inscripcion> inscripciones;

    public Participante( String nombre, String dni) {

        validarNombre(nombre);
        validarDni(dni);
        cont++;
        this.id= "P" + String.format("%05d", cont);


        this.nombre = nombre;
        this.dni = dni;
        this.puntajes = new ArrayList<>();
        this.inscripciones=new ArrayList<>();
    }

    public Participante( String id,String nombre, String dni, ArrayList<Puntaje> puntajes, ArrayList<Inscripcion> inscripciones) {

        validarNombre(nombre);
        validarDni(dni);
        validarID(id);
        validarInscripciones(inscripciones);
        validarPuntajes(puntajes);

        this.id=id;
        this.nombre = nombre;
        this.dni = dni;
        this.puntajes = puntajes;
        this.inscripciones= inscripciones;
    }


    public void agregarPuntos(Integer puntos, Concurso concurso) {

        Puntaje puntajeExistente = puntajes.stream()
                .filter(p -> p.perteneceA(concurso))
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

    public Integer obtenerPuntaje(Concurso concurso) {
        Puntaje puntaje = puntajes.stream()
                .filter(p -> p.perteneceA(concurso))
                .findFirst()
                .orElse(null);

        if (puntaje == null) throw new RuntimeException("El participante no tiene puntaje para este concurso.");


        return puntaje.puntos();
    }

    public void agregarInscripcion(Inscripcion inscripcion){
        validarInscripcion(inscripcion);
        this.inscripciones.add(inscripcion);
    }

    public boolean estaInscripto(Concurso concurso) {
        return concurso.estaInscripto(this);
    }


    public String id(){
        return this.id;
    }
    //VALIDACIONES
    private void validarNombre(String nombre){
        if(nombre == null || nombre.isEmpty()) throw new RuntimeException("El nombre del participante no puede ser nulo o vacío.");
    }

    private void validarDni(String dni){
        if(dni == null || dni.isEmpty()) throw new RuntimeException("El DNI del participante no puede ser nulo o vacío.");
        if(dni.length() != 8 ) throw new RuntimeException("El DNI del participante debe tener 8 dígitos numéricos.");
    }

    private void validarInscripcion(Inscripcion inscripcion){
        if(inscripcion == null) throw new RuntimeException("La inscripción no puede ser nula.");
        if(!inscripcion.participante().equals(this)) throw new RuntimeException("La inscripción no corresponde a este participante.");
        if(inscripciones.contains(inscripcion)) throw new RuntimeException("El participante ya tiene esta inscripción.");
    }

    private void validarPuntajes( ArrayList<Puntaje> puntajes){
        if(puntajes == null) throw new RuntimeException("La lista de puntajes no puede ser nula.");
    }

    private void validarInscripciones( ArrayList<Inscripcion> inscripciones){
        if(inscripciones == null) throw new RuntimeException("La lista de inscripciones no puede ser nula.");
    }

    private void validarID(String id){
        if( id == null || id.trim().isEmpty()) throw new RuntimeException("El ID del Participante  no puede ser nulo o vacío.");
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