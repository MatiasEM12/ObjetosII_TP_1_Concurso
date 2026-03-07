

import java.time.LocalDate;
import java.util.ArrayList;


public class Concurso {

    private String nombre;
    private ArrayList<Inscripcion> inscriptos;
    private LocalDate fechaInicioInscripcion;
    private LocalDate fechaFinInscripcion;



    public Concurso(String nombre, LocalDate fechaInicioInscripcion, LocalDate fechaFinInscripcion) {
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

    public void agregarInscripcion(Inscripcion inscripcion){

        if (!validarInscripcion(inscripcion)){
            //System.out.println("La inscripción no es válida o ya existe.");
             throw new IllegalArgumentException("La inscripción no es válida o ya existe.");
        }


        if(validarPeridoInscripcion(inscripcion)){
            this.inscriptos.add(inscripcion);
        }else{
            //System.out.println("La inscripción no se encuentra dentro del período permitido.");
             throw new IllegalArgumentException("La inscripción no se encuentra dentro del período permitido.");
        }

    }

    private Boolean validarPeridoInscripcion(Inscripcion inscripcion){

        return inscripcion.getFechaInscripcion().isAfter(this.fechaInicioInscripcion) &&
                inscripcion.getFechaInscripcion().isBefore(this.fechaFinInscripcion);

    }

    private Boolean validarInscripcion(Inscripcion inscripcion){
        return (inscripcion != null) && (!this.inscriptos.contains(inscripcion));
    }


}

