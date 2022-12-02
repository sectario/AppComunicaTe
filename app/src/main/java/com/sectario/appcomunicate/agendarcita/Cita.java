package com.sectario.appcomunicate.agendarcita;

import android.widget.SpinnerAdapter;

public class Cita {

    private String nombresyapellidos;
    private String tiposolicitud;
    private String fechasolicitud;
    private String horasolicitud;

    public Cita(String nombresyapellidos, String tiposolicitud, String fechasolicitud, String horasolicitud) {
        this.nombresyapellidos = nombresyapellidos;
        this.tiposolicitud = tiposolicitud;
        this.fechasolicitud = fechasolicitud;
        this.horasolicitud = horasolicitud;
    }

    public String getNombresyapellidos() {
        return nombresyapellidos;
    }

    public void setNombresyapellidos(String nombresyapellidos) {
        this.nombresyapellidos = nombresyapellidos;
    }

    public String getTiposolicitud() {
        return tiposolicitud;
    }

    public void setTiposolicitud(String tiposolicitud) {
        this.tiposolicitud = tiposolicitud;
    }

    public String getFechasolicitud() {
        return fechasolicitud;
    }

    public void setFechasolicitud(String fechasolicitud) {
        this.fechasolicitud = fechasolicitud;
    }

    public String getHorasolicitud() {
        return horasolicitud;
    }

    public void setHorasolicitud(String horasolicitud) {
        this.horasolicitud = horasolicitud;
    }
}


