package org;

import Entities.*;

import backend.InscripcionDAOJDBC;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        var inscriptosDao= new InscripcionDAOJDBC();

        List<String> inscripciones = new ArrayList<>();

        try {
            inscriptosDao.truncateTabla();

        } catch (RuntimeException e) {
            System.out.println("Error al limpiar tabla: " + e.getMessage()) ;
        }
        // ========== CREAR CONCURSO ==========
        System.out.println("=== SISTEMA DE GESTIÓN DE CONCURSOS ===\n");

        LocalDate fechaInicio = LocalDate.now().minusWeeks(1);
        LocalDate fechaFin = LocalDate.now().plusWeeks(2);

        Concurso concurso = new Concurso("Concurso de Objetos II", fechaInicio, fechaFin,inscriptosDao);

        System.out.println("Concurso creado: " + concurso.getId());
        System.out.println("Fecha de inscripción: " + fechaInicio + " a " + fechaFin + "\n");


        // ========== CREAR PARTICIPANTES ==========
        System.out.println("=== REGISTRANDO PARTICIPANTES ===\n");

        ArrayList<Participante> participantes = new ArrayList<>();

        participantes.add(new Participante("Juan Pérez", "12345678"));
        participantes.add(new Participante("María García", "87654321"));
        participantes.add(new Participante("Carlos López", "11111111"));
        participantes.add(new Participante("Ana Martínez", "22222222"));

        for (Participante p : participantes) {
            System.out.println("Participante registrado: " + p.getId());
        }
        System.out.println();


        // ========== INSCRIBIR PARTICIPANTES EN CONCURSO ==========
        System.out.println("=== INSCRIBIENDO PARTICIPANTES ===\n");

        // Inscripción en primer día (obtiene puntos)
        Inscripcion inscripcion1 = new Inscripcion(participantes.get(0), fechaInicio);
        try {
            concurso.nuevaInscripcion(inscripcion1);
            System.out.println(  participantes.get(0).getId() + " inscripto el primer día");
            System.out.println("  Bonificación: 10 puntos otorgados");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
        System.out.println();

        // Inscripciones normales
        for (int i = 1; i < participantes.size(); i++) {
            Inscripcion inscripcion = new Inscripcion(participantes.get(i), LocalDate.now());
            try {
                concurso.nuevaInscripcion(inscripcion);
                System.out.println( participantes.get(i).getId() + " inscripto exitosamente");
            } catch (Exception e) {
                System.out.println("✗ Error en inscripción: " + e.getMessage());
            }
        }
        System.out.println();


        // ========== AGREGAR PUNTAJES ADICIONALES ==========
        System.out.println("=== ASIGNANDO PUNTAJES ===\n");

        participantes.get(0).agregarPuntos(50, concurso);
        System.out.println( participantes.get(0).getId() + ": +50 puntos (Total: 60)");

        participantes.get(1).agregarPuntos(35, concurso);
        System.out.println( participantes.get(1).getId() + ": +35 puntos");

        participantes.get(2).agregarPuntos(45, concurso);
        System.out.println(participantes.get(2).getId() + ": +45 puntos");

        participantes.get(3).agregarPuntos(40, concurso);
        System.out.println( participantes.get(3).getId() + ": +40 puntos");
        System.out.println();


        // ========== GUARDAR INSCRIPCIONES EN ARCHIVO ==========
        System.out.println("=== PERSISTENCIA DE DATOS ===\n");




        System.out.println("=== LISTADO DE INSCRIPTOS DESDE EL ARCHIVO ===\n");

        inscripciones=inscriptosDao.findAllInscriptos();

        if (inscripciones.isEmpty()) {
            System.out.println("No hay inscripciones registradas");
        } else {
            for(String s : inscripciones){
                System.out.println(s);
            }
        }
        System.out.println();


        // ========== RESUMEN FINAL ==========
        System.out.println("=== RESUMEN FINAL ===\n");
        System.out.println("Concurso: " + concurso.getId());
        System.out.println("Participantes registrados: " + participantes.size());
        System.out.println("Fecha límite de inscripción: " + fechaFin);

    }
}