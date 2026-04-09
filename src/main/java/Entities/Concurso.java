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

    private GestionArchivo<Inscripcion> archivo;
    public static final Integer PUNTOS_PRIMER_DIA = 10;


    public Concurso(String nombre, LocalDate fechaInicioInscripcion, LocalDate fechaFinInscripcion,GestionArchivo<Inscripcion> archivo) {

        validarNombre(nombre);
        validarFecha(fechaInicioInscripcion);
        validarFecha(fechaFinInscripcion);
        validarFechasInscripcion(fechaInicioInscripcion,fechaFinInscripcion);
        validarArchivo(archivo);
        cont++;
        this.id= "C" + String.format("%05d", cont);

        this.archivo=archivo;
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

        if(estaInscripto(inscripcion.participante()))  throw new IllegalArgumentException("El participante se encuentra inscripto");;

        this.inscriptos.add(inscripcion);
        inscripcion.cargarConcurso(this);
        inscripcion.asignarInscripcion();

        archivo.crear(inscripcion);
        if(esInscriptoPrimerDia(inscripcion)) {
            inscripcion.agregarPuntos(PUNTOS_PRIMER_DIA,this);

        }
    }

    private Boolean esInscriptoPrimerDia(Inscripcion inscripcion){
        validarInscripcion(inscripcion);

        return inscripcion.fechaInscripcion().isEqual(this.fechaInicioInscripcion);
    }

    public boolean estaInscripto(Participante participante) {
        validarParticipante(participante);
        return  inscriptos.stream().anyMatch(i -> i.participante().equals(participante));
    }

    public String id() {
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
        validarInscripcion(inscripcion);
        if (inscripcion.fechaInscripcion().isBefore(this.fechaInicioInscripcion) ||
                inscripcion.fechaInscripcion().isAfter(this.fechaFinInscripcion)){

            throw new IllegalArgumentException("La inscripción no se encuentra dentro del período permitido.");

        };

    }

    public ArrayList<Inscripcion> inscriptos() {
        return inscriptos;
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
    private void validarArchivo(GestionArchivo<Inscripcion> archivo){
        if(archivo==null)throw new IllegalArgumentException("archivo no puede ser nulo");
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
