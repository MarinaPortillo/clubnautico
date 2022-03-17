
package Gui;

import clubnautico.ISociosDAO;
import entidades.Socio;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marina
 */
public class SociosForm extends javax.swing.JFrame {

    private ISociosDAO sociosDAO;
    /**
     * Creates new form SociosForm
     */
    public SociosForm(ISociosDAO sociosDAO) {
        initComponents();
        this.sociosDAO = sociosDAO;
        this.llenarTabla();
    }

  private void guardar(){ 
   //TODO: COMPROBAR SI SE AGREGA O SE ACTUALIZA
   if(this.txtIdSocio.getText().isEmpty()){
     this.agregar();
     }else{
       this.actualizar();
   }
  }
  
 private void agregar(){
         String nombre = this.txtNombre.getText();
         String curp = this.txtCurp.getText();
      //TODO: VALIDACIONES DE DATOS
         Socio socioNuevo = new Socio(nombre,curp);
         boolean seAgregoSocio = this.sociosDAO.agregar(socioNuevo);
      if (seAgregoSocio){
          JOptionPane.showMessageDialog(this,"Se agrego el socio", "Información", JOptionPane.INFORMATION_MESSAGE);
          this.limpiar();
          this.llenarTabla();
      }else{
          JOptionPane.showMessageDialog(this,"No se agrego el socio", "Error", JOptionPane.ERROR_MESSAGE);       
                 }    
     }
  
 private void actualizar(){
        
  Long idSocio = Long.parseLong(this.txtIdSocio.getText());
  String nombre = this.txtNombre.getText();
  String curp = this.txtCurp.getText();
     boolean seActualizoSocio = this.sociosDAO.actualizar(new Socio(idSocio, nombre, curp));
      if (seActualizoSocio){
          JOptionPane.showMessageDialog(this,"Se actualizo el socio", "Información", JOptionPane.INFORMATION_MESSAGE);
          this.limpiar();
          this.llenarTabla();
      }else{
          JOptionPane.showMessageDialog(this,"No fue posible actualizar el socio", "Error", JOptionPane.ERROR_MESSAGE);       
                 }    
 }
 
 private void llenarTabla(){
      List<Socio> listaSocios = this.sociosDAO.consultarTodos();
      DefaultTableModel modeloTabla = (DefaultTableModel)this.tblSocios.getModel();
      modeloTabla.setRowCount(0);
      listaSocios.forEach(socio -> {
          Object[] fila = new Object[3];
          fila[0] = socio.getIdSocio();
          fila[1] = socio.getNombre();
          fila[2] = socio.getCurp();
          modeloTabla.addRow(fila);
      });
      
  }

  private void limpiar(){
      this.txtIdSocio.setText("");
      this.txtNombre.setText("");
      this.txtCurp.setText("");
  }
  
 private void eliminar(){
  
     Long idSocioSeleccionado = this.getIdSocioSeleccionado();
    if(idSocioSeleccionado == null){
         JOptionPane.showMessageDialog(this, "Debes seleccionar un socio", "Informacion", JOptionPane.WARNING_MESSAGE);
        return;   
    }else{
        int opcionSeleccionada = JOptionPane.showConfirmDialog(this, "¿Estas seguro de eliminar al socio?", "Confirmación", JOptionPane.YES_NO_OPTION);      if(opcionSeleccionada == JOptionPane.NO_OPTION){
           return;   
       }
        boolean seEliminoSocio = this.sociosDAO.eliminar(idSocioSeleccionado);
    if(seEliminoSocio){
          JOptionPane.showMessageDialog(this, "Se elimino el socio correctamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
          this.llenarTabla();  
    }else{
          JOptionPane.showMessageDialog(this, "No se puede eliminar el socio", "Informacion", JOptionPane.ERROR_MESSAGE);
       }
     }
     
 }
 
 private Long getIdSocioSeleccionado(){
     
    int indiceFilaSeleccionada = this.tblSocios.getSelectedRow();
    if(indiceFilaSeleccionada != -1){
        DefaultTableModel modelo = (DefaultTableModel)this.tblSocios.getModel();
        int indiceColumnaId = 0;
        Long idSocioSeleccionado = (Long)modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaId);
        return idSocioSeleccionado;
    
    } else{
        return null;
    }
    
 }
 
 private void editar(){
       Long idSocioSeleccionado = this.getIdSocioSeleccionado();
    if(idSocioSeleccionado == null){
         JOptionPane.showMessageDialog(this, "Debes seleccionar un socio", "Informacion", JOptionPane.WARNING_MESSAGE);
        return;   
    }
    Socio socio = this.sociosDAO.consultar(idSocioSeleccionado);
    if(socio != null){
        this.llenarFormulario(socio);
 }
 }

private void llenarFormulario(Socio socio){
    this.txtIdSocio.setText(socio.getIdSocio().toString());
    this.txtNombre.setText(socio.getNombre());
    this.txtCurp.setText(socio.getCurp());
    
} 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LbLIdSocio = new javax.swing.JLabel();
        LbLNombre = new javax.swing.JLabel();
        LblCurp = new javax.swing.JLabel();
        txtIdSocio = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCurp = new javax.swing.JTextField();
        butGuardar = new javax.swing.JButton();
        butCancelar = new javax.swing.JButton();
        pnlTablaSocios = new javax.swing.JScrollPane();
        tblSocios = new javax.swing.JTable();
        butEditar = new javax.swing.JButton();
        butElimiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administacion de Socios");

        LbLIdSocio.setText("Id Socio");

        LbLNombre.setText("Nombre");

        LblCurp.setText("Curp");

        txtIdSocio.setEditable(false);

        butGuardar.setText("Guardar");
        butGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butGuardarActionPerformed(evt);
            }
        });

        butCancelar.setText("Cancelar");
        butCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCancelarActionPerformed(evt);
            }
        });

        tblSocios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Socio", "Nombre", "Curp"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pnlTablaSocios.setViewportView(tblSocios);

        butEditar.setText("Editar");
        butEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEditarActionPerformed(evt);
            }
        });

        butElimiar.setText("Eliminar");
        butElimiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butElimiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LbLIdSocio, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                            .addComponent(LbLNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LblCurp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCurp, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(butGuardar)
                        .addGap(52, 52, 52)
                        .addComponent(butCancelar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlTablaSocios, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(butEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butElimiar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(LbLIdSocio)
                                    .addComponent(txtIdSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(LbLNombre)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(LblCurp)
                                    .addComponent(txtCurp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(butGuardar)
                                    .addComponent(butCancelar)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(butEditar)
                                .addGap(30, 30, 30)
                                .addComponent(butElimiar)))
                        .addGap(0, 84, Short.MAX_VALUE))
                    .addComponent(pnlTablaSocios, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butGuardarActionPerformed
        this.guardar();
    }//GEN-LAST:event_butGuardarActionPerformed

    private void butCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCancelarActionPerformed
        this.limpiar();
    }//GEN-LAST:event_butCancelarActionPerformed

    private void butEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butEditarActionPerformed
         this.editar();
    }//GEN-LAST:event_butEditarActionPerformed

    private void butElimiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butElimiarActionPerformed
        this.eliminar();
    }//GEN-LAST:event_butElimiarActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LbLIdSocio;
    private javax.swing.JLabel LbLNombre;
    private javax.swing.JLabel LblCurp;
    private javax.swing.JButton butCancelar;
    private javax.swing.JButton butEditar;
    private javax.swing.JButton butElimiar;
    private javax.swing.JButton butGuardar;
    private javax.swing.JScrollPane pnlTablaSocios;
    private javax.swing.JTable tblSocios;
    private javax.swing.JTextField txtCurp;
    private javax.swing.JTextField txtIdSocio;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
