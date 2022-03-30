/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frm;

import Atxy2k.CustomTextField.RestrictedTextField;
import java.util.Scanner;
import br.com.parg.viacep.ViaCEP;
import br.com.parg.viacep.ViaCEPEvents;
import br.com.parg.viacep.ViaCEPException;
import classes.limite_letras;
import classes.limite_numero;
import dao.data;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class frm_cliente extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;      

    public frm_cliente() {
        initComponents();
        conexao = data.conector();
        RestrictedTextField validarCliente;
        validarCliente = new RestrictedTextField(txtNome);
        validarCliente.setLimit(50);
        RestrictedTextField validarCpf;
        validarCpf = new RestrictedTextField(txtCpf);
        validarCpf.setLimit(12);
        RestrictedTextField validarFone;
        validarFone = new RestrictedTextField(txtFone);
        validarFone.setLimit(15);
        RestrictedTextField validarCep;
        validarCep = new RestrictedTextField(txtCep);
        validarCep.setLimit(9);
    }
    private void pesquisar_cliente(){
        if (txtCpf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o CPF do cliente");
            txtCpf.requestFocus();
        }else{
            String sql = "select * from clientes where cpf_cliente=?";
            try {
                pst=conexao.prepareStatement(sql);
                pst.setString(1, txtCpf.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                   txtNome.setText(rs.getString(2));
                   txtFone.setText(rs.getString(4));
                   txtBairro.setText(rs.getString(5));
                   txtCep.setText(rs.getString(6));
                   txtRua.setText(rs.getString(7));
                   txtN.setText(rs.getString(8));
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
                    txtNome.setText(null);
                    txtFone.setText(null);
                    txtCep.setText(null);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } }
    }
    
    private void add() {
        String sql = "insert into clientes(nome_cliente,cpf_cliente,telefone_cliente, bairro, cep, rua, numero) values(?,?,?,?,?,?,?)";
        try {
            conexao = data.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtCpf.getText());
            pst.setString(3, txtFone.getText());
            pst.setString(4, txtBairro.getText());
            pst.setString(5, txtCep.getText());
            pst.setString(6, txtRua.getText());
            pst.setString(7, txtN.getText());
            if ((txtNome.getText().isEmpty()) || (txtCpf.getText().isEmpty()) || (txtFone.getText().isEmpty()) || (txtCep.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
                    txtNome.setText(null);
                    txtCpf.setText(null);
                    txtFone.setText(null);
                    txtCep.setText(null);
                    limpar();
                }
            }
        } catch (SQLIntegrityConstraintViolationException e1) {
            JOptionPane.showMessageDialog(null, "cliente já cadastrado.");
            txtCpf.setText(null);
            limpar();
            txtCpf.requestFocus();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } 
    }
    
    private void editar_usuario() {
        String sql = "update clientes set nome_cliente=?, telefone_cliente=?, bairro=?, cep=?, rua=?, numero=? where cpf_cliente=?";
        try {
            conexao = data.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtFone.getText());
            pst.setString(3, txtBairro.getText());
            pst.setString(4, txtCep.getText());
            pst.setString(5, txtRua.getText());
            pst.setString(6, txtN.getText());
            pst.setString(7, txtCpf.getText());
            if ((txtCpf.getText().isEmpty()) || (txtNome.getText().isEmpty()) || (txtFone.getText().isEmpty()) || (txtBairro.getText().isEmpty()) || (txtCep.getText().isEmpty()) || (txtRua.getText().isEmpty())|| (txtN.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do cliente alterados com sucesso");
                    limpar();
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } 
        
    }
    
    private void limpar() {
        txtNome.setText(null);
        txtCpf.setText(null);
        txtFone.setText(null);
        txtCep.setText(null);
        txtRua.setText(null);
        txtBairro.setText(null);
        txtN.setText(null);
 }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtCpf = new javax.swing.JTextField();
        txtFone = new javax.swing.JTextField();
        txtCep = new javax.swing.JTextField();
        txtRua = new javax.swing.JTextField();
        txtN = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        btnPes = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnalt = new javax.swing.JButton();
        buscar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Clientes");
        setToolTipText("Clientes");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/img/client.png"))); // NOI18N

        jDesktopPane1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Calibri", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cadastro de clientes ");

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(291, 291, 291))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel2.setText("NOME:");
        jLabel2.setToolTipText("Nome");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel3.setText("CPF:");
        jLabel3.setToolTipText("Nome");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel4.setText("FONE:");
        jLabel4.setToolTipText("Nome");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel5.setText("CEP:");
        jLabel5.setToolTipText("Nome");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel6.setText("RUA:");
        jLabel6.setToolTipText("Nome");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel7.setText("Nº:");
        jLabel7.setToolTipText("Nome");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel8.setText("BAIRRO:");
        jLabel8.setToolTipText("Nome");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        txtNome.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtNome.setToolTipText("Nome do Cliente");
        txtNome.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        txtCpf.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtCpf.setToolTipText("CPF do Cliente");
        txtCpf.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        txtFone.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtFone.setToolTipText("ex: (DDD) 1111-1111");
        txtFone.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        txtCep.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtCep.setToolTipText("ex: 13457051");
        txtCep.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        txtRua.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtRua.setToolTipText(" Logradouro");
        txtRua.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        txtN.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtN.setToolTipText("Ex: 123");
        txtN.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        txtBairro.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtBairro.setToolTipText("Bairro");
        txtBairro.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        btnPes.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnPes.setText("Pesquisar");
        btnPes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnAdd.setText("Adicionar");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnalt.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnalt.setText("Alterar");
        btnalt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnalt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaltActionPerformed(evt);
            }
        });

        buscar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-zoom.png"))); // NOI18N
        buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtN, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(102, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd)
                .addGap(8, 8, 8)
                .addComponent(btnPes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnalt, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(351, 351, 351))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPes)
                    .addComponent(btnalt)
                    .addComponent(btnAdd))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        ViaCEP viacep = new ViaCEP();
        
        try {
            viacep.buscar(txtCep.getText());
            txtBairro.setText(viacep.getBairro());
            txtRua.setText(viacep.getLogradouro());          
        } catch (ViaCEPException ex) {
            Logger.getLogger(frm_cliente.class.getName()).log(Level. SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Não foi possível encontrar o CEP");
        }
    }//GEN-LAST:event_buscarActionPerformed

    private void btnPesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesActionPerformed
        pesquisar_cliente();
    }//GEN-LAST:event_btnPesActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        add();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnaltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaltActionPerformed
        editar_usuario();
    }//GEN-LAST:event_btnaltActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnPes;
    private javax.swing.JButton btnalt;
    private javax.swing.JButton buscar;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtFone;
    private javax.swing.JTextField txtN;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtRua;
    // End of variables declaration//GEN-END:variables
}
