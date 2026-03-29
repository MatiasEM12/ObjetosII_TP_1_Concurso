package Entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Inscripcion {

    private static int cont=0;
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final String id;
    private Concurso concurso;
    private Participante participante;
    private LocalDate fechaInscripcion;

    public Inscripcion(Participante participante, LocalDate fechaInscripcion) {

        validarFechaInscripcion( fechaInscripcion);
        validarParticipante(participante);

        cont++;
        this.id= "I" + String.format("%05d", cont);

        this.participante = participante;
        this.fechaInscripcion = fechaInscripcion;

    }
    public Inscripcion(String id, Participante participante, LocalDate fechaInscripcion,Concurso concurso) {

        validarFechaInscripcion( fechaInscripcion);
        validarParticipante(participante);
        validarID(id);
        validarConcurso(concurso);

        this.id=id;
        this.concurso=concurso;
        this.participante = participante;
        this.fechaInscripcion = fechaInscripcion;

    }



    public void agregarPuntos(Integer puntosPrimerDia, Concurso concurso) {


        this.participante.agregarPuntos(puntosPrimerDia, concurso);

    }
    public void asignarInscripcion() {
        validarParticipante(this.participante);
        this.participante.agregarInscripcion(this);
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public Participante getParticipante() {
        return participante;
    }


    public void cargarConcurso(Concurso concurso){
        validarConcurso(concurso);
        this.concurso=concurso;
    }
    //VALIDACIONES
    private void validarParticipante(Participante participante){
        if(participante == null) throw new RuntimeException("El participante no puede ser nulo.");
    }
    private void validarFechaInscripcion(LocalDate fechaInscripcion){
        if(fechaInscripcion == null) throw new RuntimeException("La fecha de inscripción no puede ser nula.");
    }
    private void validarConcurso(Concurso concurso){
        if( concurso == null) throw new RuntimeException("El concurso no puede ser nulo.");
    }
    private void validarID(String id){
        if( id == null || id.trim().isEmpty()) throw new RuntimeException("El ID de la Inscripcion no puede ser nulo o vacío.");
    }

    public String  toStringInscripto() {
        return this.getFechaInscripcion().format(formato)
                + ", " + this.participante.getId()
                + ", " + this.concurso.getId();
    }


}