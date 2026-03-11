
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
        var Concurso = new Concurso("Concurso de Programación", LocalDate.of(2025, 1, 1),
                LocalDate.of(2024, 1, 31)); // la fecha no tiene que depender del dia actual

        var Participante = new Participante("Juan Perez", "12345678");
        var Inscripcion = new Inscripcion(Participante, LocalDate.now());

        var e = assertThrows(IllegalArgumentException.class, () -> Concurso.nuevaInscripcion(Inscripcion));
        assertEquals("El participante se encuentra inscripto", e.getMessage());

    }

    @Test void inscribirParticipantePrimerDia(){

    }

    @Test void inscribirParticipanteFueraDeFecha(){

    }

}
