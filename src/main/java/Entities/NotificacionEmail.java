package Entities;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


public class NotificacionEmail implements Notificador{

    private Session session;
    private String origen;
    private int cantNotificaciones;

    public NotificacionEmail(Session session ,String origen) {
        this.session = session;
        this.origen=origen;
        this.cantNotificaciones=0;
    }

    @Override
    public void notificar(String email,String mensaje) {
        try{
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(origen));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(email));

            message.setSubject("Notificacion");
            message.setText(mensaje);
            jakarta.mail.Transport.send(message);

            this.cantNotificaciones++;
        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al envair elcorre"+e);
        }
    }

    @Override
    public String obtenerUltimoMail() {
        throw new RuntimeException("Obtener Ultimo mail no implementado");
    }

    @Override
    public int cantidadDeNotificaciones() {
        return this.cantNotificaciones;
    }
}
