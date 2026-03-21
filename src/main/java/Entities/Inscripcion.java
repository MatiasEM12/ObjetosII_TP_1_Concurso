package Entities;

import java.time.LocalDate;

public class Inscripcion {

    private Participante participante;
    private LocalDate fechaInscripcion;

    public Inscripcion(Participante participante, LocalDate fechaInscripcion) {

        validarFechaInscripcion( fechaInscripcion);
        validarParticipante(participante);

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



    //VALIDACIONES
    private void validarParticipante(Participante participante){
        if(participante == null) throw new RuntimeException("El participante no puede ser nulo.");
    }

    private void validarFechaInscripcion(LocalDate fechaInscripcion){
        if(fechaInscripcion == null) throw new RuntimeException("La fecha de inscripción no puede ser nula.");
    }



}
