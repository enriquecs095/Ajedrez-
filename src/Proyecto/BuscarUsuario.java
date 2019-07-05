package Proyecto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class BuscarUsuario extends javax.swing.JFrame {

    DefaultListModel ModeloLista;
    static String UserLog;
    private File ArchivoVictorias = null;
    private File ArchivoDerrotas = null;
    private File ArchivoEmpates = null;

    public BuscarUsuario(String usuario) {
        initComponents();
        this.setLocationRelativeTo(null);
        ModeloLista = new DefaultListModel();
        Listado.setModel(ModeloLista);
        this.UserLog = usuario;
    }

    int LongitudVictorias(String usuario) {
        ArchivoVictorias = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + usuario + "\\Partidas\\Victorias");
        File[] CantidadFicheros = ArchivoVictorias.listFiles();
        return CantidadFicheros.length;
    }

    int LongitudDerrotas(String usuario) {
        ArchivoDerrotas = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + usuario + "\\Partidas\\Derrotas");
        File[] CantidadFicheros = ArchivoDerrotas.listFiles();
        return CantidadFicheros.length;
    }

    int LongitudEmpates(String usuario) {
        ArchivoEmpates = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + usuario + "\\Partidas\\Empates");
        File[] CantidadFicheros = ArchivoEmpates.listFiles();
        return CantidadFicheros.length;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        Listar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Listado = new javax.swing.JList<>();
        Ver = new javax.swing.JButton();
        Regresar = new javax.swing.JButton();
        Borrar = new javax.swing.JButton();
        Rol = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Listar.setText("Listar Usuarios");
        Listar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListarActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(Listado);

        Ver.setText("Ver datos usuario");
        Ver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerActionPerformed(evt);
            }
        });

        Regresar.setText("Regresar");
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });

        Borrar.setText("Borrar usuario");
        Borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrarActionPerformed(evt);
            }
        });

        Rol.setText("Modificar Rol");
        Rol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RolActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(Borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(Ver, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Rol, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(Listar)))
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(Listar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ver, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Rol, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void MostrarDatos(String usuario) {
        FileSystem fileSystem = new FileSystem();
        for (int i = 0; i < fileSystem.Usuarios().size(); i++) {
            if (fileSystem.Usuarios().get(i).getNombreUsuario().equals(usuario)) {
                Mensaje("El usuario: " + usuario + " \nIngreso el: " + FileSystem.Usuarios().get(i).getFechaIngreso() + " \nEn el rol de: "
                        + FileSystem.Usuarios().get(i).getRol() + "\n" + LongitudVictorias(usuario) + " victorias " + LongitudEmpates(usuario)
                        + " empates " + LongitudDerrotas(usuario) + " derrotas");
            }
        }
    }

    void ModificarRol(String usuario) throws FileNotFoundException {
        FileSystem fileSystem = new FileSystem();
        String NuevoRol = JOptionPane.showInputDialog("Ingrese el nuevo rol \n 1. Administrador \n 2. Analista \n 3. Jugador");
        if (NuevoRol.equals("1")) {
            if (fileSystem.ModificarRol(usuario, "Administrador")) {
                Mensaje("Se modifico el rol");
            }
        } else if (NuevoRol.equals("2")) {
            if (fileSystem.ModificarRol(usuario, "Analista")) {
                Mensaje("Se modifico el rol");
            }
        } else if (NuevoRol.equals("3")) {
            if (fileSystem.ModificarRol(usuario, "Jugador")) {
                Mensaje("Se modifico el rol");
            }
        } else {
            Mensaje("Ingrese el rol correcto");
        }
    }

    void EliminarUsuario(String usuario) {
        FileSystem fileSystem = new FileSystem();
        if (fileSystem.EliminarUsuario(usuario)) {
            Mensaje("Usuario ya no existe");
        }
    }


    private void ListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarActionPerformed
        FileSystem fileSystem = new FileSystem();
        for (int i = 0; i < fileSystem.Usuarios().size(); i++) {
            if (!fileSystem.Usuarios().get(i).getNombreUsuario().equals(UserLog)) {
                ModeloLista.addElement(fileSystem.Usuarios().get(i).getNombreUsuario());
            }
        }
    }//GEN-LAST:event_ListarActionPerformed

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
        MenuAdministrador menuAdministrador = new MenuAdministrador(UserLog);
        MenuJugador menuJugador = new MenuJugador(UserLog);
        MenuAnalista menuAnalista = new MenuAnalista(UserLog);
        FileSystem fileSystem = new FileSystem();
        switch (fileSystem.BuscarTipoUsuarioB(UserLog)) {
            case 1:
                menuAdministrador.setVisible(true);
                this.dispose();
                break;
            case 2:
                menuAnalista.setVisible(true);
                this.dispose();
                break;
            default:
                menuJugador.setVisible(true);
                this.dispose();
                break;
        }
    }//GEN-LAST:event_RegresarActionPerformed

    private void VerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerActionPerformed
        String usuario = Listado.getSelectedValue();
        if (Listado.isSelectionEmpty()) {
            Mensaje("Por favor seleccione un usuario");
        } else {
            MostrarDatos(usuario);
        }

    }//GEN-LAST:event_VerActionPerformed

    private void BorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarActionPerformed
        String usuario = Listado.getSelectedValue();
        if (Listado.isSelectionEmpty()) {
            Mensaje("Por favor seleccione un usuario");
        } else {
            EliminarUsuario(usuario);
        }

    }//GEN-LAST:event_BorrarActionPerformed

    private void RolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RolActionPerformed
        String usuario = Listado.getSelectedValue();
        try {
            if (Listado.isSelectionEmpty()) {
                Mensaje("Por favor seleccione un usuario");
            } else {
                ModificarRol(usuario);
            }
        } catch (IOException e) {
            Mensaje(e.getMessage());
        }
    }//GEN-LAST:event_RolActionPerformed

    static void Mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Borrar;
    private javax.swing.JList<String> Listado;
    private javax.swing.JButton Listar;
    private javax.swing.JButton Regresar;
    private javax.swing.JButton Rol;
    private javax.swing.JButton Ver;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
