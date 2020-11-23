package app.vistas;

import java.awt.Component;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import modelos.Area;
import modelos.ColaExamen;
import modelos.DBArea;
import modelos.DBColaExamen;
import modelos.DBExamen;
import modelos.DBPersona;
import modelos.Examen;
import modelos.Persona;
import modelos.Usuario;

/**
 * Marlon Caal *
 */
public class panelRegistro extends javax.swing.JPanel {

    private final Usuario usuario;
    private double TOTAL_EXAMENES = 0;

    public panelRegistro(Usuario usuario) {
        initComponents();
        initNuevoRegistro();
        initTablaExamenesPaciente();
        this.usuario = usuario;
    }

    private void buscarUsuarioEnLaDB() {
        Persona persona = DBPersona.obtenerRegistroLike(txtPacienteDpi.getText());
        if (persona != null) {
            txtPacienteNombre.setText(persona.getNombre());
            txtPacienteApellido.setText(persona.getApellido());
            txtPacienteFNacimiento.setText(persona.getFechaNacimiento());

            switch (persona.getGenero()) {
                case "M":
                    comboPacienteGenero.setSelectedIndex(0);
                    break;
                case "F":
                    comboPacienteGenero.setSelectedIndex(1);
                    break;
                default:
                    comboPacienteGenero.setSelectedIndex(2);
                    break;
            }

            txtPacienteTelefono.setText(persona.getTelefono());
            txtPacienteCorreo.setText(persona.getCorreo());
            txtPacienteDireccion.setText(persona.getDireccion());
        } else {
            txtPacienteNombre.setText("");
            txtPacienteApellido.setText("");
            txtPacienteFNacimiento.setText("");
            comboPacienteGenero.setSelectedIndex(0);
            txtPacienteTelefono.setText("");
            txtPacienteCorreo.setText("");
            txtPacienteDireccion.setText("");
        }
    }

    private void initNuevoRegistro() {
        txtPacienteDpi.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarUsuarioEnLaDB();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarUsuarioEnLaDB();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarUsuarioEnLaDB();
            }
        });

        llenarComboArea();
    }

    private void resetearCampos() {
        txtPacienteDpi.setText("");
        txtPacienteNombre.setText("");
        txtPacienteApellido.setText("");
        txtPacienteFNacimiento.setText("");
        txtPacienteTelefono.setText("");
        txtPacienteCorreo.setText("");
        txtPacienteDireccion.setText("");

        txtDoctorNombre.setText("");
        txtDoctorTelefono.setText("");
        txtDoctorCorreo.setText("");

        initTablaExamenesPaciente();
        TOTAL_EXAMENES = 0;
        txtTotalExamenes.setText(String.valueOf(TOTAL_EXAMENES));
    }

    private void initTablaExamenesPaciente() {
        tablaExamenesPaciente.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "CODIGO", "DESCRIPCION", "COSTO"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaExamenesPaciente);

        tablaExamenesPaciente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaExamenesPaciente.getModel().addTableModelListener((TableModelEvent tme) -> {
            if (tme.getType() == TableModelEvent.INSERT) {
                double precio = (double) tablaExamenesPaciente.getValueAt(tme.getLastRow(), 2);

                TOTAL_EXAMENES += precio;
                txtTotalExamenes.setText(String.valueOf(TOTAL_EXAMENES));
            }
        });
    }

    private void llenarComboArea() {
        List<Area> areas = DBArea.obtenerRegistros();
        comboAreaExamen.setModel(new DefaultComboBoxModel(areas.toArray()));
        comboAreaExamen.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Area) {
                    Area area = (Area) value;
                    setText(area.getNombre());
                }
                return this;
            }
        });
    }

    private void llenarComboExamenes(String area) {
        List<Examen> examenes = DBExamen.obtenerRegistros(area);
        comboNombreExamen.setModel(new DefaultComboBoxModel(examenes.toArray()));
        comboNombreExamen.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Examen) {
                    Examen examen = (Examen) value;
                    setText(examen.getNombre());
                }
                return this;
            }
        });
    }

    private void guardarDetallesRegistro() {
        //Datos del paciente
        String DPI = txtPacienteDpi.getText();
        String NPACIENTE = txtPacienteNombre.getText();
        String APACIENTE = txtPacienteApellido.getText();
        String FNACIMIENTO = txtPacienteFNacimiento.getText();
        String GENERO = comboPacienteGenero.getSelectedItem().toString();
        String TPACIENTE = txtPacienteTelefono.getText();
        String CPACIENTE = txtPacienteCorreo.getText();
        String DIRECCION = txtPacienteDireccion.getText();

        //Guardar o Actualizar Paciente
        Persona encontrada = DBPersona.obtenerRegistro(DPI);
        if (encontrada != null) {
            Persona persona = new Persona(DPI, NPACIENTE, APACIENTE, FNACIMIENTO, GENERO, TPACIENTE, CPACIENTE, DIRECCION);
            DBPersona.actualizarRegistro(persona);
        } else {
            Persona persona = new Persona(DPI, NPACIENTE, APACIENTE, FNACIMIENTO, GENERO, TPACIENTE, CPACIENTE, DIRECCION, usuario.getNombre());
            DBPersona.guardarRegistro(persona);
        }

        //Guardar Examenes y doctor seleccionados
        String NDOCTOR = txtDoctorNombre.getText();

        String TDOCTOR;
        if (txtDoctorTelefono.getText().isEmpty()) {
            TDOCTOR = "";
        } else {
            TDOCTOR = txtDoctorTelefono.getText();
        }

        String CDOCTOR;
        if (txtDoctorCorreo.getText().isEmpty()) {
            CDOCTOR = "";
        } else {
            CDOCTOR = txtDoctorCorreo.getText();
        }

        for (int x = 0; x < tablaExamenesPaciente.getRowCount(); x++) {
            String IDEXAMEN = String.valueOf(tablaExamenesPaciente.getValueAt(x, 0));
            String DESCRIPCIONEXAMEN = String.valueOf(tablaExamenesPaciente.getValueAt(x, 1));

            ColaExamen colaExamen = new ColaExamen(DPI, NDOCTOR, TDOCTOR, CDOCTOR, IDEXAMEN, DESCRIPCIONEXAMEN);
            DBColaExamen.guardarRegistro(colaExamen);
        }

        resetearCampos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPacienteNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPacienteApellido = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPacienteFNacimiento = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        comboPacienteGenero = new javax.swing.JComboBox<String>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtPacienteCorreo = new javax.swing.JTextField();
        txtPacienteDpi = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPacienteDireccion = new javax.swing.JTextArea();
        txtPacienteTelefono = new javax.swing.JTextField();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtDoctorNombre = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDoctorTelefono = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDoctorCorreo = new javax.swing.JTextField();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        comboAreaExamen = new javax.swing.JComboBox<String>();
        comboNombreExamen = new javax.swing.JComboBox<String>();
        btnAgregarExamen = new javax.swing.JButton();
        btnEliminarExamen = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaExamenesPaciente = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        txtTotalExamenes = new javax.swing.JTextField();
        btnCancelarInformacionPaciente = new javax.swing.JButton();
        btnGuardarInformacioPaciente = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 204, 204));
        setMaximumSize(new java.awt.Dimension(850, 640));
        setMinimumSize(new java.awt.Dimension(850, 640));
        setPreferredSize(new java.awt.Dimension(850, 640));

        jLabel1.setText("DPI PACIENTE*");

        jLabel5.setText("NOMBRE PACIENTE*");

        jLabel6.setText("APELLIDO PACIENTE*");

        jLabel7.setText("FECHA NACIMIENTO P.*");

        jLabel8.setText("GENERO P.*");

        comboPacienteGenero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "F", "OTRO" }));

        jLabel9.setText("TELEFONO P.");

        jLabel10.setText("CORREO PACIENTE");

        txtPacienteDpi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPacienteDpiKeyTyped(evt);
            }
        });

        jLabel17.setText("DIRECCION*");

        txtPacienteDireccion.setColumns(20);
        txtPacienteDireccion.setRows(5);
        jScrollPane2.setViewportView(txtPacienteDireccion);

        txtPacienteTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPacienteTelefonoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtPacienteDpi, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtPacienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtPacienteApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtPacienteFNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel17)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(comboPacienteGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(34, 34, 34)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(txtPacienteTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(44, 44, 44)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(txtPacienteCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPacienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPacienteDpi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPacienteApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPacienteFNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboPacienteGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPacienteCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPacienteTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("INFORMACION DEL PACIENTE", jPanel4);

        jLabel4.setText("NOMBRE DOCTOR*");

        jLabel11.setText("TELEFONO D.");

        txtDoctorTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDoctorTelefonoKeyTyped(evt);
            }
        });

        jLabel12.setText("CORREO DOCTOR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDoctorTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDoctorCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addComponent(txtDoctorNombre))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDoctorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDoctorTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDoctorCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(147, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("INFORMACION DE DOCTOR", jPanel1);

        jLabel14.setText("AREA EXAMEN");

        jLabel15.setText("NOMBRE EXAMEN");

        comboAreaExamen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAreaExamenActionPerformed(evt);
            }
        });

        btnAgregarExamen.setText("AGREGAR");
        btnAgregarExamen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarExamenActionPerformed(evt);
            }
        });

        btnEliminarExamen.setText("ELIMINAR");
        btnEliminarExamen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarExamenActionPerformed(evt);
            }
        });

        tablaExamenesPaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "PRECIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaExamenesPaciente);

        jLabel16.setText("TOTAL");

        txtTotalExamenes.setEditable(false);

        btnCancelarInformacionPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/CancelIcon.png"))); // NOI18N
        btnCancelarInformacionPaciente.setText("CANCEL");
        btnCancelarInformacionPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarInformacionPacienteActionPerformed(evt);
            }
        });

        btnGuardarInformacioPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/AcceptIcon.png"))); // NOI18N
        btnGuardarInformacioPaciente.setText("LISTO");
        btnGuardarInformacioPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarInformacioPacienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboAreaExamen, 0, 239, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboNombreExamen, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregarExamen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarExamen))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalExamenes, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelarInformacionPaciente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuardarInformacioPaciente))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(comboAreaExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregarExamen)
                        .addComponent(btnEliminarExamen)
                        .addComponent(comboNombreExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotalExamenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardarInformacioPaciente)
                        .addComponent(btnCancelarInformacionPaciente)))
                .addContainerGap())
        );

        jTabbedPane3.addTab("INFORMACION DE EXAMENES", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addGap(18, 18, 18)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane1)
                    .addComponent(jTabbedPane2))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtPacienteDpiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPacienteDpiKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || txtPacienteDpi.getText().length() >= 13) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPacienteDpiKeyTyped

    private void txtDoctorTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDoctorTelefonoKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || txtDoctorTelefono.getText().length() >= 8) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDoctorTelefonoKeyTyped

    private void txtPacienteTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPacienteTelefonoKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || txtPacienteTelefono.getText().length() >= 8) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPacienteTelefonoKeyTyped

    private void comboAreaExamenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAreaExamenActionPerformed
        int SELECCION = comboAreaExamen.getSelectedIndex();
        if (SELECCION != -1) {
            Area area = (Area) comboAreaExamen.getSelectedItem();
            llenarComboExamenes(area.getId());
        }
    }//GEN-LAST:event_comboAreaExamenActionPerformed

    private void btnAgregarExamenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarExamenActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tablaExamenesPaciente.getModel();

        if (comboNombreExamen.getSelectedIndex() != -1) {

            Examen examen = (Examen) comboNombreExamen.getSelectedItem();
            if (examen != null) {
                modelo.addRow(new Object[]{examen.getId(), examen.getNombre(), examen.getPrecio()});
            }

        }
    }//GEN-LAST:event_btnAgregarExamenActionPerformed

    private void btnEliminarExamenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarExamenActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tablaExamenesPaciente.getModel();

        int seleccion = tablaExamenesPaciente.getSelectedRow();
        if (seleccion != -1) {
            double precio = (double) tablaExamenesPaciente.getValueAt(seleccion, 2);
            TOTAL_EXAMENES -= precio;
            txtTotalExamenes.setText(String.valueOf(TOTAL_EXAMENES));
            modelo.removeRow(seleccion);
        }
    }//GEN-LAST:event_btnEliminarExamenActionPerformed

    private void btnCancelarInformacionPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarInformacionPacienteActionPerformed
        resetearCampos();
    }//GEN-LAST:event_btnCancelarInformacionPacienteActionPerformed

    private void btnGuardarInformacioPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarInformacioPacienteActionPerformed
        boolean USUARIO_OK = false;
        boolean DOCTOR_OK = false;
        boolean EXAMENES_OK = false;

        String DPI = txtPacienteDpi.getText();
        String NPACIENTE = txtPacienteNombre.getText();
        String APACIENTE = txtPacienteApellido.getText();
        String FNACIMIENTO = txtPacienteFNacimiento.getText();
        String DIRECCION = txtPacienteDireccion.getText();

        if (!DPI.isEmpty() && !NPACIENTE.isEmpty() && !APACIENTE.isEmpty() && !FNACIMIENTO.isEmpty() && !DIRECCION.isEmpty()) {
            USUARIO_OK = true;
        }

        String NDOCTOR = txtDoctorNombre.getText();
        if (!NDOCTOR.isEmpty()) {
            DOCTOR_OK = true;
        }

        if (tablaExamenesPaciente.getRowCount() > 0) {
            EXAMENES_OK = true;
        }

        if (USUARIO_OK && DOCTOR_OK && EXAMENES_OK) {
            guardarDetallesRegistro();
        } else {
            JOptionPane.showMessageDialog(this, "LOS CAMPOS QUE CONTIENEN * NO PUEDEN ESTAR VACIOS Y DEBE SELECCIONAR AL MENOS UN EXAMEN", "ERROR", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnGuardarInformacioPacienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarExamen;
    private javax.swing.JButton btnCancelarInformacionPaciente;
    private javax.swing.JButton btnEliminarExamen;
    private javax.swing.JButton btnGuardarInformacioPaciente;
    private javax.swing.JComboBox<String> comboAreaExamen;
    private javax.swing.JComboBox<String> comboNombreExamen;
    private javax.swing.JComboBox<String> comboPacienteGenero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable tablaExamenesPaciente;
    private javax.swing.JTextField txtDoctorCorreo;
    private javax.swing.JTextField txtDoctorNombre;
    private javax.swing.JTextField txtDoctorTelefono;
    private javax.swing.JTextField txtPacienteApellido;
    private javax.swing.JTextField txtPacienteCorreo;
    private javax.swing.JTextArea txtPacienteDireccion;
    private javax.swing.JTextField txtPacienteDpi;
    private javax.swing.JFormattedTextField txtPacienteFNacimiento;
    private javax.swing.JTextField txtPacienteNombre;
    private javax.swing.JTextField txtPacienteTelefono;
    private javax.swing.JTextField txtTotalExamenes;
    // End of variables declaration//GEN-END:variables
}
