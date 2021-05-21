/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jard;

import cjb.ci.Validaciones;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
///
/**
 *
 * @author arele
 */
public class AñadeUsuario extends javax.swing.JFrame
{
    String Admi;
    DefaultTableModel model = new DefaultTableModel(){
      @Override  
        public  boolean isCellEditable (int row , int colum){
            return false ;
        }
    };
    private TableRowSorter<TableModel>tr;
    Connection con= ConexionBD.getConexion();
    
    
    
    /**
     * Creates new form AñadeUsuario
     */
    public AñadeUsuario()
    {
        initComponents();
        
        
        model.addColumn("Tipo Usuario");
        model.addColumn("Nombre Usuario");
        model.addColumn("Contraseña");
        model.addColumn("Nombre");
        jTablaUser.setModel(model);
        mostrarBD();
    }
    private void mostrarBD() { //FUNCION QUE MUESTRA DATOS de BD EN JTABLA DE CLIENTES 
     ResultSet rs = ConexionBD.getTabla("select tipo_user, nombre_user, contraseña, nombre from usuarios");
        try {
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("tipo_user"), rs.getString("nombre_user"), rs.getString("contraseña"), rs.getString("nombre")});
            }
            jTablaUser.setModel(model);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
public void Limpiar() {
       tfUsuario.setText("");
        tfContrasenia.setText("");
        tfNombreC.setText("");
    }
public int compruebavacio() { //Funcion que comprueba que no existan espacios en blanco
        int x = 0;
        String vacio = "";

        for (int i = 0; i < 40; i++) {  //comprobar que no este en blanco
            vacio = vacio + " ";
            if (tfNombreC.getText().equals(vacio)) {
                x = 0;
                JOptionPane.showMessageDialog(null, "No se aceptan espacios en blanco");
                break;
            } else {
                x = 1;
            }
        }
        return x;
    }
 void CargarTabla(String valor) {
        try {
            String[] titulos = {"Tipo Usuario", "Usuario", "Contraseña", "Nombre"};

            String[] usuarios = new String[4];
            model = new DefaultTableModel(null, titulos);
            //busca cualquier dato similar a Valor(). busca datos que coincidan con esos caracteres.
            String cons = "select * from usuarios WHERE CONCAT (tipo_user, nombre_user, contraseña, nombre) LIKE '%" + valor + "%'";
            Statement st = con.createStatement();//sentencia (SQL).
            ResultSet rs = st.executeQuery(cons);//ejecuta la consulta,,diferente a executeUpdate que sirve para A-B-M.
            while (rs.next()) {//se agregan datos a la tabla
                usuarios[0] = rs.getString("tipo_user");
                usuarios[1] = rs.getString("nombre_user");
                usuarios[2] = rs.getString("contraseña");
                usuarios[3] = rs.getString("nombre");

                model.addRow(usuarios);
            }
            jTablaUser.setModel(model);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage());//error por pantalla.
        }
    }
  public void Ingresa() {
          String[] Ingresar = new String[4];
        Ingresar[0] = jCBAdmin.getSelectedItem().toString();
        Ingresar[1] = tfUsuario.getText();
        Ingresar[2] = tfContrasenia.getText();
        Ingresar[3] = tfNombreC.getText();
        model.addRow(Ingresar);
        Limpiar();
    }
  public void Registra() {
        int a = 0;
        if (tfUsuario.getText().isEmpty() || tfContrasenia.getText().isEmpty() || tfNombreC.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos");
        } else {
            a = compruebavacio();
        }
        if (a == 1) {
            JOptionPane.showMessageDialog(null, "¡Nuevo usuario registrado!");
            try {
                Connection con = ConexionBD.getConexion();
                PreparedStatement pst = con.prepareStatement("insert into usuarios value (?,?,?,?,?)");
                pst.setString(1, "0");
                pst.setString(2, jCBAdmin.getSelectedItem().toString());//Aquí hace la selección del ComboBox
                pst.setString(3, tfUsuario.getText().trim());
                pst.setString(4, tfContrasenia.getText().trim());
                pst.setString(5, tfNombreC.getText().trim());
                pst.executeUpdate();

                Ingresa();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jCBAdmin = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        tfUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfContrasenia = new javax.swing.JTextField();
        tfNombreC = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        btnModifica = new javax.swing.JButton();
        btnElimina = new javax.swing.JButton();
        btnLimpia = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaUser = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(224, 247, 250));

        jLabel1.setText("TIPO DE USUARIO");

        jCBAdmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Usuario", " " }));

        jLabel2.setText("USUARIO");

        tfUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfUsuarioKeyTyped(evt);
            }
        });

        jLabel3.setText("CONTRASEÑA");

        jLabel4.setText("NOMBRE COMPLETO");

        tfContrasenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfContraseniaActionPerformed(evt);
            }
        });
        tfContrasenia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfContraseniaKeyTyped(evt);
            }
        });

        tfNombreC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNombreCKeyTyped(evt);
            }
        });

        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnModifica.setText("MODIFICAR");
        btnModifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificaActionPerformed(evt);
            }
        });

        btnElimina.setText("ELIMINAR");
        btnElimina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminaActionPerformed(evt);
            }
        });

        btnLimpia.setText("LIMPIAR");
        btnLimpia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiaActionPerformed(evt);
            }
        });

        jTablaUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tipo Usuario", "Nombre Usuario", "Contraseña", "Nombre Completo"
            }
        ));
        jTablaUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablaUser);

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(btnModifica, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(84, 84, 84)
                                        .addComponent(jLabel1)
                                        .addGap(26, 26, 26)
                                        .addComponent(jCBAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(74, 74, 74)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel3))
                                                .addGap(27, 27, 27)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(tfUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                                    .addComponent(tfContrasenia)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(56, 56, 56)))
                        .addComponent(btnElimina, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnLimpia, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jCBAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(tfContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLimpia, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btnElimina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModifica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(55, 55, 55)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButton1)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfContraseniaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_tfContraseniaActionPerformed
    {//GEN-HEADEREND:event_tfContraseniaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfContraseniaActionPerformed

    private void btnEliminaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnEliminaActionPerformed
    {//GEN-HEADEREND:event_btnEliminaActionPerformed
       String name = tfUsuario.getText();
        int dialog = JOptionPane.YES_NO_OPTION;
        String sql = "DELETE from usuarios WHERE nombre_user='" + name + "'";

        if (tfUsuario.getText().isEmpty() || tfContrasenia.getText().isEmpty() || tfNombreC.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado un usuario para eliminar");
        } else {
            int result = JOptionPane.showConfirmDialog(null, "Los datos del USUARIO serán borrados de forma permanente\n\n                              ¿Desea continuar?", "Advertencia", dialog);
            if (result == 0) {
                try {
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "El usuario ha sido eliminado");

                    CargarTabla("");
                    Limpiar();
                } catch (HeadlessException | SQLException e) {
                    System.out.println("Error" + e);
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnEliminaActionPerformed

    private void btnModificaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnModificaActionPerformed
    {//GEN-HEADEREND:event_btnModificaActionPerformed
           int SeleccionFila = jTablaUser.getSelectedRow();

        String tipouser = (jTablaUser.getValueAt(SeleccionFila, 0).toString());
        String nombreus = (jTablaUser.getValueAt(SeleccionFila, 1).toString());
        String contraseña = (jTablaUser.getValueAt(SeleccionFila, 2).toString());
        String nombre = (jTablaUser.getValueAt(SeleccionFila, 3).toString());
        String idconsul = "";
        if(SeleccionFila>=0){
        try {

            String consul = "SELECT * from usuarios WHERE tipo_user='" + tipouser + "' and nombre_user='" + nombreus + "' and contraseña='" + contraseña + "' and nombre='" + nombre + "'";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(consul);
            while (rs.next()) {

            }
            idconsul = rs.getString("id_cl");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String nombrecomp = tfNombreC.getText();
        String sql = "UPDATE usuarios SET tipo_user='" + jCBAdmin.getSelectedItem().toString() + "', nombre_user='" + tfUsuario.getText()
                + "', contraseña='" + tfContrasenia.getText() + "', nombre='" + tfNombreC.getText() + "' WHERE nombre='" + nombrecomp + "'";

        compruebavacio();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Usuario modificado de forma correcta");

            CargarTabla("");
            Limpiar();
        } catch (HeadlessException | SQLException e) {
            System.out.println("Error" + e);
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }else{
            JOptionPane.showMessageDialog(null, "Porfavor seleccione un usuario para modificar");
        }
                                               
    }//GEN-LAST:event_btnModificaActionPerformed
int filas;
    private void jTablaUserMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTablaUserMouseClicked
    {//GEN-HEADEREND:event_jTablaUserMouseClicked
        int SeleccionFila = jTablaUser.getSelectedRow();
        filas = SeleccionFila;

        String tipouser = (jTablaUser.getValueAt(SeleccionFila, 0).toString());
        String nombreus = (jTablaUser.getValueAt(SeleccionFila, 1).toString());
        String contraseña = (jTablaUser.getValueAt(SeleccionFila, 2).toString());
        String nombre = (jTablaUser.getValueAt(SeleccionFila, 3).toString());
        try {

            String consulta = "SELECT * from usuarios WHERE tipo_user='" + tipouser + "' and nombre_user='" + nombreus + "' and contraseña='" + contraseña + "' and nombre='" + nombre + "'";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                tipouser = rs.getString("tipo_user");
                nombreus = rs.getString("nombre_user");
                contraseña = rs.getString("contraseña");
                nombre = rs.getString("nombre");
            }

            jCBAdmin.setSelectedItem(tipouser);
            tfUsuario.setText(nombreus);
            tfContrasenia.setText(contraseña);
            tfNombreC.setText(nombre);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jTablaUserMouseClicked

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnRegistrarActionPerformed
    {//GEN-HEADEREND:event_btnRegistrarActionPerformed
Registra();        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnLimpiaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnLimpiaActionPerformed
    {//GEN-HEADEREND:event_btnLimpiaActionPerformed
Limpiar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
new InicioAdmi(Admi).setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tfUsuarioKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_tfUsuarioKeyTyped
    {//GEN-HEADEREND:event_tfUsuarioKeyTyped
        if (tfUsuario.getText().length()==10)
        {
            evt.consume();
        }else{
            Validaciones.validaAlfanumerico(evt);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tfUsuarioKeyTyped

    private void tfContraseniaKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_tfContraseniaKeyTyped
    {//GEN-HEADEREND:event_tfContraseniaKeyTyped
        if (tfContrasenia.getText().length()==10
)        {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tfContraseniaKeyTyped

    private void tfNombreCKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_tfNombreCKeyTyped
    {//GEN-HEADEREND:event_tfNombreCKeyTyped
        if (tfNombreC.getText().length()==30)
        {
            evt.consume();
        }else{
            Validaciones.validaAlfabeticos(evt);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNombreCKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(AñadeUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(AñadeUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(AñadeUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(AñadeUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new AñadeUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnElimina;
    private javax.swing.JButton btnLimpia;
    private javax.swing.JButton btnModifica;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCBAdmin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablaUser;
    private javax.swing.JTextField tfContrasenia;
    private javax.swing.JTextField tfNombreC;
    private javax.swing.JTextField tfUsuario;
    // End of variables declaration//GEN-END:variables
}
