import Entities.Notificador;

import java.util.ArrayList;

public class FakeNotificacionEmail implements Notificador {

    ArrayList<String>notificaciones;
    int cantNotificaciones;

    public FakeNotificacionEmail(){
        this.notificaciones=new ArrayList<>();
        this.cantNotificaciones=0;
    }
    @Override
    public void notificar(String email,String mensaje) {
        notificaciones.add(email+mensaje);
        this.cantNotificaciones++;
    }

    @Override
    public String obtenerUltimoMail() {
        return this.notificaciones.getLast();
    }

    @Override
    public int cantidadDeNotificaciones() {
        return this.cantNotificaciones;
    }
}
