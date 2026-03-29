package Entities;

public interface Notificador {


    public void notificar(String email, String mensaje);

    public String obtenerUltimoMail();

    public int cantidadDeNotificaciones();
}