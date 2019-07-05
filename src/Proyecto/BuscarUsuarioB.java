package Proyecto;

import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class BuscarUsuarioB extends javax.swing.JFrame {

    DefaultListModel ModeloLista;
    static String UserLog;
    private File ArchivoVictorias = null;
    private File ArchivoDerrotas = null;
    private File ArchivoEmpates = null;

    public BuscarUsuarioB(String usuario) {
        initComponents();
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

    static void Mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Listar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Listado = new javax.swing.JList<>();
        Ver = new javax.swing.JButton();
        Regresar = new javax.swing.JButton();

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(Ver, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(Listar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Listar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ver, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarActionPerformed
        FileSystem fileSystem = new FileSystem();
        for (int i = 0; i < fileSystem.Usuarios().size(); i++) {
            if (!fileSystem.Usuarios().get(i).getNombreUsuario().equals(UserLog)) {
                ModeloLista.addElement(fileSystem.Usuarios().get(i).getNombreUsuario());
            }
        }
    }//GEN-LAST:event_ListarActionPerformed

    private void VerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerActionPerformed
        String usuario = Listado.getSelectedValue();
        if (Listado.isSelectionEmpty()) {
            Mensaje("Por favor seleccione un usuario");
        } else {
            MostrarDatos(usuario);
        }
    }//GEN-LAST:event_VerActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> Listado;
    private javax.swing.JButton Listar;
    private javax.swing.JButton Regresar;
    private javax.swing.JButton Ver;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
