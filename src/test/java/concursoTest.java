import Entities.Concurso;
import Entities.Inscripcion;
import Entities.Participante;
import Persistence.ArchivoInscriptos;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class concursoTest {

    @Test
    public void inscribirParticipante(){

        var fakeArchivoInscriptos= new FakeArchivoInscriptos("fakeArchivoInscriptos.txt");
        var concurso = new Concurso("Concurso de Programación", LocalDate.now().minusWeeks(1),
                LocalDate.now().plusWeeks(1),fakeArchivoInscriptos); //

        var participante = new Participante("Juan Perez", "12345678");
        var inscripcion = new Inscripcion(participante, LocalDate.now());

        concurso.nuevaInscripcion(inscripcion);

        var e = assertThrows(IllegalArgumentException.class, () -> concurso.nuevaInscripcion(inscripcion));
        assertEquals("El participante se encuentra inscripto", e.getMessage());

    }

    @Test 
    void inscribirParticipantePrimerDia(){

        var fakeArchivoInscriptos= new FakeArchivoInscriptos("fakeArchivoInscriptos.txt");
        var concurso = new Concurso("Concurso de Matematica", LocalDate.now(),
                LocalDate.now().plusWeeks(1),fakeArchivoInscriptos); //

        var participante = new Participante("Jhon Doe", "12345677");
        var inscripcion = new Inscripcion(participante, LocalDate.now());


       concurso.nuevaInscripcion(inscripcion);
       var puntos = participante.obtenerPuntaje(concurso);

       assertEquals(Concurso.PUNTOS_PRIMER_DIA, puntos);
    }

    @Test 
    void inscribirParticipanteFueraDeFecha(){
        var fakeArchivoInscriptos= new FakeArchivoInscriptos("fakeArchivoInscriptos.txt");
        var concurso = new Concurso("Concurso de Lengua", LocalDate.now().minusWeeks(2),
                LocalDate.now().minusWeeks(1),fakeArchivoInscriptos); //

        var participante = new Participante("Maria Marta", "12345671");
        var inscripcion = new Inscripcion(participante, LocalDate.now());

        var e = assertThrows(IllegalArgumentException.class, () -> concurso.nuevaInscripcion(inscripcion));
        assertEquals("La inscripción no se encuentra dentro del período permitido.", e.getMessage());
    }
    
    
    @Test
    void puntaje(){
        var fakeArchivoInscriptos= new FakeArchivoInscriptos("fakeArchivoInscriptos.txt");
        var concurso = new Concurso("Concurso de Ingles", LocalDate.now().minusWeeks(1),
                LocalDate.now().plusWeeks(1),fakeArchivoInscriptos); //

        var participante = new Participante("Pepe Cruz", "12345555");
        var inscripcion = new Inscripcion(participante, LocalDate.now());

        concurso.nuevaInscripcion(inscripcion);

        participante.agregarPuntos(20, concurso);
        var e = assertThrows(IllegalArgumentException.class, () -> participante.agregarPuntos(-10,concurso));
        assertEquals("Los puntos no pueden ser negativos o null.", e.getMessage());

        

    }
    
    
    @Test 
    void archivoInscriptos(){
        var fakeArchivoInscriptos= new FakeArchivoInscriptos("fakeArchivoInscriptos.txt");
        var concurso = new Concurso("Concurso de Programación", LocalDate.now().minusWeeks(1),
                LocalDate.now().plusWeeks(1),fakeArchivoInscriptos);
        var participante = new Participante("Juan Perez", "12777777");
        var inscripcion = new Inscripcion(participante, LocalDate.now());
        concurso.nuevaInscripcion(inscripcion);

      ArrayList<String> inscriptos = fakeArchivoInscriptos.listar();
      assertTrue(inscriptos.contains(inscripcion.toStringInscripto()));


    }
    

}
