import java.time.LocalDate;

public class Inscripcion {

    private Participante participante;
    private LocalDate fechaInscripcion;

    public Inscripcion(Participante participante, LocalDate fechaInscripcion) {
        this.participante = participante;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Participante getParticipante() {
        return participante;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }
}
