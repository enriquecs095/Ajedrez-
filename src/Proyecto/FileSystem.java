package Proyecto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class FileSystem implements Encriptacion {

    static ArrayList<Usuarios> RegistroUsuarios;
    private String NewUser;
    private File CarpetaUsuario, CarpetaPartidas, CarpetaVictorias, CarpetaEmpates, CarpetaDerrotas;
    private File Usuarios = null;
    private File Datos = null;

    public FileSystem() {
        RegistroUsuarios = new ArrayList<>(100);
        Datos = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\Usuarios.txt");
        try {
            EscribirArrayList();
        } catch (FileNotFoundException e) {
            Mensaje(e.getMessage());
        }
    }

    boolean CrearRegistroUsuario(String NombreUsuario) {
        try {
            CarpetaUsuario = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + NombreUsuario);
            CarpetasCreadas();
            CarpetaPartidas = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + NombreUsuario + "\\Partidas");
            CarpetasPartidas();
            //CrearUsuario("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\", NombreUsuario);
            //RegistroUsuarios();
            CarpetaVictorias = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + NombreUsuario + "\\Partidas\\Victorias");
            CrearVictorias();
            CarpetaEmpates = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + NombreUsuario + "\\Partidas\\Empates");
            CrearEmpates();
            CarpetaDerrotas = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + NombreUsuario + "\\Partidas\\Derrotas");
            CrearDerrotas();
            Datos = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\Usuarios.txt");
            ArchivoUsuarios();
            return true;
        } catch (Exception e) {
            Mensaje(e.getMessage());
            return false;
        }
    }

    void CrearUsuario(String url, String NombreUsuario) {
        Usuarios = new File(url + NombreUsuario + "\\" + NombreUsuario + ".txt");
    }

    void ArchivoUsuarios() throws IOException {
        Datos.createNewFile();
    }

    void RegistroUsuarios() throws IOException {
        Usuarios.createNewFile();
    }

    void CarpetasCreadas() {
        CarpetaUsuario.mkdirs();
    }

    void CarpetasPartidas() {
        CarpetaPartidas.mkdirs();
    }

    void CrearVictorias() {
        CarpetaVictorias.mkdirs();
    }

    void CrearEmpates() {
        CarpetaEmpates.mkdirs();
    }

    void CrearDerrotas() {
        CarpetaDerrotas.mkdirs();
    }

    void EscribirArrayList() throws FileNotFoundException {
        String usuarioB;
        String contraseñaB;
        String rolB;
        String fecha;
        Scanner leer = new Scanner(Datos);
        while (leer.hasNext()) {
            usuarioB = leer.next();
            contraseñaB = leer.next();
            rolB = leer.next();
            fecha = leer.next();
            Roles roles = Roles.valueOf(rolB.toUpperCase());
            switch (roles) {
                case JUGADOR:
                    RegistroUsuarios.add(new Jugador(usuarioB, Desencriptar(contraseñaB), rolB, fecha));
                    break;
                case ANALISTA:
                    RegistroUsuarios.add(new Analista(usuarioB, Desencriptar(contraseñaB), rolB, fecha));
                    break;
                case ADMINISTRADOR:
                    RegistroUsuarios.add(new Administrador(usuarioB, Desencriptar(contraseñaB), rolB, fecha));
                    break;
            }
        }
    }

    
    static ArrayList<Usuarios> Usuarios() {
        return RegistroUsuarios;
    }

    Usuarios BuscarUsuarioB(String usuario) throws FileNotFoundException {
        for (Usuarios usuarios : RegistroUsuarios) {
            if (usuarios.getNombreUsuario().equals(usuario)) {
                return usuarios;
            }
        }
        return null;
    }

    boolean ModificarContraseña(String usuario, String contraseña) throws FileNotFoundException {
        if (BuscarUsuarioB(usuario) != null) {
            for (int i = 0; i < RegistroUsuarios.size(); i++) {
                if (RegistroUsuarios.get(i).getNombreUsuario().equals(usuario)) {
                    RegistroUsuarios.get(i).setContraseña(contraseña);
                    ActualizarArchivo();
                    return true;
                }
            }
        }
        return false;
    }

    boolean ModificarRol(String usuario, String Rol) throws FileNotFoundException {
        if (BuscarUsuarioB(usuario) != null) {
            for (int i = 0; i < RegistroUsuarios.size(); i++) {
                if (RegistroUsuarios.get(i).getNombreUsuario().equals(usuario)) {
                    RegistroUsuarios.get(i).setRol(Rol);
                    ActualizarArchivo();
                    return true;
                }
            }
        }
        return false;
    }

    boolean BuscarUsuarioSesionB(String usuario, String contraseña) {
        for (int i = 0; i < RegistroUsuarios.size(); i++) {
            if (RegistroUsuarios.get(i).getNombreUsuario().equals(usuario)) {
                if (RegistroUsuarios.get(i).getContraseña().equals(contraseña)) {
                    return true;
                }
            }
        }
        return false;
    }

    int BuscarTipoUsuarioB(String usuario) {
        for (int i = 0; i < RegistroUsuarios.size(); i++) {
            if (RegistroUsuarios.get(i).getNombreUsuario().equals(usuario)) {
                switch (RegistroUsuarios.get(i).getRol()) {
                    case "Administrador":
                        return 1;
                    case "Analista":
                        return 2;
                    default:
                        return 3;
                }
            }
        }
        return 0;
    }

//eliminar
    /*
    void AñadirUsuario(String usuario, String contraseña, String rol) throws IOException {
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        mes = mes + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        FileWriter fw = new FileWriter(Usuarios);
        fw.write(usuario + " ");
        fw.write(contraseña + " ");
        fw.write(rol + " ");
        fw.write(dia + "/" + mes + "/" + año + " ");
        fw.flush();
        Mensaje("Se añadio el usuario");
    }//Eliminar*/
    void AñadirArchivoUsuarios(String usuario, String contraseña, String rol) throws IOException {
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        mes = mes + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        FileWriter fw = new FileWriter(Datos, true);
        fw.write(usuario + " ");
        fw.write(contraseña + " ");
        fw.write(rol + " ");
        fw.write(dia + "/" + mes + "/" + año + " ");
        Mensaje("Se añadio el usuario");
        fw.flush();
    }

    void ActualizarArchivo() {
        try {
            FileWriter fw = new FileWriter(Datos, false);
            int contar = 1;
            while (contar <= RegistroUsuarios.size()) {
                for (int i = 0; i < RegistroUsuarios.size(); i++) {
                    fw.write(RegistroUsuarios.get(i).getNombreUsuario() + " ");
                    fw.write(Encriptar(RegistroUsuarios.get(i).getContraseña()) + " ");
                    fw.write(RegistroUsuarios.get(i).getRol() + " ");
                    fw.write(RegistroUsuarios.get(i).getFechaIngreso() + " ");
                    fw.flush();
                    contar++;
                }
            }
        } catch (IOException e) {
            Mensaje(e.getMessage());
        }
    }
    

    boolean EliminarUsuario(String usuario) {
        for (int i = 0; i < RegistroUsuarios.size(); i++) {
            if (RegistroUsuarios.get(i).getNombreUsuario().equals(usuario)) {
                RegistroUsuarios.get(i).setNombreUsuario("");
                RegistroUsuarios.get(i).setContraseña("");
                RegistroUsuarios.get(i).setRol("");
                RegistroUsuarios.get(i).setFechaIngreso("");
                EliminarCarpeta(usuario);
                ActualizarArchivo();
                return true;
            }
        }
        return false;
    }

    void EliminarCarpeta(String usuario) {
        File f;
        CarpetaUsuario = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + usuario);
        CarpetaPartidas = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + usuario + "\\Partidas");
        CarpetaVictorias = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + usuario + "\\Partidas\\Victorias");
        CarpetaEmpates = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + usuario + "\\Partidas\\Empates");
        CarpetaDerrotas = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + usuario + "\\Partidas\\Derrotas");
        if (CarpetaVictorias.isDirectory()) {
            String[] archivos = CarpetaVictorias.list();
            if (archivos.length != 0) {
                for (String archivo : archivos) {
                    f = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + usuario + "\\Partidas\\Victorias");
                    f.delete();
                }
            }
        }
        if (CarpetaEmpates.isDirectory()) {
            String[] archivos = CarpetaEmpates.list();
            if (archivos.length != 0) {
                for (String archivo : archivos) {
                    f = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + usuario + "\\Partidas\\Empates");
                    f.delete();
                }
            }
        }
        if (CarpetaDerrotas.isDirectory()) {
            String[] archivos = CarpetaDerrotas.list();
            if (archivos.length != 0) {
                for (String archivo : archivos) {
                    f = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + usuario + "\\Partidas\\Derrotas");
                    f.delete();
                }
            }
        }
        if (CarpetaVictorias.delete()) {
            if (CarpetaEmpates.delete()) {
                if (CarpetaDerrotas.delete()) {
                    if (CarpetaPartidas.delete()) {
                        if (CarpetaUsuario.delete()) {
                            Mensaje("Eliminados los registros");
                        }
                    }
                }
            }
        }
    }

    void Mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);

    }

   
    public String Encriptar(String contraseña) {
       char letra;
        int valorASCII;
        String contraseña2="";
        char c;
        for (int i = 0; i < contraseña.length(); i++) {
            letra = contraseña.charAt(i);
            valorASCII = ((int) letra);
            valorASCII++;
            c=(char)valorASCII;
            contraseña2 += c;
        }
        return contraseña2;
    }

    
    public String Desencriptar(String contraseña) {
       char letra;
        int valorASCII;
        String contraseña2="";
        char c;
        for (int i = 0; i < contraseña.length(); i++) {
            letra = contraseña.charAt(i);
            valorASCII = ((int) letra);
            valorASCII--;
            c=(char)valorASCII;
            contraseña2 += c;
        }
        return contraseña2;
    }

    @Override
    public String Desencriptar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String Encriptar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
