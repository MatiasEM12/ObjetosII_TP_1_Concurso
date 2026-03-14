import Entities.Concurso;
import Entities.Inscripcion;
import Entities.Participante;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class concursoTest {

    @Test
    public void inscribirParticipante(){
        //setup
        var concurso = new Concurso("Concurso de Programación", LocalDate.now().minusWeeks(1),
                LocalDate.now().plusWeeks(1)); //

        var participante = new Participante("Juan Perez", "12345678");
        var inscripcion = new Inscripcion(participante, LocalDate.now());

        concurso.nuevaInscripcion(inscripcion);

        var e = assertThrows(IllegalArgumentException.class, () -> concurso.nuevaInscripcion(inscripcion));
        assertEquals("El participante se encuentra inscripto", e.getMessage());

    }

    @Test void inscribirParticipantePrimerDia(){
        var concurso = new Concurso("Concurso de Matematica", LocalDate.now(),
                LocalDate.now().plusWeeks(1)); //

        var participante = new Participante("Jhon Doe", "12345677");
        var inscripcion = new Inscripcion(participante, LocalDate.now());


       concurso.nuevaInscripcion(inscripcion);
       var puntos = participante.obtenerPuntaje(concurso);

       assertEquals(Concurso.PUNTOS_PRIMER_DIA, puntos);
    }

    @Test void inscribirParticipanteFueraDeFecha(){

        var concurso = new Concurso("Concurso de Lengua", LocalDate.now().minusWeeks(2),
                LocalDate.now().minusWeeks(1)); //

        var participante = new Participante("Maria Marta", "12345671");
        var inscripcion = new Inscripcion(participante, LocalDate.now());

        var e = assertThrows(IllegalArgumentException.class, () -> concurso.nuevaInscripcion(inscripcion));
        assertEquals("La inscripción no se encuentra dentro del período permitido.", e.getMessage());
    }

}
