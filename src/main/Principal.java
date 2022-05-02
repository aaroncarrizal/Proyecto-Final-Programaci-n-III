package main;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Principal extends javax.swing.JFrame {

    public ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    public ArrayList<Paquete> listaPaquetes = new ArrayList<>();
    public Paquete p = new Paquete();
    public Empleado e = new Empleado();
    public ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    public Usuario us = new Usuario();
    public ArrayList<Paquete> paquetesAEntregar = new ArrayList<>();
    public boolean isAdmin1;
    public int easter = 0;
    public int milis = 100;

    public Principal(boolean admin) {
        initComponents();
        setLocationRelativeTo(null);
        this.setTitle("Principal");
        Image icon= new ImageIcon(getClass().getResource("/img/favicon.png")).getImage();
        this.setIconImage(icon);
        this.setResizable(false);
        inicializaRuta();
        inicializaModifica();
        inicializaUsuarios();
        if (!admin) {
            PanelGral.remove(PanelModificar);
            PanelGral.remove(PanelUsuarios);
        }
        this.isAdmin1=admin;
        this.txtInfo.append("Universidad Politécnica de San Luis Potosí\n" +
        "Programación III\n" +
        "Grupo T22A\n" +
        "Otoño 2020	20203S\n" +
        "MTI. Guadalupe Ledesma Ramos\n" +
        "Juan Pablo Orellana Franco	180997\n" +
        "Aarón Mishael Carrizal Méndez	180816");
        this.txtInfo.setEditable(false);
        
    }

    public Principal() {
        initComponents();
    }

    public void inicializaRuta() {
        String sid;
        combo.removeAllItems();
        e.bajaBase(listaEmpleados);
        for (int i = 0; i < listaEmpleados.size(); i++) {
            sid = String.valueOf(listaEmpleados.get(i).getId());
            combo.addItem(sid);
        }
        txtSalida.setEditable(false);
        p.bajaBase(listaPaquetes);
        String cad = "";
        p.bajaBase(listaPaquetes);
        this.txtSalida.setText("ID\tDirX\tDirY\tID Empleado\n");
        int idB = Integer.parseInt(String.valueOf(combo.getSelectedItem()));
        getAsignados(idB, paquetesAEntregar);
        for (Paquete paq : paquetesAEntregar) {
            cad = String.valueOf(paq.getId()) + "\t" + String.valueOf(paq.getDx()) + "\t" + String.valueOf(paq.getDy()) + "\t" + String.valueOf(paq.getIdEmpleado());
            this.txtSalida.append(cad + "\n");
        }
        if (paquetesAEntregar.size() == 0) {
            this.txtSalida.setText("No ha sido asignado ningún paquete al empleado " + String.valueOf(combo.getSelectedItem()) + ".");
        }

    }

    public void inicializaModifica() {
        txtSalidaEmp.setEditable(false);
        txtSalidaPaq.setEditable(false);
        p.bajaBase(listaPaquetes);
        String cad = "";
        this.txtSalidaPaq.setText("ID\tDirX\tDirY\tID Empleado\n");
        for (Paquete paq : listaPaquetes) {
            cad = String.valueOf(paq.getId()) + "\t" + String.valueOf(paq.getDx()) + "\t" + String.valueOf(paq.getDy()) + "\t" + String.valueOf(paq.getIdEmpleado());
            this.txtSalidaPaq.append(cad + "\n");
        }
        e.bajaBase(listaEmpleados);
        String cad2 = "";
        this.txtSalidaEmp.setText("ID\tNombre\tDirX\tDirY\tTelefono\tCorreo\n");
        for (Empleado emp : listaEmpleados) {
            cad2 = String.valueOf(emp.getId()) + "\t" + emp.getNombre() + "\t" + String.valueOf(emp.getDx()) + "\t" + String.valueOf(emp.getDy()) + "\t" + String.valueOf(emp.getTelefono()) + "\t" + emp.getCorreo();
            this.txtSalidaEmp.append(cad2 + "\n");
        }
        
        try {
            Image img = ImageIO.read(getClass().getResource("/img/refresh.png"));
            btnActualizar.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void inicializaUsuarios() {
        txtSalidaUs.setEditable(false);
        us.bajaBase(listaUsuarios);
        String cad = "";
        this.txtSalidaUs.setText("Nombre\tContraseña\tAdmin\n");
        for (Usuario us : listaUsuarios) {
            cad = us.getNombre() + "\t" + String.valueOf(us.getContra()) + "\t" + String.valueOf(us.isAdmin());
            this.txtSalidaUs.append(cad + "\n");
        }

        String sid;
        comboModUs.removeAllItems();
        comboElimUs.removeAllItems();
        comboAdminMod.removeAllItems();
        comboAdminMod.addItem("true");
        comboAdminMod.addItem("false");
        comboAdminAg.removeAllItems();
        comboAdminAg.addItem("true");
        comboAdminAg.addItem("false");
        us.bajaBase(listaUsuarios);
        for (int i = 0; i < listaUsuarios.size(); i++) {
            sid = String.valueOf(listaUsuarios.get(i).getNombre());
            comboModUs.addItem(sid);
            comboElimUs.addItem(sid);
        }
    }

    public void getAsignados(int idEmpleado, ArrayList<Paquete> paquetesAEntregar) {
        p.bajaBase(listaPaquetes);
        paquetesAEntregar.removeAll(paquetesAEntregar);
        for (Paquete paq : listaPaquetes) {
            if (paq.getIdEmpleado() == idEmpleado) {
                paquetesAEntregar.add(paq);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelGral = new javax.swing.JTabbedPane();
        PanelRuta = new javax.swing.JPanel();
        combo = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSalida = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnMuestraRuta = new javax.swing.JButton();
        panelDibujo = new javax.swing.JScrollPane();
        PanelModificar = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnAgregarEmp = new javax.swing.JButton();
        btnModificarEmp = new javax.swing.JButton();
        btnEliminarEmp = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSalidaPaq = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtSalidaEmp = new javax.swing.JTextArea();
        btnAgregarPaq = new javax.swing.JButton();
        btnModificarPaq = new javax.swing.JButton();
        btnEliminarPaq = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        PanelUsuarios = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtSalidaUs = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        comboModUs = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtContraMod = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        comboAdminMod = new javax.swing.JComboBox<>();
        comboElimUs = new javax.swing.JComboBox<>();
        btnEliminarUs = new javax.swing.JButton();
        btnModificarUs = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNombreAg = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtContraAg = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        comboAdminAg = new javax.swing.JComboBox<>();
        btnAgregarUs = new javax.swing.JButton();
        PanelConfiguracion = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        btnOscuro = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtInfo = new javax.swing.JTextArea();
        botona = new javax.swing.JButton();
        txtMilis = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelGral.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                PanelGralStateChanged(evt);
            }
        });

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });

        txtSalida.setColumns(20);
        txtSalida.setRows(5);
        jScrollPane2.setViewportView(txtSalida);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Paquetes registrados:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mostrando ruta de empleado:");

        btnMuestraRuta.setText("Muestra ruta");
        btnMuestraRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuestraRutaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelRutaLayout = new javax.swing.GroupLayout(PanelRuta);
        PanelRuta.setLayout(PanelRutaLayout);
        PanelRutaLayout.setHorizontalGroup(
            PanelRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRutaLayout.createSequentialGroup()
                .addGroup(PanelRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelRutaLayout.createSequentialGroup()
                        .addContainerGap(55, Short.MAX_VALUE)
                        .addGroup(PanelRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelRutaLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                .addGap(41, 41, 41)
                                .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(110, 110, 110))
                            .addGroup(PanelRutaLayout.createSequentialGroup()
                                .addComponent(panelDibujo, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(PanelRutaLayout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(btnMuestraRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(PanelRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        PanelRutaLayout.setVerticalGroup(
            PanelRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRutaLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(PanelRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelRutaLayout.createSequentialGroup()
                        .addComponent(panelDibujo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMuestraRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
                .addGap(48, 48, 48))
        );

        PanelGral.addTab("Ruta", PanelRuta);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Lista de Empleados:");

        btnAgregarEmp.setText("Agregar");
        btnAgregarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEmpActionPerformed(evt);
            }
        });

        btnModificarEmp.setText("Modificar");
        btnModificarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarEmpActionPerformed(evt);
            }
        });

        btnEliminarEmp.setText("Eliminar");
        btnEliminarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEmpActionPerformed(evt);
            }
        });

        txtSalidaPaq.setColumns(20);
        txtSalidaPaq.setRows(5);
        jScrollPane3.setViewportView(txtSalidaPaq);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Lista de Paquetes:");

        txtSalidaEmp.setColumns(20);
        txtSalidaEmp.setRows(5);
        jScrollPane4.setViewportView(txtSalidaEmp);

        btnAgregarPaq.setText("Agregar");
        btnAgregarPaq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPaqActionPerformed(evt);
            }
        });

        btnModificarPaq.setText("Modificar");
        btnModificarPaq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarPaqActionPerformed(evt);
            }
        });

        btnEliminarPaq.setText("Eliminar");
        btnEliminarPaq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPaqActionPerformed(evt);
            }
        });

        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelModificarLayout = new javax.swing.GroupLayout(PanelModificar);
        PanelModificar.setLayout(PanelModificarLayout);
        PanelModificarLayout.setHorizontalGroup(
            PanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelModificarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelModificarLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(btnAgregarEmp)
                        .addGap(69, 69, 69)
                        .addComponent(btnModificarEmp)
                        .addGap(77, 77, 77)
                        .addComponent(btnEliminarEmp))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(PanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(PanelModificarLayout.createSequentialGroup()
                        .addComponent(btnAgregarPaq)
                        .addGap(65, 65, 65)
                        .addComponent(btnModificarPaq)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarPaq))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelModificarLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizar))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );
        PanelModificarLayout.setVerticalGroup(
            PanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelModificarLayout.createSequentialGroup()
                .addGroup(PanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelModificarLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(PanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addGroup(PanelModificarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnActualizar)))
                .addGap(18, 18, 18)
                .addGroup(PanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(PanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregarPaq)
                        .addComponent(btnModificarPaq)
                        .addComponent(btnEliminarPaq))
                    .addGroup(PanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregarEmp)
                        .addComponent(btnModificarEmp)
                        .addComponent(btnEliminarEmp)))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        PanelGral.addTab("Modificar", PanelModificar);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Usuarios registrados:");

        txtSalidaUs.setColumns(20);
        txtSalidaUs.setRows(5);
        jScrollPane5.setViewportView(txtSalidaUs);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Eliminar usuario");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Modificar usuario");

        comboModUs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboModUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboModUsActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Nombre:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Contraseña:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("¿Es Admin?");

        comboAdminMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboElimUs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnEliminarUs.setText("Eliminar");
        btnEliminarUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsActionPerformed(evt);
            }
        });

        btnModificarUs.setText("Modificar");
        btnModificarUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarUsActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Agregar usuario");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Nombre:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Contraseña:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("¿Es Admin?");

        comboAdminAg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAgregarUs.setText("Agregar");
        btnAgregarUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelUsuariosLayout = new javax.swing.GroupLayout(PanelUsuarios);
        PanelUsuarios.setLayout(PanelUsuariosLayout);
        PanelUsuariosLayout.setHorizontalGroup(
            PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUsuariosLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelUsuariosLayout.createSequentialGroup()
                        .addComponent(comboElimUs, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(153, 153, 153)
                        .addComponent(btnEliminarUs))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelUsuariosLayout.createSequentialGroup()
                        .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreAg, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContraAg, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(79, 79, 79)
                        .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboAdminAg, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregarUs, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelUsuariosLayout.createSequentialGroup()
                        .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtContraMod, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(comboModUs, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68)
                        .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboAdminMod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnModificarUs, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))))
                .addContainerGap(114, Short.MAX_VALUE))
        );

        PanelUsuariosLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {comboElimUs, comboModUs});

        PanelUsuariosLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnEliminarUs, btnModificarUs});

        PanelUsuariosLayout.setVerticalGroup(
            PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUsuariosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelUsuariosLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelUsuariosLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(comboAdminMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelUsuariosLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboModUs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContraMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarUs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboElimUs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarUs))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelUsuariosLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(comboAdminAg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelUsuariosLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombreAg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContraAg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarUs))
                .addGap(52, 52, 52))
        );

        PanelGral.addTab("Usuarios", PanelUsuarios);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Cambiar tema");

        btnOscuro.setText("Cambiar");
        btnOscuro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOscuroActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Información:");

        txtInfo.setColumns(20);
        txtInfo.setRows(5);
        jScrollPane1.setViewportView(txtInfo);

        botona.setText("OK");
        botona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonaActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Duración de dibujo de ruta (0 - 1000)");

        javax.swing.GroupLayout PanelConfiguracionLayout = new javax.swing.GroupLayout(PanelConfiguracion);
        PanelConfiguracion.setLayout(PanelConfiguracionLayout);
        PanelConfiguracionLayout.setHorizontalGroup(
            PanelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelConfiguracionLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addGroup(PanelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(botona, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMilis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnOscuro, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                .addGroup(PanelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelConfiguracionLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(205, 205, 205))
        );
        PanelConfiguracionLayout.setVerticalGroup(
            PanelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelConfiguracionLayout.createSequentialGroup()
                .addContainerGap(132, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addGroup(PanelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelConfiguracionLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(btnOscuro)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(txtMilis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(botona)))
                .addGap(160, 160, 160))
        );

        PanelGral.addTab("Configuración", PanelConfiguracion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelGral)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelGral)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEmpActionPerformed
        new NuevosEmpleadosGUI().setVisible(true);
    }//GEN-LAST:event_btnAgregarEmpActionPerformed

    private void btnModificarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarEmpActionPerformed
        new EditarEmpleadosGUI().setVisible(true);
    }//GEN-LAST:event_btnModificarEmpActionPerformed

    private void btnEliminarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEmpActionPerformed
        new BorrarEmpleadosGUI().setVisible(true);
    }//GEN-LAST:event_btnEliminarEmpActionPerformed

    private void btnAgregarPaqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPaqActionPerformed
        new NuevosPaquetesGUI().setVisible(true);
    }//GEN-LAST:event_btnAgregarPaqActionPerformed

    private void btnModificarPaqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarPaqActionPerformed
        new EditarPaquetesGUI().setVisible(true);
    }//GEN-LAST:event_btnModificarPaqActionPerformed

    private void btnEliminarPaqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPaqActionPerformed
        new BorrarPaquetesGUI().setVisible(true);
    }//GEN-LAST:event_btnEliminarPaqActionPerformed

    private void PanelGralStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_PanelGralStateChanged
        inicializaRuta();
        inicializaModifica();
        inicializaUsuarios();
    }//GEN-LAST:event_PanelGralStateChanged

    private void comboModUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboModUsActionPerformed
        try {
            int idx = Integer.parseInt(String.valueOf(comboModUs.getSelectedItem()));
            txtContraMod.setText(listaUsuarios.get(idx).getContra());
        } catch (java.lang.NumberFormatException e) {
        }
    }//GEN-LAST:event_comboModUsActionPerformed

    private void btnModificarUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarUsActionPerformed
        String nombreN, contra;
        boolean admin;
        try {
            contra = txtContraMod.getText();
            nombreN = String.valueOf(comboModUs.getSelectedItem());
            if (comboAdminMod.getSelectedIndex() == 0) {
                admin = true;
            } else {
                admin = false;
            }
            if(nombreN.equals("")||contra.equals("")){
                throw new ME1("Ningún campo puede estar vacío.");
            }
            us.modificaUs(nombreN, contra, admin);
            us.bajaBase(listaUsuarios);
            this.inicializaUsuarios();
        } catch (ME1 ex) {
        }
        inicializaUsuarios();

    }//GEN-LAST:event_btnModificarUsActionPerformed

    private void btnAgregarUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUsActionPerformed
        String nombreM, contraM;
        boolean adminM;
        nombreM = txtNombreAg.getText();
        contraM = txtContraAg.getText();
        int a = comboAdminAg.getSelectedIndex();
        if (a == 0) {
            adminM = true;
        } else {
            adminM = false;
        }
        try{
            if(nombreM.equals("")||contraM.equals("")){
                throw new ME1("Ningún campo puede estar vacío.");
            }
            for(Usuario prueba: listaUsuarios){
                if(prueba.getNombre().equals(nombreM)){
                    throw new ME1("No puedes crear 2 usuarios con el mismo nombre.");
                }
            }
            us.nuevoUs(nombreM, contraM, adminM);
        }catch(ME1 e){
           
        }
        
        //us.bajaBase(listaUsuarios);
        this.inicializaUsuarios();
    }//GEN-LAST:event_btnAgregarUsActionPerformed

    private void btnEliminarUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsActionPerformed
        String nombre;
        nombre = String.valueOf(comboElimUs.getSelectedItem());
        int r = JOptionPane.showConfirmDialog(null, "¿Estas seguro?","Confirmación", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            us.eliminaUs(nombre);
            }
        //us.bajaBase(listaUsuarios);
        this.inicializaUsuarios();
    }//GEN-LAST:event_btnEliminarUsActionPerformed

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
        try {
            String cad = "";
            p.bajaBase(listaPaquetes);
            this.txtSalida.setText("");
            this.txtSalida.setText("ID\tDirX\tDirY\tID Empleado\n");
            int idB = Integer.parseInt(String.valueOf(combo.getSelectedItem()));
            getAsignados(idB, paquetesAEntregar);
            for (Paquete paq : paquetesAEntregar) {
                cad = String.valueOf(paq.getId()) + "\t" + String.valueOf(paq.getDx()) + "\t" + String.valueOf(paq.getDy()) + "\t" + String.valueOf(paq.getIdEmpleado());
                this.txtSalida.append(cad + "\n");
            }
            if (paquetesAEntregar.size() == 0) {
                this.txtSalida.setText("No ha sido asignado ningún paquete al empleado " + String.valueOf(combo.getSelectedItem()) + ".");
            }
        } catch (NumberFormatException e) {
        }
    }//GEN-LAST:event_comboActionPerformed

    private void btnMuestraRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuestraRutaActionPerformed
        int ancho = 20;
        CaminosMinimos ruta = new CaminosMinimos();
        ArrayList<int[]> caminos = new ArrayList<>();
        String laf = String.valueOf(UIManager.getLookAndFeel());
        
        
        Paquete base = new Paquete();
        base.setDx(0);
        base.setDy(0);
        base.setId(999);
        base.setIdEmpleado(paquetesAEntregar.get(0).getIdEmpleado());
        paquetesAEntregar.add(0, base);
        
        caminos = ruta.algoritmoFloyd(paquetesAEntregar, 1);
        Graphics papel = panelDibujo.getGraphics();
        papel.setColor(panelDibujo.getBackground());
        papel.fillRect(0, 0, 500, 500);
        
//        System.out.println("Tamaño a entregar: "+paquetesAEntregar.size());
//        System.out.println("Tamaño caminos: "+caminos.size());
        
        //pinta todas las
        if(laf.equals("[FlatLaf Dark Look and Feel - com.formdev.flatlaf.FlatDarkLaf]")){
            papel.setColor(Color.black);
        }else{
            papel.setColor(Color.GRAY);
        }
        
        for (int i = 0; i < paquetesAEntregar.size(); i++) {
            for (int j = 0; j < paquetesAEntregar.size(); j++) {
                int x1 = paquetesAEntregar.get(i).getDx()+ (ancho / 2);
                int y1 = paquetesAEntregar.get(i).getDy()+ (ancho / 2);
                int x2 = paquetesAEntregar.get(j).getDx()+ (ancho / 2);
                int y2 = paquetesAEntregar.get(j).getDy()+ (ancho / 2);
                papel.drawLine(x1, y1, x2, y2);
            }
            
        }
        
        
        //pinta una ruta
        for (int i = 0; i < paquetesAEntregar.size(); i++) {
            papel.setColor(Color.LIGHT_GRAY);
            papel.fillOval(paquetesAEntregar.get(i).getDx(), paquetesAEntregar.get(i).getDy(), ancho, ancho);
            try {
                Thread.sleep(this.milis);
            } catch (InterruptedException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(laf.equals("[FlatLaf Dark Look and Feel - com.formdev.flatlaf.FlatDarkLaf]")){
                papel.setColor(Color.ORANGE);
            }else{
                papel.setColor(Color.BLUE);
            }
            if (i == paquetesAEntregar.size() - 1) {
                papel.drawLine(paquetesAEntregar.get(i).getDx() + (ancho / 2), paquetesAEntregar.get(i).getDy() + (ancho / 2), paquetesAEntregar.get(0).getDx()+(ancho/2), paquetesAEntregar.get(0).getDy()+(ancho/2));
            } else {
                papel.drawLine(paquetesAEntregar.get(i).getDx() + (ancho / 2), paquetesAEntregar.get(i).getDy() + (ancho / 2), paquetesAEntregar.get(i + 1).getDx() + (ancho / 2), paquetesAEntregar.get(i + 1).getDy() + (ancho / 2));
            }
        }
    }//GEN-LAST:event_btnMuestraRutaActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        inicializaRuta();
        inicializaModifica();
        inicializaUsuarios();    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnOscuroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOscuroActionPerformed
        String laf = String.valueOf(UIManager.getLookAndFeel());
        if(laf.equals("[FlatLaf Dark Look and Feel - com.formdev.flatlaf.FlatDarkLaf]")){
            try {
                UIManager.setLookAndFeel( new FlatLightLaf() );
            } catch( Exception ex ) {
                System.err.println( "Failed to initialize LaF" );
            }
        }else{
            try {
                UIManager.setLookAndFeel( new FlatDarkLaf() );
            } catch( Exception ex ) {
                 System.err.println( "Failed to initialize LaF" );
            }
        }
        this.dispose();
        new Principal(this.isAdmin1).setVisible(true);
    }//GEN-LAST:event_btnOscuroActionPerformed

    private void botonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonaActionPerformed
        easter++;
        if(easter>=3){
            new EasterEgg().setVisible(true);
            System.out.println("Agradecido con el de arriba");
            easter = 0;
        }
        try{
            this.milis = Integer.parseInt(txtMilis.getText());
            if(this.milis<0){
                this.milis = 100;
                throw new ME1("Introduce un numero positivo");
            }
            if(this.milis>1000){
                this.milis = 100;
                throw new ME1("No quieres esperar tanto tiempo, creeme.");
            }
        }catch(NumberFormatException e){
            
        }catch(ME1 e){
            
        }
        
    }//GEN-LAST:event_botonaActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelConfiguracion;
    private javax.swing.JTabbedPane PanelGral;
    private javax.swing.JPanel PanelModificar;
    private javax.swing.JPanel PanelRuta;
    private javax.swing.JPanel PanelUsuarios;
    private javax.swing.JButton botona;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregarEmp;
    private javax.swing.JButton btnAgregarPaq;
    private javax.swing.JButton btnAgregarUs;
    private javax.swing.JButton btnEliminarEmp;
    private javax.swing.JButton btnEliminarPaq;
    private javax.swing.JButton btnEliminarUs;
    private javax.swing.JButton btnModificarEmp;
    private javax.swing.JButton btnModificarPaq;
    private javax.swing.JButton btnModificarUs;
    private javax.swing.JButton btnMuestraRuta;
    private javax.swing.JButton btnOscuro;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JComboBox<String> comboAdminAg;
    private javax.swing.JComboBox<String> comboAdminMod;
    private javax.swing.JComboBox<String> comboElimUs;
    private javax.swing.JComboBox<String> comboModUs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JScrollPane panelDibujo;
    private javax.swing.JTextField txtContraAg;
    private javax.swing.JTextField txtContraMod;
    private javax.swing.JTextArea txtInfo;
    private javax.swing.JTextField txtMilis;
    private javax.swing.JTextField txtNombreAg;
    private javax.swing.JTextArea txtSalida;
    private javax.swing.JTextArea txtSalidaEmp;
    private javax.swing.JTextArea txtSalidaPaq;
    private javax.swing.JTextArea txtSalidaUs;
    // End of variables declaration//GEN-END:variables
}
