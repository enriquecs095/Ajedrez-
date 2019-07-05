package Proyecto;

import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

public class CambiarDatos extends javax.swing.JFrame {

    static String UserLog;

    public CambiarDatos(String usuario) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.UserLog = usuario;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Contraseña = new javax.swing.JPasswordField();
        Cambiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Ingrese la nueva contraseña: ");

        Cambiar.setText("Cambiar");
        Cambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CambiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(Cambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Cambiar, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CambiarActionPerformed
        FileSystem fileSystem = new FileSystem();
        MenuAdministrador Administrador = new MenuAdministrador(UserLog);
        MenuJugador Jugador = new MenuJugador(UserLog);
        MenuAnalista Analista = new MenuAnalista(UserLog);
        String contraseña = Contraseña.getText();
        try {
            if (fileSystem.ModificarContraseña(UserLog, contraseña)) {
                Mensaje("Cambio realizado");
                switch (fileSystem.BuscarTipoUsuarioB(UserLog)) {
                    case 1:
                        Administrador.setVisible(true);
                        this.dispose();
                        break;
                    case 2:
                        Analista.setVisible(true);
                        this.dispose();
                        break;
                    default:
                        Jugador.setVisible(true);
                        this.dispose();
                        break;
                }
            }
        } catch (FileNotFoundException ex) {
            Mensaje(ex.getMessage());
        }
    }//GEN-LAST:event_CambiarActionPerformed

    void Mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cambiar;
    private javax.swing.JPasswordField Contraseña;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
