package app.vistas;

import static conexion.conexion.getConection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * @author Marlon Caal
 */
public class panelReportes extends javax.swing.JPanel {
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    
    public panelReportes() {
        initComponents();
        try {

            DefaultTableModel modelo = new DefaultTableModel();
            jTable1.setModel(modelo);

            //PreparedStatement ps = null;
            //ResultSet rs = null;
            con = getConection();

            String sql = "SELECT c.Id, p.Dpi, CONCAT(p.Nombre, ' ', p.Apellido) 'Nombre y Apellido', p.Genero, p.Telefono, p.Correo, p.Direccion, c.NombreDoctor, c.CorreoDoctor, e.Nombre, c.FechaCommit FROM personas p JOIN colaexamenes c ON c.DpiPaciente = p.Dpi JOIN examenes e ON c.IdExamen = e.Id";
            System.out.println(sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();

            modelo.addColumn("Id");
            modelo.addColumn("Dpi");
            modelo.addColumn("Nombre y Apellido");
            modelo.addColumn("Genero");
            modelo.addColumn("Telefono");
            modelo.addColumn("Correo");
            modelo.addColumn("Direccion");
            modelo.addColumn("Medico");
            modelo.addColumn("Correo");
            modelo.addColumn("Examene");
            modelo.addColumn("Fecha");
            
            int[] anchos = {5, 50, 140, 6, 15, 40, 100, 40, 40, 120, 50};
            for (int x = 0; x < cantidadColumnas; x++) {
                jTable1.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
            }

            while (rs.next()) {

                Object[] filas = new Object[cantidadColumnas];

                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }

        } catch (Exception ex) {
            System.err.println(ex.toString());
        }

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngenerarReporte = new javax.swing.JButton();
        txtparametro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtexamen = new javax.swing.JTextField();
        txtfecha = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnadjunto = new javax.swing.JButton();
        txtcorreo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtcorreomedico = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(850, 640));
        setMinimumSize(new java.awt.Dimension(850, 640));
        setPreferredSize(new java.awt.Dimension(850, 640));

        btngenerarReporte.setText("Generar Rporte");
        btngenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngenerarReporteActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("DPI Paciente");

        jLabel2.setText("Examen");

        jLabel3.setText("Fecha de Registro");

        btnadjunto.setText("Enviar Correo");
        btnadjunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnadjuntoActionPerformed(evt);
            }
        });

        jLabel4.setText(" Correo Paciente");

        jLabel5.setText("Correo Medico");

        jLabel6.setText("ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel6))
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtparametro, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtexamen, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtcorreomedico, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnadjunto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btngenerarReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(btngenerarReporte)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtparametro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtexamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnadjunto)
                    .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcorreomedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btngenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngenerarReporteActionPerformed
        try {
            con = getConection();
            Connection conn = con;
            
            JasperReport reporte = null;
            String path = "src\\reportes\\resultados.jasper";
            
            Map parametro = new HashMap();
            parametro.put("id", txtparametro.getText());
            //parametro.put("examen", txtexamen.getText());
            //parametro.put("fecha", txtfecha.getText());
            
            
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            
            JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, conn);
            
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
            
        } catch (JRException ex) {
            Logger.getLogger(panelReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_btngenerarReporteActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
        int seleccion = jTable1.rowAtPoint(evt.getPoint());
        txtid.setText(String.valueOf(jTable1.getValueAt(seleccion, 0)));
        txtparametro.setText(String.valueOf(jTable1.getValueAt(seleccion, 1)));
        txtexamen.setText(String.valueOf(jTable1.getValueAt(seleccion, 9)));
        txtfecha.setText(String.valueOf(jTable1.getValueAt(seleccion, 0)));
        //txtfecha.setText(String.valueOf(jTable1.getValueAt(seleccion, 1)));
        txtcorreo.setText(String.valueOf(jTable1.getValueAt(seleccion, 5)));
        txtcorreomedico.setText(String.valueOf(jTable1.getValueAt(seleccion, 8)));
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnadjuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnadjuntoActionPerformed
        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            
            javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
            
            String correoRemitente = "martiu171@gmail.com";
            String passwordRemitente = ".me.69_91.ra";
            String correoReceptor = txtcorreo.getText();
            String correoReceptor2 = txtcorreomedico.getText();
            String asunto = "Resultados de Examenes";
            String mensaje = "Buen día <br>Adjunto sus Resultados<b> java</b><br><br>Por <b>BioLab</b>";
            
            BodyPart texto = new MimeBodyPart();
                    texto.setContent(mensaje, "text/html");
                    
            BodyPart adjunto = new MimeBodyPart();
            //adjunto.setDataHandler(new DataHandler(new FileDataSource("E:/Resultados.pdf")));
            adjunto.setDataHandler(new DataHandler(new FileDataSource("E:/Reportes/"+ txtfecha.getText()+".pdf")));
            adjunto.setFileName("Resultados.pdf");
            
            MimeMultipart miltiParte = new MimeMultipart();
            miltiParte.addBodyPart(texto);
            miltiParte.addBodyPart(adjunto);
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoRemitente));
            
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
            //para enviar una copia a otra persona
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(correoReceptor2));
            message.setSubject(asunto);
            message.setContent(miltiParte);
            
            Transport t = session.getTransport("smtp");
            t.connect(correoRemitente, passwordRemitente);
            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            t.sendMessage(message, message.getRecipients(Message.RecipientType.CC));
            t.close();
            
            JOptionPane.showMessageDialog(null, "Correo Electronico Enviado");
            
        } catch (AddressException ex) {
            Logger.getLogger(panelReportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(panelReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnadjuntoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadjunto;
    private javax.swing.JButton btngenerarReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtcorreomedico;
    private javax.swing.JTextField txtexamen;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtparametro;
    // End of variables declaration//GEN-END:variables
}
