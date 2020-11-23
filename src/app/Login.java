package app;

import java.awt.event.KeyEvent;
import modelos.DBUsuario;
import modelos.Usuario;

/**
 * @author francisco
 */
public class Login extends javax.swing.JFrame {

    private Usuario usuario;

    private int xcord = 0;
    private int ycord = 0;

    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        btnLoginWindow = new javax.swing.JButton();
        btnCloseWindow = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 157, 347, 11));

        jLabel4.setFont(new java.awt.Font("Bitstream Vera Sans", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 99, -1, 17));

        txtUsuario.setBackground(new java.awt.Color(51, 51, 51));
        txtUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 122, 347, 29));

        jLabel3.setFont(new java.awt.Font("Bitstream Vera Sans", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Clave");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 186, 44, 27));

        txtClave.setBackground(new java.awt.Color(51, 51, 51));
        txtClave.setForeground(new java.awt.Color(255, 255, 255));
        txtClave.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveActionPerformed(evt);
            }
        });
        txtClave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClaveKeyPressed(evt);
            }
        });
        jPanel1.add(txtClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 218, 347, 27));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 251, 347, 10));

        btnLoginWindow.setFont(new java.awt.Font("Bitstream Vera Sans", 1, 12)); // NOI18N
        btnLoginWindow.setForeground(new java.awt.Color(66, 78, 92));
        btnLoginWindow.setText("LOGIN");
        btnLoginWindow.setFocusPainted(false);
        btnLoginWindow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginWindowActionPerformed(evt);
            }
        });
        jPanel1.add(btnLoginWindow, new org.netbeans.lib.awtextra.AbsoluteConstraints(301, 360, 90, -1));

        btnCloseWindow.setFont(new java.awt.Font("Bitstream Vera Sans", 1, 12)); // NOI18N
        btnCloseWindow.setForeground(new java.awt.Color(255, 255, 255));
        btnCloseWindow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrarIcon.png"))); // NOI18N
        btnCloseWindow.setBorderPainted(false);
        btnCloseWindow.setContentAreaFilled(false);
        btnCloseWindow.setFocusPainted(false);
        btnCloseWindow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseWindowActionPerformed(evt);
            }
        });
        jPanel1.add(btnCloseWindow, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 50, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 410, 400));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/verde.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 400));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        xcord = evt.getX();
        ycord = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xcord, y - ycord);
    }//GEN-LAST:event_formMouseDragged

    private void btnCloseWindowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseWindowActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCloseWindowActionPerformed

    private void btnLoginWindowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginWindowActionPerformed
        String USUARIO = txtUsuario.getText();
        String CLAVE = String.valueOf(txtClave.getPassword());

        if (!USUARIO.isEmpty() && !CLAVE.isEmpty()) {
            usuario = new Usuario(USUARIO, CLAVE);
            DBUsuario.validarLogin(this, usuario);
        }
    }//GEN-LAST:event_btnLoginWindowActionPerformed

    private void txtClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveActionPerformed
        btnLoginWindow.doClick();
    }//GEN-LAST:event_txtClaveActionPerformed

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        txtClave.requestFocus();
        }
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtClaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        btnLoginWindow.requestFocus();
        }
    }//GEN-LAST:event_txtClaveKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCloseWindow;
    private javax.swing.JButton btnLoginWindow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
