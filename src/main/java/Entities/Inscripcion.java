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

    private Notificador notificacion;

    public Inscripcion(Participante participante, LocalDate fechaInscripcion,Notificador notificacion) {

        validarFechaInscripcion( fechaInscripcion);
        validarParticipante(participante);

        cont++;
        this.id= "I" + String.format("%05d", cont);

        this.notificacion=notificacion;
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

    public LocalDate fechaInscripcion() {
        return fechaInscripcion;
    }

    public Participante participante() {
        return participante;
    }


    public void cargarConcurso(Concurso concurso){
        validarConcurso(concurso);
        this.concurso=concurso;
    }

    public String id() {
        return id;
    }

    public String concursoId(){
        return this.concurso.id();
    }

    public String participanteId(){
        return this.participante.id();
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
        return this.fechaInscripcion().format(formato)
                + ", " + this.participante.id()
                + ", " + this.concurso.id();
    }


    public void notificarInscripcion(String mensaje) {

        notificacion.notificar(participante.email(),mensaje);
    }
}