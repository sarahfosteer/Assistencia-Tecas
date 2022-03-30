/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frm;
import java.sql.*;
import dao.data;
import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import Atxy2k.CustomTextField.RestrictedTextField;

/**
 *
 * @author Sarah Foster
 */
public class frm_cad extends javax.swing.JInternalFrame {
        Connection conexao = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
    public frm_cad() {
        initComponents();
        conexao = data.conector();
        RestrictedTextField validarCliente;
        validarCliente = new RestrictedTextField(txtnome);
        validarCliente.setLimit(50);
        RestrictedTextField validarCpf;
        validarCpf = new RestrictedTextField(txtcpf);
        validarCpf.setLimit(12);
        RestrictedTextField validarSenha;
        validarSenha = new RestrictedTextField(txtSenha);
        validarSenha.setLimit(15);
    }
    
    private void pesquisar(){
        if (txtcpf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o CPF do usuário");
            txtcpf.requestFocus();
        }else{
            String sql = "select * from users where cpf=?";
            try {
                pst=conexao.prepareStatement(sql);
                pst.setString(1, txtcpf.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                   txtnome.setText(rs.getString(2));
                   txtDat.setText(rs.getString(4));
                   txtSenha.setText(rs.getString(5));
                   txtPerfil.setSelectedItem(rs.getString(6));
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
                    txtnome.setText(null);
                    txtDat.setText(null);
                    txtSenha.setText(null);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } }
    }
    
    private void add() {
        String sql = "insert into users(nome,cpf,dtnc,senha,perfil) values(?,?,?,?,?)";
        try {
            conexao = data.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtnome.getText());
            pst.setString(2, txtcpf.getText());
            pst.setString(3, txtDat.getText());
            String captura = new String(txtSenha.getPassword());
            pst.setString(4, captura);
            pst.setString(5, txtPerfil.getSelectedItem().toString());
            if ((txtnome.getText().isEmpty()) || (txtcpf.getText().isEmpty()) || (txtSenha.getPassword().length == 0) || txtPerfil.getSelectedItem().equals(" ")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
                    txtnome.setText(null);
                    txtDat.setText(null);
                    txtSenha.setText(null);
                    txtPerfil.setSelectedItem(null);
                    limpar();
                }
            }
        } catch (SQLIntegrityConstraintViolationException e1) {
            JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro login.");
            txtcpf.setText(null);
            limpar();
            txtcpf.requestFocus();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } 
    }
 /*   
private void editar_senha() {
        String sql = "update users set nome=?,dtnc=?,senha=?,perfil=? where cpf=?";
        try {
            conexao = data.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtnome.getText());
            pst.setString(2, txtDat.getText());
            String captura = new String(txtSenha.getPassword());
            pst.setString(4, captura);
            pst.setString(5, txtPerfil.getSelectedItem().toString());
            pst.setString(6, txtcpf.getText());
            if ((txtcpf.getText().isEmpty()) || (txtnome.getText().isEmpty()) || (txtSenha.getPassword().length == 0) || txtPerfil.getSelectedItem().equals(" ")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
                    limpar();
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }*/

private void editar_usuario() {
        String sql = "update users set nome=?,dtnc=?,perfil=? where cpf=?";
        try {
            conexao = data.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtnome.getText());
            pst.setString(2, txtDat.getText());
            pst.setString(3, txtPerfil.getSelectedItem().toString());
            pst.setString(4, txtcpf.getText());
            if ((txtcpf.getText().isEmpty()) || (txtnome.getText().isEmpty()) || (txtSenha.getPassword().length == 0) || txtPerfil.getSelectedItem().equals(" ")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
                    limpar();
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } 
        
    }
private void delete() {
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from users where cpf=?";
            try {
                conexao = data.conector();
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtcpf.getText());
                pst.executeUpdate();
                limpar();
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            } }
    }

private void limpar() {
        txtnome.setText(null);
        txtcpf.setText(null);
        txtDat.setText(null);
        txtSenha.setText(null);
        txtSenha.setEnabled(true);
        txtcpf.setEnabled(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPerfil = new javax.swing.JComboBox<>();
        txtnome = new javax.swing.JTextField();
        txtcpf = new javax.swing.JTextField();
        txtDat = new javax.swing.JTextField();
        btnUsuCreate = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        btnUsuRead = new javax.swing.JButton();
        btnUsuDelete = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();

        setClosable(true);
        setTitle("Usuários");
        setToolTipText("Usuários");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/img/profile.png"))); // NOI18N
        setName("Usuários"); // NOI18N
        setPreferredSize(new java.awt.Dimension(642, 365));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel1.setText("Nome:");
        jLabel1.setToolTipText("Nome Completo");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel2.setText("CPF:");
        jLabel2.setToolTipText("Contem 11 digitos");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel3.setText("Data de Nascimento:");
        jLabel3.setToolTipText("Ex: dd/mm/yy");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel4.setText("Senha:");
        jLabel4.setToolTipText("senha");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jLabel5.setText("Perfil:");
        jLabel5.setToolTipText("Perfil");

        txtPerfil.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        txtPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "user", "admin" }));
        txtPerfil.setToolTipText("Pefil");
        txtPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtnome.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        txtnome.setToolTipText("Nome Completo");
        txtnome.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        txtcpf.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        txtcpf.setToolTipText("CPF com 11 digitos");
        txtcpf.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        txtDat.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        txtDat.setToolTipText("Ex: dd/mm/aaaa");
        txtDat.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        btnUsuCreate.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnUsuCreate.setText("Cadastrar");
        btnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreateActionPerformed(evt);
            }
        });

        btnUsuUpdate.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnUsuUpdate.setText("Alterar");
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });

        btnUsuRead.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnUsuRead.setText("Buscar");
        btnUsuRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuReadActionPerformed(evt);
            }
        });

        btnUsuDelete.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnUsuDelete.setText("Excluir");
        btnUsuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDeleteActionPerformed(evt);
            }
        });

        txtSenha.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcpf, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDat, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUsuCreate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUsuUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUsuRead)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUsuDelete)
                .addGap(158, 158, 158))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtcpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUsuCreate)
                    .addComponent(btnUsuUpdate)
                    .addComponent(btnUsuRead)
                    .addComponent(btnUsuDelete))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        setBounds(200, 100, 642, 329);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsuReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuReadActionPerformed
        pesquisar();
    }//GEN-LAST:event_btnUsuReadActionPerformed

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
        add();
    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        editar_usuario();     
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeleteActionPerformed
        delete();
    }//GEN-LAST:event_btnUsuDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JButton btnUsuRead;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtDat;
    private javax.swing.JComboBox<String> txtPerfil;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtcpf;
    private javax.swing.JTextField txtnome;
    // End of variables declaration//GEN-END:variables
}
