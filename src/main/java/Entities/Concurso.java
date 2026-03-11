package Entities;

import java.time.LocalDate;
import java.util.ArrayList;


public class Concurso {

    private String nombre;
    private ArrayList<Inscripcion> inscriptos;
    private LocalDate fechaInicioInscripcion;
    private LocalDate fechaFinInscripcion;

    private static final int PUNTOS_PRIMER_DIA = 10;


    public Concurso(String nombre, LocalDate fechaInicioInscripcion, LocalDate fechaFinInscripcion) {

        validarNombre(nombre);
        validarFecha(fechaInicioInscripcion);
        validarFecha(fechaFinInscripcion);
        validarFechasInscripcion(fechaInicioInscripcion,fechaFinInscripcion);

        this.nombre = nombre;
        this.inscriptos = new ArrayList<>();
        this.fechaInicioInscripcion = fechaInicioInscripcion;
        this.fechaFinInscripcion = fechaFinInscripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Inscripcion> getInscriptos() {
        return inscriptos;
    }

    public LocalDate getFechaInicioInscripcion() {
        return fechaInicioInscripcion;
    }

    public LocalDate getFechaFinInscripcion() {
        return fechaFinInscripcion;
    }

    public void nuevaInscripcion(Inscripcion inscripcion) {

        validarInscripcion(inscripcion);
        validarPeridoInscripcion(inscripcion);

        if(participanteInscripto(inscripcion.getParticipante()))   throw new IllegalArgumentException("El participante se encuentra inscripto");;

        this.inscriptos.add(inscripcion);

        if(esInscriptoPrimerDia(inscripcion)) inscripcion.getParticipante().agregarPuntos(PUNTOS_PRIMER_DIA);
    }

    private Boolean esInscriptoPrimerDia(Inscripcion inscripcion){
        return inscripcion.getFechaInscripcion().isEqual(this.fechaInicioInscripcion);
    }

    public boolean participanteInscripto(Participante participante) {
        return this.inscriptos.stream().anyMatch(inscripcion -> inscripcion.getParticipante().equals(participante));
    }


    //VALIDACIONES
    private void validarNombre(String nombre){
        if (nombre == null || nombre.trim().isEmpty()){

            //System.out.println("El nombre del concurso no puede ser nulo o vacío.");
            throw new IllegalArgumentException("El nombre del concurso no puede ser nulo o vacío.");

        };
    }
    private void validarFecha(LocalDate fecha){
        if (fecha == null){

            //System.out.println("La fecha no puede ser nula.");
            throw new IllegalArgumentException("La fecha no puede ser nula.");

        };
    }
    private void validarFechasInscripcion(LocalDate fechaInicio, LocalDate fechaFin){

        if ( !fechaInicio.isBefore(fechaFin) ){

            //System.out.println("La fecha de inicio de inscripción debe ser anterior a la fecha de fin de inscripción.");
            throw new IllegalArgumentException("La fecha de inicio de inscripción debe ser anterior a la fecha de fin de inscripción.");

        };

    }
    private void validarPeridoInscripcion(Inscripcion inscripcion){

        if ( !inscripcion.getFechaInscripcion().isAfter(this.fechaInicioInscripcion) &&
                !inscripcion.getFechaInscripcion().isBefore(this.fechaFinInscripcion)){

            //System.out.println("La inscripción no se encuentra dentro del período permitido.");
            throw new IllegalArgumentException("La inscripción no se encuentra dentro del período permitido.");

        };

    }
    private void validarInscripcion(Inscripcion inscripcion){
        if( (inscripcion != null) && (!this.inscriptos.contains(inscripcion)) ){
            //System.out.println("La inscripción no es válida o ya existe.");
            throw new IllegalArgumentException("La inscripción no es válida o ya existe.");

        };
    }


}
