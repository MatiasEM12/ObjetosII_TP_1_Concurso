package Entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


public class Concurso {

    private static int cont=0;

    private final String  id;
    private String nombre;
    private ArrayList<Inscripcion> inscriptos;
    private LocalDate fechaInicioInscripcion;
    private LocalDate fechaFinInscripcion;

    public static final Integer PUNTOS_PRIMER_DIA = 10;


    public Concurso(String nombre, LocalDate fechaInicioInscripcion, LocalDate fechaFinInscripcion) {

        validarNombre(nombre);
        validarFecha(fechaInicioInscripcion);
        validarFecha(fechaFinInscripcion);
        validarFechasInscripcion(fechaInicioInscripcion,fechaFinInscripcion);

        cont++;
        this.id= "C" + String.format("%05d", cont);

        this.nombre = nombre;
        this.inscriptos = new ArrayList<>();
        this.fechaInicioInscripcion = fechaInicioInscripcion;
        this.fechaFinInscripcion = fechaFinInscripcion;
    }

    public Concurso(String id,String nombre, LocalDate fechaInicioInscripcion, LocalDate fechaFinInscripcion) {

        validarNombre(nombre);
        validarFecha(fechaInicioInscripcion);
        validarFecha(fechaFinInscripcion);
        validarFechasInscripcion(fechaInicioInscripcion,fechaFinInscripcion);
        validarID(id);
        this.id=id;
        this.nombre = nombre;
        this.inscriptos = new ArrayList<>();
        this.fechaInicioInscripcion = fechaInicioInscripcion;
        this.fechaFinInscripcion = fechaFinInscripcion;
    }


    public void nuevaInscripcion(Inscripcion inscripcion) {

        validarInscripcion(inscripcion);
        validarPeridoInscripcion(inscripcion);

        if(estaInscripto(inscripcion.getParticipante()))  throw new IllegalArgumentException("El participante se encuentra inscripto");;

        this.inscriptos.add(inscripcion);
        inscripcion.cargarConcurso(this);
        inscripcion.asignarInscripcion();


        if(esInscriptoPrimerDia(inscripcion)) {
            inscripcion.agregarPuntos(PUNTOS_PRIMER_DIA,this);
            System.out.println("Inscripto el primer dia");
        }
    }

    private Boolean esInscriptoPrimerDia(Inscripcion inscripcion){
        validarInscripcion(inscripcion);

        return inscripcion.getFechaInscripcion().isEqual(this.fechaInicioInscripcion);
    }

    public boolean estaInscripto(Participante participante) {
        validarParticipante(participante);
        return  inscriptos.stream().anyMatch(i -> i.getParticipante().equals(participante));
    }

    public String getId() {
        return id;
    }


    //VALIDACIONES
    private void validarNombre(String nombre){
        if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("El nombre del concurso no puede ser nulo o vacío.");
    }
    private void validarFecha(LocalDate fecha){
        if (fecha == null) throw new IllegalArgumentException("La fecha no puede ser nula.");
    }
    private void validarFechasInscripcion(LocalDate fechaInicio, LocalDate fechaFin){
        validarFecha(fechaInicio);
        validarFecha(fechaFin);
        if ( !fechaInicio.isBefore(fechaFin) ) throw new IllegalArgumentException("La fecha de inicio de inscripción debe ser anterior a la fecha de fin de inscripción.");

    }
    private void validarPeridoInscripcion(Inscripcion inscripcion){
        nuevaInscripcion(inscripcion);
        if (inscripcion.getFechaInscripcion().isBefore(this.fechaInicioInscripcion) ||
                inscripcion.getFechaInscripcion().isAfter(this.fechaFinInscripcion)){

            throw new IllegalArgumentException("La inscripción no se encuentra dentro del período permitido.");

        };

    }
    private void validarInscripcion(Inscripcion inscripcion){
        if(inscripcion == null) throw new IllegalArgumentException("La inscripción no es válida o ya existe.");
    }
    private void validarParticipante (Participante participante){
        if(participante == null) throw new IllegalArgumentException("El participante no puede ser nulo.");

    }
    private void validarID( String id){
        if(  id == null || id.trim().isEmpty()) throw new IllegalArgumentException("El ID del concurso no puede ser nulo o vacío.");

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Concurso concurso = (Concurso) o;
        return Objects.equals(nombre, concurso.nombre) && Objects.equals(fechaInicioInscripcion, concurso.fechaInicioInscripcion) && Objects.equals(fechaFinInscripcion, concurso.fechaFinInscripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, fechaInicioInscripcion, fechaFinInscripcion);
    }
}
