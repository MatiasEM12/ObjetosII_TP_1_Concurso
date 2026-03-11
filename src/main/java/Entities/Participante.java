package Entities;

import java.util.Objects;

public class Participante {

    private String nombre;
    private String dni;
    private int puntos;

    public Participante( String nombre, String dni) {

        validarNombre(nombre);
        validarDni(dni);

        this.nombre = nombre;
        this.dni = dni;
        this.puntos = 0;
    }

    public Participante (String nombre, String dni, int puntos) {

        validarNombre(nombre);
        validarDni(dni);
        validarPuntos(puntos);

        this.nombre = nombre;
        this.dni = dni;
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public int getPuntos() {
        return puntos;
    }

    public void agregarPuntos(int puntos) {
        validarPuntos(puntos);
        this.puntos += puntos;
    }

    //VALIDACIONES
    private void validarNombre(String nombre){
        if(nombre == null || nombre.isEmpty()) throw new RuntimeException("El nombre del participante no puede ser nulo o vacío.");
    }

    private void validarDni(String dni){
        if(dni == null || dni.isEmpty()) throw new RuntimeException("El DNI del participante no puede ser nulo o vacío.");
    }

    private void validarPuntos(Integer puntos){
        if(puntos == null )throw new RuntimeException("Los puntos no pueden ser null.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participante that)) return false;
        return Objects.equals(dni, that.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}
