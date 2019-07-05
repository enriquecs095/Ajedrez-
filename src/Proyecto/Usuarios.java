package Proyecto;

import java.util.Date;

public abstract class Usuarios {

    protected String NombreUsuario;
    protected String Contraseña;
    protected String Rol;
    protected String FechaIngreso;

    public Usuarios(String NombreUsuario, String Contraseña, String Rol, String FechaIngreso) {
        this.NombreUsuario = NombreUsuario;
        this.Contraseña = Contraseña;
        this.FechaIngreso = FechaIngreso;
        this.Rol = Rol;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public String getFechaIngreso() {
        return FechaIngreso;
    }

    public void setFechaIngreso(String FechaIngreso) {
        this.FechaIngreso = FechaIngreso;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }

}
