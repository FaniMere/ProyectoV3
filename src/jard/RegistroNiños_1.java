/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jard;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vicOMG99
 */
public class RegistroNiños_1 extends javax.swing.JFrame {

   DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) { //Método para hacer NO editable la tabla
            return false;
        }
    };
   Connection con = ConexionBD.getConexion();
    public RegistroNiños_1() {
        initComponents();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NOMBRE");
        model.addColumn("APELIDO PATERNO");
        model.addColumn("APELLIDO MATERNO");
        model.addColumn("Matricula"); 
        jTabDatos.setModel(model);
        
        mostrarBD();
        setLocationRelativeTo(null);
    }

    private void mostrarBD() { //FUNCION QUE MUESTRA DATOS de BD EN JTABLA DE PADRES 
        ResultSet rs = ConexionBD.getTabla("select nombre_alum, apellido_p, apellido_m, matricula");
        try {
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("nombre_alum"), rs.getString("apellido_p"), rs.getString("apellido_m"),
                    rs.getString("matricula_alum")});
                // model.addRow(new Object[]{rs.getString("tipo_usuario"), rs.getString("nombre_usuario"), rs.getString("contraseña"), rs.getString("nombre_comp")});
            }
            jTabDatos.setModel(model);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void Limpiar() {
        
       
        txtNombreAlum.setText("");
        txtAPT.setText("");
        txtApMat.setText("");
        txtMatri.setText("");
        txtIdProf.setText("");
        txtId_Us.setText("");

    }

   void CargarTabla(String valor) {
        try {
            String[] titulos = { "nombre_alum", "apellido_p", "apellido_m", "matricula_alumn", "id_alumno"};

            String[] padres = new String[4];
            model = new DefaultTableModel(null, titulos);
            //busca cualquier dato similar a Valor(). busca datos que coincidan con esos caracteres.
            String cons = "select * from alumnos WHERE CONCAT (nombre_alum, apellido_p, apellido_m, matricula_alum, id_alumno, calificacion) LIKE '%" + valor + "%'";
            Statement st = con.createStatement();//sentencia (SQL).
            ResultSet rs = st.executeQuery(cons);//ejecuta la consulta,,diferente a executeUpdate que sirve para A-B-M.
            while (rs.next()) {//se agregan datos a la tabla
            
                padres[0] = rs.getString("nombre_alum");
                padres[1] = rs.getString("apellido_p");
                padres[2] = rs.getString("apellido_m");
                padres[3] = rs.getString("matricula_alum");
               
            
                model.addRow(padres);
            }
            jTabDatos.setModel(model);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage());//error por pantalla.
        }
    }

    public void Ingresa() {
        String[] Ingresar = new String[4];
        Ingresar[0] = txtNombreAlum.getText();
        Ingresar[1] = txtApMat.getText();
        Ingresar[2] = txtAPT.getText();
        Ingresar[3] =  txtMatri.getText();
        
  
        
        model.addRow(Ingresar);
        Limpiar();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRNiños = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombreAlum = new javax.swing.JTextField();
        txtAPT = new javax.swing.JTextField();
        txtApMat = new javax.swing.JTextField();
        txtMatri = new javax.swing.JTextField();
        txtCALF = new javax.swing.JTextField();
        btnRegresar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtIdProf = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtId_Us = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTabDatos = new javax.swing.JTable();
        jBRegistar = new javax.swing.JButton();
        jBElimina = new javax.swing.JButton();
        jBModificar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelRNiños.setBackground(new java.awt.Color(224, 247, 250));
        panelRNiños.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel1.setText("Registro Alumnos");
        panelRNiños.add(jLabel1);
        jLabel1.setBounds(510, 20, 205, 41);

        jLabel2.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 0));
        jLabel2.setText("Nombre Alumno:");
        panelRNiños.add(jLabel2);
        jLabel2.setBounds(48, 94, 160, 30);

        jLabel3.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 0, 0));
        jLabel3.setText("Apellido Paterno:");
        panelRNiños.add(jLabel3);
        jLabel3.setBounds(520, 100, 160, 30);

        jLabel4.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 0, 0));
        jLabel4.setText("Apellido Materno:");
        panelRNiños.add(jLabel4);
        jLabel4.setBounds(36, 209, 160, 30);

        jLabel5.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 0, 0));
        jLabel5.setText("Matricula:");
        panelRNiños.add(jLabel5);
        jLabel5.setBounds(89, 307, 120, 30);

        jLabel6.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 0, 0));
        jLabel6.setText("Calificacion:");
        panelRNiños.add(jLabel6);
        jLabel6.setBounds(520, 230, 120, 20);
        panelRNiños.add(txtNombreAlum);
        txtNombreAlum.setBounds(164, 133, 204, 34);
        panelRNiños.add(txtAPT);
        txtAPT.setBounds(600, 150, 204, 38);

        txtApMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApMatActionPerformed(evt);
            }
        });
        panelRNiños.add(txtApMat);
        txtApMat.setBounds(164, 254, 204, 37);
        panelRNiños.add(txtMatri);
        txtMatri.setBounds(164, 363, 204, 36);
        panelRNiños.add(txtCALF);
        txtCALF.setBounds(590, 260, 204, 36);

        btnRegresar.setBackground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("regresar");
        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegresarMouseClicked(evt);
            }
        });
        panelRNiños.add(btnRegresar);
        btnRegresar.setBounds(0, 0, 73, 23);

        jLabel8.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 0, 0));
        jLabel8.setText("id profesor:");
        panelRNiños.add(jLabel8);
        jLabel8.setBounds(930, 110, 170, 30);

        txtIdProf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdProfActionPerformed(evt);
            }
        });
        panelRNiños.add(txtIdProf);
        txtIdProf.setBounds(960, 160, 204, 34);

        jLabel9.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 0, 0));
        jLabel9.setText("id alumno:");
        panelRNiños.add(jLabel9);
        jLabel9.setBounds(930, 240, 170, 30);

        txtId_Us.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtId_UsActionPerformed(evt);
            }
        });
        panelRNiños.add(txtId_Us);
        txtId_Us.setBounds(950, 280, 204, 34);

        jTabDatos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTabDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTabDatos.setGridColor(new java.awt.Color(255, 255, 255));
        jTabDatos.setRowHeight(20);
        jTabDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabDatosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTabDatos);

        panelRNiños.add(jScrollPane2);
        jScrollPane2.setBounds(170, 410, 940, 330);

        jBRegistar.setText("AGREGAR");
        jBRegistar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegistarActionPerformed(evt);
            }
        });
        panelRNiños.add(jBRegistar);
        jBRegistar.setBounds(630, 770, 81, 23);

        jBElimina.setText("ELIMINAR");
        jBElimina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminaActionPerformed(evt);
            }
        });
        panelRNiños.add(jBElimina);
        jBElimina.setBounds(740, 770, 81, 23);

        jBModificar.setText("MODIFICAR");
        jBModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBModificarActionPerformed(evt);
            }
        });
        panelRNiños.add(jBModificar);
        jBModificar.setBounds(870, 770, 91, 30);

        jPanel1.setBackground(new java.awt.Color(9, 9, 68));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 93, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelRNiños, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1253, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRNiños, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtApMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApMatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApMatActionPerformed

    private void btnRegresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseClicked
        Menu_Principal obj = new Menu_Principal();
        obj.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnRegresarMouseClicked

    private void txtIdProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProfActionPerformed

    private void txtId_UsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtId_UsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtId_UsActionPerformed
int filas;
    private void jTabDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabDatosMouseClicked

        int SeleccionFila = jTabDatos.getSelectedRow();
        filas = SeleccionFila;

        String idProf = (jTabDatos.getValueAt(SeleccionFila, 0).toString());
        String idAlum = (jTabDatos.getValueAt(SeleccionFila, 1).toString());
        String nombre = (jTabDatos.getValueAt(SeleccionFila, 2).toString());
        String Ap_pat = (jTabDatos.getValueAt(SeleccionFila, 3).toString());
        String Ap_mat = (jTabDatos.getValueAt(SeleccionFila, 4).toString());
        String calf = (jTabDatos.getValueAt(SeleccionFila, 4).toString());

        String mat = (jTabDatos.getValueAt(SeleccionFila, 5).toString());

        try {

            String consulta = "SELECT * from alumno WHERE id_prof='" + idProf + "'id_alum='" + idAlum + "'and nombre_prof='" + nombre + "' and apellido_p='" + Ap_pat + "'"
            + "' and apellido_m='" + Ap_mat + "'"+ "' and matricula_alumno='" +mat + "'calificacion'"+calf+"'";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                nombre = rs.getString("nombre_prof");
                Ap_pat = rs.getString("apellido_p");
                Ap_mat = rs.getString("apellido_materno");
                mat = rs.getString("matricula_alumn");
                calf = rs.getString("calificacion");

                txtNombreAlum.setText(nombre);
                txtAPT.setText(Ap_pat);
                txtApMat.setText(Ap_mat);
                txtMatri.setText(mat);
                txtIdProf.setText(idAlum);
                txtId_Us.setText(idProf);
                txtCALF.setText(calf);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }//GEN-LAST:event_jTabDatosMouseClicked

    private void jBRegistarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegistarActionPerformed
        Registra();
    }//GEN-LAST:event_jBRegistarActionPerformed

    private void jBEliminaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminaActionPerformed
        String name = txtNombreAlum.getText();
        int dialog = JOptionPane.YES_NO_OPTION;
        String sql = "DELETE from alumno WHERE nombre_alum='" + name + "'";

        if (txtIdProf.getText().isEmpty() || txtId_Us.getText().isEmpty() || txtNombreAlum.getText().isEmpty()
            || txtAPT.getText().isEmpty() || txtApMat.getText().isEmpty()
            || txtMatri.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado un usuario para eliminar");
        } else {
            int result = JOptionPane.showConfirmDialog(null, "Los datos del USUARIO serán borrados de forma permanente\n\n                              ¿Desea continuar?", "Advertencia", dialog);
            if (result == 0) {
                try {
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "El usuario ha sido eliminado");

                    //                    CargarTabla("");
                    Limpiar();
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jBEliminaActionPerformed

    private void jBModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBModificarActionPerformed
        /* int SeleccionFila = jTabDatos.getSelectedRow();

        String idAlum = (jTabDatos.getValueAt(SeleccionFila, 0).toString());
        String idMaestro = (jTabDatos.getValueAt(SeleccionFila, 1).toString());
        String nombre = (jTabDatos.getValueAt(SeleccionFila, 2).toString());
        String Ap_pat = (jTabDatos.getValueAt(SeleccionFila, 3).toString());
        String Ap_mat = (jTabDatos.getValueAt(SeleccionFila, 4).toString());
        String matri = (jTabDatos.getValueAt(SeleccionFila, 5).toString());

        String idconsul = "";
        if(SeleccionFila>=0){
            try {

                String consul =  "SELECT * from padre WHERE id_alumno='" + idAlum + "'id_padre='" + idMaestro + "'and nombre_padre='" + nombre + "' and apellido_p='" + Ap_pat + "'"
                + "' and apellido_m='" + Ap_mat + "'"+ "' and clave_elector='" + matri + "'";

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(consul);
                while (rs.next()) {

                }
                idconsul = rs.getString("id_alumno");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            String nombrecomp = jTNombre.getText();
            String sql = "UPDATE padre SET id_alumno='" + .getSelectedText().toString()+  "'Nombre Profesor'" + txtNombreAlum.getSelectedText().toString()  + "'apellido_p='" + txtAPT.getSelectedText().toString()+
            "'apellido_m='" + txtApMat.getSelectedText().toString() + "'"+ "' clave_elector='" + txtMatri.getSelectedText().toString()+"'";

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
        }*/
    }//GEN-LAST:event_jBModificarActionPerformed

     public int compruebavacio() { //Funcion que comprueba que no existan espacios en blanco
        int x = 0;
        String vacio = "";

        for (int i = 0; i < 40; i++) {  //comprobar que no este en blanco
            vacio = vacio + " ";
            if (txtNombreAlum.getText().equals(vacio)) {
                x = 0;
                JOptionPane.showMessageDialog(null, "No se aceptan espacios en blanco");
                break;
            } else {
                x = 1;
            }
        }
        return x;
    }
    
    
    
     public void Registra() {//funcion para registar los datos
        int a = 0;
        if (txtIdProf.getText().isEmpty() || txtId_Us.getText().isEmpty() || txtNombreAlum.getText().isEmpty()
                || txtAPT.getText().isEmpty() || txtApMat.getText().isEmpty() 
                || txtMatri.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos");
        } else {
           a = compruebavacio();
            JOptionPane.showMessageDialog(null, "No nombres vacios");System.out.println("Txt Vacion");
        }
        if (a == 1) {
            JOptionPane.showMessageDialog(null, "¡Nuevo usuario registrado!");
            try {
                Connection con = ConexionBD.getConexion();

                PreparedStatement pst = con.prepareStatement("insert into padre value (?,?,?,?,?,?,?,?)");
                pst.setString(1, "0");
                pst.setString(2, txtNombreAlum.getText().trim());
                pst.setString(3, txtAPT.getText().trim());
                pst.setString(4, txtApMat.getText().trim());
                pst.setString(5, txtApMat.getText().trim());
                pst.setString(6, "0");
                

                pst.executeUpdate();
               // mostrardatos();

               Ingresa();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }
    
    
    
    
    
    
    
    
    
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
            java.util.logging.Logger.getLogger(RegistroNiños.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroNiños.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroNiños.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroNiños.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroNiños().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton jBElimina;
    private javax.swing.JButton jBModificar;
    private javax.swing.JButton jBRegistar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTabDatos;
    private javax.swing.JPanel panelRNiños;
    private javax.swing.JTextField txtAPT;
    private javax.swing.JTextField txtApMat;
    private javax.swing.JTextField txtCALF;
    private javax.swing.JTextField txtIdProf;
    private javax.swing.JTextField txtId_Us;
    private javax.swing.JTextField txtMatri;
    private javax.swing.JTextField txtNombreAlum;
    // End of variables declaration//GEN-END:variables
}
