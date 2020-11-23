/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.vistas;

import app.Login;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import modelos.Area;
import modelos.AreaListModel;
import modelos.DBArea;
import modelos.DBExamen;
import modelos.DBPersona;
import modelos.DBUsuario;
import modelos.Persona;
import modelos.Usuario;

/**
 *
 * @author francisco
 */
public class panelAdministrar extends javax.swing.JPanel {

    private final JFrame FRAME;
    private final boolean ACTUALIZAR_ADMIN = false;
    private final Usuario USUARIO_ACTUAL;

    private AreaListModel modeloArea;
    private String ID;
    private String AREA;

    /**
     * Creates new form vistaUsuarios
     *
     * @param FRAME
     * @param USUARIO_ACTUAL
     */
    public panelAdministrar(JFrame FRAME, Usuario USUARIO_ACTUAL) {
        initComponents();
        initAreaYExamen();
        initPacientes();
        initUsuarios();

        this.FRAME = FRAME;
        this.USUARIO_ACTUAL = USUARIO_ACTUAL;
        txtTipoUsuario.setText("");
    }

    private void initAreaYExamen() {
        modeloArea = new AreaListModel();
        listAreaExamen.setModel(modeloArea);

        DBArea.obtenerRegistros().forEach(area -> {
            modeloArea.addArea(new Area(area.getId(), area.getNombre()));
        });
    }

    private void initTablaExamenes(String IdArea) {
        tablaExamenes.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "AREA", "EXAMEN", "PRECIO"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        if (tablaExamenes.getColumnModel().getColumnCount() > 0) {
            tablaExamenes.getColumnModel().getColumn(0).setMinWidth(50);
            tablaExamenes.getColumnModel().getColumn(0).setPreferredWidth(50);
            tablaExamenes.getColumnModel().getColumn(1).setMinWidth(50);
            tablaExamenes.getColumnModel().getColumn(1).setPreferredWidth(50);
            tablaExamenes.getColumnModel().getColumn(2).setMinWidth(300);
            tablaExamenes.getColumnModel().getColumn(2).setPreferredWidth(300);
            tablaExamenes.getColumnModel().getColumn(3).setMinWidth(100);
            tablaExamenes.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        jScrollPane3.setViewportView(tablaExamenes);
        tablaExamenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tablaExamenes.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int SELECCION = tablaExamenes.getSelectedRow();
            if (SELECCION != -1) {
                String PRECIO = String.valueOf(tablaExamenes.getValueAt(SELECCION, 3));
                ID = String.valueOf(tablaExamenes.getValueAt(SELECCION, 0));
                AREA = String.valueOf(tablaExamenes.getValueAt(SELECCION, 1));

                txtActualizarPrecio.setText(PRECIO);

            } else {
                ID = null;
                AREA = null;
            }
        });

        DefaultTableModel dm = (DefaultTableModel) tablaExamenes.getModel();
        DBExamen.obtenerRegistros(IdArea).forEach(examen -> {
            dm.addRow(new Object[]{examen.getId(), examen.getIdArea(), examen.getNombre(), examen.getPrecio()});
        });
    }

    private void initUsuarios() {
        initTablaUsuarios();
    }

    private void initTablaUsuarios() {
        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "NOMBRE", "TIPO", "HORA CREACION", "FECHA CREACION"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        if (tablaUsuarios.getColumnModel().getColumnCount() > 0) {
            tablaUsuarios.getColumnModel().getColumn(0).setMinWidth(150);
            tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(150);
            tablaUsuarios.getColumnModel().getColumn(1).setMinWidth(150);
            tablaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(150);
            tablaUsuarios.getColumnModel().getColumn(2).setMinWidth(150);
            tablaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(150);
            tablaUsuarios.getColumnModel().getColumn(3).setMinWidth(150);
            tablaUsuarios.getColumnModel().getColumn(3).setPreferredWidth(150);
            tablaUsuarios.getColumnModel().getColumn(4).setMinWidth(150);
            tablaUsuarios.getColumnModel().getColumn(4).setPreferredWidth(150);
        }

        jScrollPane4.setViewportView(tablaUsuarios);
        tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        recargarUsuariosEnTabla();

    }

    private void recargarUsuariosEnTabla() {
        DefaultTableModel dm = (DefaultTableModel) tablaUsuarios.getModel();
        DBUsuario.obtenerRegistros().forEach(usuario -> {
            dm.addRow(new Object[]{usuario.getId(), usuario.getNombre(), usuario.getTipo(), usuario.getHora(), usuario.getFecha()});
        });
    }

    private void initPacientes() {
        initTablaPacientes();
       
    }

    private void initTablaPacientes() {
        tablaPacientes.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "DPI", "NOMBRES", "DIRECCION", "REGISTRADO POR"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        if (tablaPacientes.getColumnModel().getColumnCount() > 0) {
            tablaPacientes.getColumnModel().getColumn(0).setMinWidth(100);
            tablaPacientes.getColumnModel().getColumn(0).setPreferredWidth(100);
            tablaPacientes.getColumnModel().getColumn(1).setMinWidth(150);
            tablaPacientes.getColumnModel().getColumn(1).setPreferredWidth(150);
            tablaPacientes.getColumnModel().getColumn(2).setMinWidth(150);
            tablaPacientes.getColumnModel().getColumn(2).setPreferredWidth(150);
            tablaPacientes.getColumnModel().getColumn(3).setMinWidth(150);
            tablaPacientes.getColumnModel().getColumn(3).setPreferredWidth(150);
        }

        jScrollPane1.setViewportView(tablaPacientes);
        tablaPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tablaPacientes.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int SELECCION = tablaPacientes.getSelectedRow();
            if (SELECCION != -1) {
                String DPI = String.valueOf(tablaPacientes.getValueAt(SELECCION, 0));
                Persona persona = DBPersona.obtenerRegistro(DPI);

                txtPersonaDpi.setText(persona.getDpi());
                txtPersonaNombre.setText(persona.getNombre());
                txtPersonaApellido.setText(persona.getApellido());
                txtPersonaFechaN.setText(persona.getFechaNacimiento());
                txtPersonaTelefono.setText(String.valueOf(persona.getTelefono()));

                String GENERO = persona.getGenero();
                switch (GENERO) {
                    case "M":
                        comboPersonaGenero.setSelectedIndex(0);
                        break;
                    case "F":
                        comboPersonaGenero.setSelectedIndex(1);
                        break;
                    default:
                        comboPersonaGenero.setSelectedIndex(2);
                        break;
                }

                txtPersonaCorreo.setText(persona.getCorreo());
                txtPersonaDireccion.setText(persona.getDireccion());
                txtPersonaRegistradoPor.setText("REGISTRADO POR: " + persona.getRegistroPor());
                txtPersonaHoraCommit.setText("HORA CREACION: " + persona.getHora());
                txtPersonaFechaCommit.setText("FECHA CREACION: " + persona.getFecha());
            } else {

            }
        });

        recargarPacientesEnTabla();

    }

    private void recargarPacientesEnTabla() {
        DefaultTableModel dm = (DefaultTableModel) tablaPacientes.getModel();
        DBPersona.obtenerRegistros().forEach(persona -> {
            dm.addRow(new Object[]{persona.getDpi(), persona.getNombre() + " " + persona.getApellido(), persona.getDireccion(), persona.getRegistroPor()});
        });
    }

    private void resetearCamposUsuario() {
        txtTipoUsuario.setText("");
        txtIdUsuario.setText("");
        txtNombreUsuario.setText("");
        txtClaveUsuario.setText("");
        txtCClaveUsuario.setText("");
    }

    private void resetearCamposPersona() {
        txtPersonaDpi.setText("");
        txtPersonaNombre.setText("");
        txtPersonaApellido.setText("");
        txtPersonaFechaN.setText("");
        txtPersonaTelefono.setText("");
        comboPersonaGenero.setSelectedIndex(0);
        txtPersonaCorreo.setText("");
        txtPersonaDireccion.setText("");
        txtPersonaRegistradoPor.setText("REGISTRADO POR:");
        txtPersonaHoraCommit.setText("HORA CREACION:");
        txtPersonaFechaCommit.setText("FECHA CREACION:");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabPaneAdministrar = new javax.swing.JTabbedPane();
        tabExamen = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listAreaExamen = new javax.swing.JList<String>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaExamenes = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtActualizarPrecio = new javax.swing.JFormattedTextField();
        btnActualizarPrecio = new javax.swing.JButton();
        tabUsuario = new javax.swing.JPanel();
        lblInfoUsuario = new javax.swing.JLabel();
        txtTipoUsuario = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtClaveUsuario = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        txtCClaveUsuario = new javax.swing.JPasswordField();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tabPaciente = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPacientes = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtPersonaDpi = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPersonaNombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPersonaApellido = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtPersonaCorreo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtPersonaDireccion = new javax.swing.JTextPane();
        btnActualizarPersona = new javax.swing.JButton();
        btnEliminarPersona = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtPersonaFechaN = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        comboPersonaGenero = new javax.swing.JComboBox<String>();
        txtPersonaRegistradoPor = new javax.swing.JLabel();
        txtPersonaHoraCommit = new javax.swing.JLabel();
        txtPersonaFechaCommit = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtPersonaTelefono = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(850, 640));
        setMinimumSize(new java.awt.Dimension(850, 640));
        setPreferredSize(new java.awt.Dimension(850, 640));

        tabExamen.setBackground(new java.awt.Color(0, 204, 204));

        listAreaExamen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        listAreaExamen.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listAreaExamen.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listAreaExamenValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listAreaExamen);

        tablaExamenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "EXAMEN", "PRECIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablaExamenes);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("PRECIO");

        txtActualizarPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtActualizarPrecio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtActualizarPrecio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnActualizarPrecio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/VerifyIcon.png"))); // NOI18N
        btnActualizarPrecio.setText("ACTUALIZAR");
        btnActualizarPrecio.setToolTipText("ACTUALIZAR PRECIO");
        btnActualizarPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPrecioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabExamenLayout = new javax.swing.GroupLayout(tabExamen);
        tabExamen.setLayout(tabExamenLayout);
        tabExamenLayout.setHorizontalGroup(
            tabExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabExamenLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(tabExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tabExamenLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabExamenLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtActualizarPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizarPrecio)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabExamenLayout.setVerticalGroup(
            tabExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabExamenLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(tabExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tabExamenLayout.createSequentialGroup()
                        .addGroup(tabExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtActualizarPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizarPrecio))
                        .addGap(7, 7, 7))
                    .addGroup(tabExamenLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(tabExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tabPaneAdministrar.addTab("AREAS Y EXAMENES", tabExamen);

        tabUsuario.setBackground(new java.awt.Color(0, 204, 204));

        lblInfoUsuario.setText("INFORMACION DE USUARIO");

        txtTipoUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTipoUsuario.setText("ESTANDAR");

        lblId.setText("ID");

        jLabel2.setText("NOMBRE");

        jLabel3.setText("CLAVE");

        jLabel4.setText("CONFIRMAR CLAVE");

        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "TIPO", "HORA CREACION", "FECHA CREACION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tablaUsuarios);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/userIcon.png"))); // NOI18N

        javax.swing.GroupLayout tabUsuarioLayout = new javax.swing.GroupLayout(tabUsuario);
        tabUsuario.setLayout(tabUsuarioLayout);
        tabUsuarioLayout.setHorizontalGroup(
            tabUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUsuarioLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(tabUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabUsuarioLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)
                        .addGap(47, 47, 47)
                        .addGroup(tabUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabUsuarioLayout.createSequentialGroup()
                                .addComponent(lblInfoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabUsuarioLayout.createSequentialGroup()
                                .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabUsuarioLayout.createSequentialGroup()
                                .addComponent(btnEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCancelar)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardar))
                            .addGroup(tabUsuarioLayout.createSequentialGroup()
                                .addGroup(tabUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtClaveUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(100, 100, 100)
                                .addGroup(tabUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tabUsuarioLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel4))
                                    .addComponent(txtCClaveUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        tabUsuarioLayout.setVerticalGroup(
            tabUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUsuarioLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(tabUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabUsuarioLayout.createSequentialGroup()
                        .addComponent(txtTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addGroup(tabUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtClaveUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCClaveUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(tabUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnGuardar)
                                .addComponent(btnCancelar))
                            .addComponent(btnEliminar)
                            .addComponent(btnEditar)))
                    .addGroup(tabUsuarioLayout.createSequentialGroup()
                        .addComponent(lblInfoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(tabUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabUsuarioLayout.createSequentialGroup()
                                .addGroup(tabUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblId)
                                    .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(45, 45, 45)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabPaneAdministrar.addTab("ADMINISTRAR USUARIOS", tabUsuario);

        tabPaciente.setBackground(new java.awt.Color(0, 204, 204));

        tablaPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DPI", "NOMBRES", "DIRECCION", "REGISTRADO POR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaPacientes);

        jLabel5.setText("DPI");

        txtPersonaDpi.setEditable(false);
        try {
            txtPersonaDpi.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##############")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel7.setText("NOMBRE*");

        jLabel8.setText("APELLIDO*");

        jLabel9.setText("TELEFONO");

        jLabel10.setText("CORREO");

        jLabel11.setText("DIRECCION*");

        jScrollPane5.setViewportView(txtPersonaDireccion);

        btnActualizarPersona.setText("ACTUALIZAR");
        btnActualizarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPersonaActionPerformed(evt);
            }
        });

        btnEliminarPersona.setText("ELIMINAR");
        btnEliminarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPersonaActionPerformed(evt);
            }
        });

        jLabel12.setText("F. NACIMIENTO*");

        txtPersonaFechaN.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        jLabel13.setText("GENERO*");

        comboPersonaGenero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "F", "OTRO" }));

        txtPersonaRegistradoPor.setText("REGISTRADO POR: ");

        txtPersonaHoraCommit.setText("HORA CREACION");

        txtPersonaFechaCommit.setText("FECHA CREACION");

        txtPersonaTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPersonaTelefonoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout tabPacienteLayout = new javax.swing.GroupLayout(tabPaciente);
        tabPaciente.setLayout(tabPacienteLayout);
        tabPacienteLayout.setHorizontalGroup(
            tabPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPacienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(tabPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tabPacienteLayout.createSequentialGroup()
                        .addComponent(btnActualizarPersona)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5)
                    .addComponent(txtPersonaCorreo)
                    .addComponent(txtPersonaDpi)
                    .addComponent(txtPersonaNombre)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(txtPersonaApellido)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabPacienteLayout.createSequentialGroup()
                        .addGroup(tabPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPersonaFechaN))
                        .addGap(27, 27, 27)
                        .addGroup(tabPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabPacienteLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(0, 14, Short.MAX_VALUE))
                            .addComponent(txtPersonaTelefono))
                        .addGap(18, 18, 18)
                        .addGroup(tabPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(comboPersonaGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtPersonaRegistradoPor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(txtPersonaHoraCommit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPersonaFechaCommit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        tabPacienteLayout.setVerticalGroup(
            tabPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPacienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabPacienteLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPersonaDpi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(5, 5, 5)
                        .addComponent(txtPersonaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPersonaApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(tabPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPersonaFechaN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboPersonaGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPersonaTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPersonaCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPersonaRegistradoPor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPersonaHoraCommit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPersonaFechaCommit)
                        .addGap(33, 33, 33)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(tabPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnActualizarPersona)
                            .addComponent(btnEliminarPersona)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tabPaneAdministrar.addTab("PACIENTES REGISTRADOS", tabPaciente);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPaneAdministrar)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPaneAdministrar)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void listAreaExamenValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listAreaExamenValueChanged
        int SELECCION = listAreaExamen.getSelectedIndex();
        if (SELECCION != -1) {
            Area area = modeloArea.getArea(SELECCION);
            initTablaExamenes(area.getId());
            txtActualizarPrecio.setText(null);
        }

    }//GEN-LAST:event_listAreaExamenValueChanged

    private void btnActualizarPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPrecioActionPerformed
        String PRECIO = txtActualizarPrecio.getText();
        if (!PRECIO.isEmpty() && ID != null && AREA != null) {
            DBExamen.actualizarRegistro(ID, AREA, PRECIO);
            initTablaExamenes(AREA);
        }
    }//GEN-LAST:event_btnActualizarPrecioActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        int SELECCION = tablaUsuarios.getSelectedRow();
        if (SELECCION != -1) {

            String IDUSUARIO = String.valueOf(tablaUsuarios.getValueAt(SELECCION, 0));
            Usuario usuario = DBUsuario.obtenerRegistro(IDUSUARIO);
            if (usuario != null) {
                btnEditar.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnGuardar.setText("ACTUALIZAR");

                txtTipoUsuario.setText(usuario.getTipo());
                txtIdUsuario.setText(String.valueOf(usuario.getId()));
                txtNombreUsuario.setText(usuario.getNombre());
                txtClaveUsuario.setText(usuario.getClave());
                txtCClaveUsuario.setText(usuario.getClave());
            }

        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int SELECCION = tablaUsuarios.getSelectedRow();
        if (SELECCION != -1) {

            String IDUSUARIO = String.valueOf(tablaUsuarios.getValueAt(SELECCION, 0));
            String USUARIO = String.valueOf(tablaUsuarios.getValueAt(SELECCION, 1));
            String TIPO = String.valueOf(tablaUsuarios.getValueAt(SELECCION, 2));

            Usuario usuario = DBUsuario.obtenerRegistro(IDUSUARIO);

            if (usuario != null) {
                if (TIPO.equals("Administrador")) {
                    JOptionPane.showMessageDialog(this, "EL USUARIO ADMINISTRADOR NO SE PUEDE ELIMINAR", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    int respuesta = JOptionPane.showConfirmDialog(this, "SI ELIMINA ESTE USUARIO NO SE PODRA RECUPERAR ?", "CONFIRMAR ELIMINAR USUARIO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        DBUsuario.eliminarRegistro(IDUSUARIO, USUARIO);
                        initUsuarios();
                    }
                }
            }

        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnGuardar.setText("GUARDAR");
        resetearCamposUsuario();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String NOMBRE = txtNombreUsuario.getText();
        String CLAVE = String.valueOf(txtClaveUsuario.getPassword());
        String CCLAVE = String.valueOf(txtCClaveUsuario.getPassword());

        if (!NOMBRE.isEmpty() && !CLAVE.isEmpty() && !CCLAVE.isEmpty()) {
            if (CLAVE.equals(CCLAVE)) {
                if (btnGuardar.getText().equals("GUARDAR")) {
                    Usuario usuario = new Usuario(NOMBRE, CLAVE, "Usuario");
                    DBUsuario.guardarRegistro(usuario);

                    initUsuarios();
                    resetearCamposUsuario();

                } else if (btnGuardar.getText().equals("ACTUALIZAR")) {
                    String IDU = txtIdUsuario.getText();
                    String TIPO = txtTipoUsuario.getText();

                    System.out.println("ID: " + IDU + " TIPO:" + TIPO);
                    if (DBUsuario.actualizarRegistro(IDU, NOMBRE, CLAVE)) {
                        if (TIPO.equals("Administrador")) {
                            JOptionPane.showMessageDialog(this, "SE REINICIARA LA APLICACION PARA REALIZAR LOS CAMBIOS", "INFORMACIO", JOptionPane.INFORMATION_MESSAGE);
                            initUsuarios();
                            resetearCamposUsuario();
                            btnEditar.setEnabled(true);
                            btnEliminar.setEnabled(true);
                            btnGuardar.setText("GUARDAR");

                            Login login = new Login();
                            login.setVisible(true);
                            FRAME.dispose();
                        } else {
                            initUsuarios();
                            resetearCamposUsuario();
                            btnEditar.setEnabled(true);
                            btnEliminar.setEnabled(true);
                            btnGuardar.setText("GUARDAR");

                        }
                    }

                }

            } else {
                JOptionPane.showMessageDialog(this, "LAS CLAVES NO COINCIDEN", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "LOS CAMPOS NO PUEDEN ESTAR VACIOS", "ERROR", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPersonaActionPerformed
        String DPI = txtPersonaDpi.getText();
        String NOMBRE = txtPersonaNombre.getText();
        String APELLIDO = txtPersonaApellido.getText();
        String FECHANACIMIENTO = txtPersonaFechaN.getText();
        String GENERO = (String) comboPersonaGenero.getSelectedItem();

        String TELEFONO = txtPersonaTelefono.getText();
        String CORREO = txtPersonaCorreo.getText();
        String DIRECCION = txtPersonaDireccion.getText();

        if (!DPI.isEmpty() && !NOMBRE.isEmpty() && !APELLIDO.isEmpty() && !FECHANACIMIENTO.isEmpty() && !DIRECCION.isEmpty()) {
            Persona persona = new Persona(DPI, NOMBRE, APELLIDO, FECHANACIMIENTO, GENERO, TELEFONO, CORREO, DIRECCION);
            DBPersona.actualizarRegistro(persona);
            initPacientes();
            resetearCamposPersona();
        } else {
            JOptionPane.showMessageDialog(this, "LOS CAMPOS MARCADOS CON * NO PUEDEN ESTAR VACIOS", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarPersonaActionPerformed

    private void btnEliminarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPersonaActionPerformed
        int SELECCION = tablaPacientes.getSelectedRow();
        if (SELECCION != -1) {
            String DPI = String.valueOf(tablaPacientes.getValueAt(SELECCION, 0));

            Persona persona = DBPersona.obtenerRegistro(DPI);

            if (persona != null) {
                int respuesta = JOptionPane.showConfirmDialog(this, "SI ELIMINA ESTA PERSONA NO SE PODRA RECUPERAR ?", "CONFIRMAR ELIMINAR PERSONA", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION) {
                    DBPersona.eliminarRegistro(DPI);
                    initPacientes();
                    resetearCamposPersona();
                }
            }

        }
    }//GEN-LAST:event_btnEliminarPersonaActionPerformed

    private void txtPersonaTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonaTelefonoKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || txtPersonaTelefono.getText().length() >= 8) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPersonaTelefonoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarPersona;
    private javax.swing.JButton btnActualizarPrecio;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarPersona;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> comboPersonaGenero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblInfoUsuario;
    private javax.swing.JList<String> listAreaExamen;
    private javax.swing.JPanel tabExamen;
    private javax.swing.JPanel tabPaciente;
    private javax.swing.JTabbedPane tabPaneAdministrar;
    private javax.swing.JPanel tabUsuario;
    private javax.swing.JTable tablaExamenes;
    private javax.swing.JTable tablaPacientes;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JFormattedTextField txtActualizarPrecio;
    private javax.swing.JPasswordField txtCClaveUsuario;
    private javax.swing.JPasswordField txtClaveUsuario;
    private javax.swing.JLabel txtIdUsuario;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JTextField txtPersonaApellido;
    private javax.swing.JTextField txtPersonaCorreo;
    private javax.swing.JTextPane txtPersonaDireccion;
    private javax.swing.JFormattedTextField txtPersonaDpi;
    private javax.swing.JLabel txtPersonaFechaCommit;
    private javax.swing.JFormattedTextField txtPersonaFechaN;
    private javax.swing.JLabel txtPersonaHoraCommit;
    private javax.swing.JTextField txtPersonaNombre;
    private javax.swing.JLabel txtPersonaRegistradoPor;
    private javax.swing.JTextField txtPersonaTelefono;
    private javax.swing.JLabel txtTipoUsuario;
    // End of variables declaration//GEN-END:variables
}
