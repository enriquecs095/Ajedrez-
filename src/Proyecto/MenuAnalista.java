package Proyecto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class MenuAnalista extends javax.swing.JFrame {

    static String UserLog;
    File Datos = null;
    private File ArchivoVictorias = null;
    private File ArchivoDerrotas = null;
    private File ArchivoEmpates = null;

    public MenuAnalista(String usuario) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.UserLog = usuario;
        try {
            Datos = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\Usuarios.txt");
            CargarDatos();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    void CargarDatos() throws FileNotFoundException {
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
            if (usuarioB.equals(UserLog)) {
                Usuario.setText(UserLog);
                Fecha.setText(fecha);
                Victorias.setText(Integer.toString(LongitudVictorias(UserLog)));
                Derrotas.setText(Integer.toString(LongitudDerrotas(UserLog)));
                Empates.setText(Integer.toString(LongitudEmpates(UserLog)));
            }
        }
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

    void Mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Usuario = new javax.swing.JTextField();
        Fecha = new javax.swing.JTextField();
        Victorias = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Empates = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Derrotas = new javax.swing.JTextField();
        CambiarContraseña = new javax.swing.JButton();
        Salir = new javax.swing.JButton();
        Jugar = new javax.swing.JButton();
        Ranking = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Borrar1 = new javax.swing.JButton();
        PerfilUsuarios = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Victorias");

        Usuario.setEnabled(false);

        Fecha.setEnabled(false);

        Victorias.setEnabled(false);

        jLabel3.setText("Empates:");

        jLabel1.setText("Usuario:");

        Empates.setEnabled(false);

        jLabel4.setText("Fecha de ingreso:");

        jLabel5.setText("Derrotas:");

        Derrotas.setEnabled(false);

        CambiarContraseña.setText("Cambiar Contraseña");
        CambiarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CambiarContraseñaActionPerformed(evt);
            }
        });

        Salir.setText("Cerrar Sesion");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        Jugar.setText("Jugar");
        Jugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JugarActionPerformed(evt);
            }
        });

        Ranking.setText("Ranking");
        Ranking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RankingActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Analista");

        Borrar1.setText("Borrar mi cuenta");
        Borrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Borrar1ActionPerformed(evt);
            }
        });

        PerfilUsuarios.setText("Buscar Usuarios");
        PerfilUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PerfilUsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Fecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(Usuario, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Derrotas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                    .addComponent(Victorias, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(Empates, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CambiarContraseña, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Borrar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Ranking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Jugar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PerfilUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(Salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Victorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(Empates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(Derrotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CambiarContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Jugar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Borrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PerfilUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ranking, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JugarActionPerformed
        InicioSesionSecundario secundario = new InicioSesionSecundario(UserLog);
        secundario.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_JugarActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        InicioSesion inicioSesion = new InicioSesion();
        inicioSesion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_SalirActionPerformed

    void Estadisticas(){
    
    }
    
    
    private void RankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RankingActionPerformed
      Estadisticas();
        
        
        
    }//GEN-LAST:event_RankingActionPerformed

    private void Borrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Borrar1ActionPerformed
        FileSystem fileSystem = new FileSystem();
        InicioSesion sesion = new InicioSesion();
        if (fileSystem.EliminarUsuario(UserLog)) {
            Mensaje("Usuario ya no existe");
            sesion.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_Borrar1ActionPerformed

    private void PerfilUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PerfilUsuariosActionPerformed
        BuscarUsuarioB buscarUsuario = new BuscarUsuarioB(UserLog);
        buscarUsuario.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_PerfilUsuariosActionPerformed

    private void CambiarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CambiarContraseñaActionPerformed
        CambiarDatos cambiarDatos = new CambiarDatos(UserLog);
        cambiarDatos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_CambiarContraseñaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Borrar1;
    private javax.swing.JButton CambiarContraseña;
    private javax.swing.JTextField Derrotas;
    private javax.swing.JTextField Empates;
    private javax.swing.JTextField Fecha;
    private javax.swing.JButton Jugar;
    private javax.swing.JButton PerfilUsuarios;
    private javax.swing.JButton Ranking;
    private javax.swing.JButton Salir;
    private javax.swing.JTextField Usuario;
    private javax.swing.JTextField Victorias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
