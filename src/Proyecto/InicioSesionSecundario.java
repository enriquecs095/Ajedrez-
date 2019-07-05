package Proyecto;

import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

public class InicioSesionSecundario extends javax.swing.JFrame{

    static String UserLog;
    static String UserSecondary;

    public InicioSesionSecundario(String usuario) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.UserLog = usuario;
    }

    void Ingresar() throws FileNotFoundException, UserNotFoundException {
        UserSecondary = Usuario.getText();
        FileSystem fileSystem = new FileSystem();
        Tablero tablero = new Tablero();
        VistaTablero juego = new VistaTablero(tablero, UserLog, UserSecondary);       
        String contraseña = Contraseña.getText();
        if (Usuario.getText().isEmpty()) {
            Mensaje("Usuario vacio");
        }
        if (Contraseña.getText().isEmpty()) {
            Mensaje("Contraseña vacio");
        }
        if (fileSystem.BuscarUsuarioB(UserLog) == null) {
            throw new UserNotFoundException(UserLog);
        }
        if (!fileSystem.BuscarUsuarioSesionB(UserSecondary, contraseña)) {
            Mensaje("Usuario o contraseña no coinciden");
        } else { 
            juego.setVisible(true);
            this.dispose();
            
        }
    }

    void Mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Usuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Contraseña = new javax.swing.JPasswordField();
        BtnIniciarSesion = new javax.swing.JButton();
        Salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Usuario:");

        jLabel2.setText("Contraseña:");

        BtnIniciarSesion.setText("Iniciar Sesion");
        BtnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIniciarSesionActionPerformed(evt);
            }
        });

        Salir.setText("Regresar");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 29, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(BtnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(BtnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIniciarSesionActionPerformed
        try {
            Ingresar();
        } catch (UserNotFoundException e) {
            Mensaje(e.getMessage());
        } catch (FileNotFoundException ex) {
            Mensaje(ex.getMessage());
        }
    }//GEN-LAST:event_BtnIniciarSesionActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        FileSystem fileSystem = new FileSystem();
        MenuAdministrador Administrador = new MenuAdministrador(UserLog);
        MenuJugador Jugador = new MenuJugador(UserLog);
        MenuAnalista Analista = new MenuAnalista(UserLog);
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
    }//GEN-LAST:event_SalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnIniciarSesion;
    private javax.swing.JPasswordField Contraseña;
    private javax.swing.JButton Salir;
    private javax.swing.JTextField Usuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

 
}
