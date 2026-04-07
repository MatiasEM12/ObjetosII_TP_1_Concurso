package org;

import Entities.*;

import backend.InscripcionDAOJDBC;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

import java.time.LocalDate;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        var inscriptosDao= new InscripcionDAOJDBC();

        try{
           inscriptosDao.truncateTabla();
        }catch (RuntimeException e){
            System.out.println("Error al limpiar tabla: " + e.getMessage()) ;

        }



        // provide account credentials
        final String username = "7745afec1e364b";
        final String password = "58c1826c0780a7";

        String origen = "test@mailtrap.io";

        // provide host address
        String host = "sandbox.smtp.mailtrap.io";

        // configure SMTP details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // create the mail Session object
        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });




        // ========== CREAR CONCURSO ==========
        System.out.println("=== SISTEMA DE GESTIÓN DE CONCURSOS ===\n");

        var concurso = new Concurso("Concurso de Mecanica", LocalDate.now().minusWeeks(2),
                LocalDate.now().plusWeeks(1),inscriptosDao);

        System.out.println("Concurso creado: " + concurso.toString());

        // ========== CREAR PARTICIPANTES ==========
        System.out.println("=== REGISTRANDO PARTICIPANTES ===\n");

        var participante = new Participante("Jose Maria", "12345888"," test@mailtrap.io");

        System.out.println("Participante creado: " + participante.toString());


        // ========== INSCRIBIR PARTICIPANTES EN CONCURSO ==========
        System.out.println("=== INSCRIBIENDO PARTICIPANTES ===\n");

        NotificacionEmail notificador= new NotificacionEmail(session,origen);
        var inscripcion = new Inscripcion(participante, LocalDate.now(),notificador);
        concurso.nuevaInscripcion(inscripcion);

        System.out.println("Participante inscripto: " + participante.toString());

        // ========== AGREGAR PUNTAJES ADICIONALES ==========

        System.out.println("=== ASIGNANDO PUNTAJES ===\n");

        participante.agregarPuntos(40,concurso);
        System.out.println("Puntaje adicional asignado al participante: 40" );

        // ========== GUARDAR INSCRIPCIONES EN BASE DE DATOS ==========
        System.out.println("=== PERSISTENCIA DE DATOS ===\n");

        System.out.println("Se realiza en la logica de negocio de Concurso\n");

        // ========== NOTIFICAR INSCRIPCION ==========
        System.out.println("=== NOTIFICACION AL PARTICIPANTE ===\n");

        System.out.println("Se realiza en la logica de negocio de Concurso-Inscripcion\n");

        // ========== RESUMEN FINAL ==========
        System.out.println("\n=== PROCESO FINALIZADO ===");
        System.out.println("Cantidad de mails enviados: " + notificador.cantidadDeNotificaciones());

    }
}