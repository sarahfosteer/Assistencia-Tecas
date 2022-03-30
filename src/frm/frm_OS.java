/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frm;

import br.com.parg.viacep.ViaCEP;
import br.com.parg.viacep.ViaCEPException;
import dao.data;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Sarah Foster
 */
public class frm_OS extends javax.swing.JInternalFrame {
        Connection conexao;
        PreparedStatement pst;
        ResultSet rs;
        
        private String tipo; 
        private String entrega; 
    public frm_OS() {
        initComponents();
        conexao = data.conector();
    }
    
    
    
    private void emitirOs() {
        String sql = "insert into os(tipo, situacao,imei1, imei2, entrega, outros, senha, equipamento, defeito, valor,id_cliente) values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conexao = data.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, txtcaixa.getSelectedItem().toString());
            pst.setString(3, txtmei1.getText());
            pst.setString(4, txtmei2.getText());
            pst.setString(5, entrega);
            pst.setString(6, txtout.getText());
            pst.setString(7, txtsenha.getText());
            pst.setString(8, txtmodel.getText());
            pst.setString(9, txtdef.getText());
            pst.setString(10, txtvalor.getText().replace(",", "."));
            pst.setString(11, id.getText());
            if ((id.getText().isEmpty()) || (entrega.isEmpty()) || (txtout.getText().isEmpty()) || (txtmodel.getText().isEmpty()) || (txtdef.getText().isEmpty()) || txtcaixa.getSelectedItem().equals(" ")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    limpar();
                    //recuperarOs();
                    JOptionPane.showMessageDialog(null, "OS emitida com sucesso");
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    private void pesquisar(){
        if (txtcpf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o CPF");
            txtcpf.requestFocus();
        }else{
            String sql = "select * from clientes where cpf_cliente=?";
            try {
                pst=conexao.prepareStatement(sql);
                pst.setString(1, txtcpf.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                   id.setText(rs.getString(1));
                   txtnome.setText(rs.getString(2));
                   txtfone.setText(rs.getString(4));
                   txtbairro.setText(rs.getString(5));
                   txtcep.setText(rs.getString(6));
                   txtend.setText(rs.getString(7));
                   txtn2.setText(rs.getString(8));
                   
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
                    txtnome.setText(null);
                    txtfone.setText(null);
                    txtcep.setText(null);
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
            pst.setString(1, txtnome.getText());
            pst.setString(2, txtcpf.getText());
            pst.setString(3, txtfone.getText());
            pst.setString(4, txtbairro.getText());
            pst.setString(5, txtcep.getText());
            pst.setString(6, txtend.getText());
            pst.setString(7, txtn2.getText());
            if ((txtnome.getText().isEmpty()) || (txtcpf.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
                }
            }
        } catch (SQLIntegrityConstraintViolationException e1) {
            JOptionPane.showMessageDialog(null, "Cliente já cadastrado");
            txtcpf.setText(null);
            txtcpf.requestFocus();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } 
    }
        
    private void pesquisarOs() {
        String num_os = JOptionPane.showInputDialog("Número da OS");
        String sql = "select os, date_format(dataOS,'%d/%m/%Y - %H:%i'), tipo, situacao, imei1, imei2, entrega, outros, senha, equipamento, defeito, valor, id_cliente from os where os= " + num_os;
        try {
            conexao = data.conector();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtN.setText(rs.getString(1));
                txtD.setText(rs.getString(2));
                String rbtTipo = rs.getString(3);
                if (rbtTipo.equals("OS")) {
                    txtord.setSelected(true);
                    tipo = "OS";
                } else {
                    txtorc.setSelected(true);
                    tipo = "Orçamento";
                }
                txtcaixa.setSelectedItem(rs.getString(4));
                txtmodel.setText(rs.getString(10));
                txtdef.setText(rs.getString(11));
                txtmei1.setText(rs.getString(5));
                txtmei2.setText(rs.getString(6));
                txtout.setText(rs.getString(8));
                txtsenha.setText(rs.getString(9));
                txtvalor.setText(rs.getString(12));
                id.setText(rs.getString(13));
                String entrega2 = rs.getString(7);
                if (entrega2.equals("Riscado")) {
                    txt1.setSelected(true);
                    entrega = "Riscado";
                }else if(entrega2.equals("Trincado")){
                    txt2.setSelected(true);
                    entrega = "Trincado";
                }else if(entrega2.equals("Sem chip")){
                    txt3.setSelected(true);
                    entrega = "Sem chip";
                } else if(entrega2.equals("Deligado")){
                    txt4.setSelected(true);
                    entrega = "Desligado";
                } else if(entrega2.equals("Sem bateria")){
                    txt5.setSelected(true);
                    entrega = "Sem bateria";
                }else{
                    txt6.setSelected(true);
                    entrega = "Sem cartão";
                }
            }else {
                JOptionPane.showMessageDialog(null, "OS não cadastrada");
            }
        } catch (SQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "OS Inválida");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void pesquisar2(){
            String sql = "select * from clientes where id_cliente = ?";
            try {
                pst=conexao.prepareStatement(sql);
                pst.setString(1, id.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                   txtnome.setText(rs.getString(2));
                   txtcpf.setText(rs.getString(3));
                   txtfone.setText(rs.getString(4));
                   txtbairro.setText(rs.getString(5));
                   txtcep.setText(rs.getString(6));
                   txtend.setText(rs.getString(7));
                   txtn2.setText(rs.getString(8));
                   
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
                    txtnome.setText(null);
                    txtfone.setText(null);
                    txtcep.setText(null);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } }
    
 private void editar_os() {
        String sql = "update os set tipo=?, situacao=?, imei1=?, imei2=?, entrega=?, outros=?, senha=?, equipamento=?, defeito=?, valor=? where os=?";
        try {
            conexao = data.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, txtcaixa.getSelectedItem().toString());
            pst.setString(3, txtmei1.getText());
            pst.setString(4, txtmei2.getText());
            pst.setString(5, entrega);
            pst.setString(6, txtout.getText());
            pst.setString(7, txtsenha.getText());
            pst.setString(8, txtmodel.getText());
            pst.setString(9, txtdef.getText());
            pst.setString(10, txtvalor.getText().replace(",", "."));
            pst.setString(11, txtN.getText());
            if ((id.getText().isEmpty()) || (entrega.isEmpty()) || (txtout.getText().isEmpty()) || (txtmodel.getText().isEmpty()) || (txtdef.getText().isEmpty()) || txtcaixa.getSelectedItem().equals(" ")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    limpar();
                    JOptionPane.showMessageDialog(null, "OS alterada com sucesso");
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
 
 private void imprimirOs() throws JRException {
        conexao = data.conector();
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja imprimir esta OS?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                HashMap filtro = new HashMap();
                filtro.put("os", Integer.parseInt(txtN.getText()));
                JasperPrint print = JasperFillManager.fillReport("C:\\Users\\Sarah Foster\\Documents\\NetBeansProjects\\Loja1\\Reports\\OS.jasper", filtro, conexao);
                JasperViewer.viewReport(print, false);
                conexao.close();
            } catch (NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
 
private void limpar() {
        txtnome.setText(null);
        txtcpf.setText(null);
        txtfone.setText(null);
        txtcep.setText(null);
        txtend.setText(null);
        txtbairro.setText(null);
        txtN.setText(null);
        txtcaixa.setSelectedItem(" ");
        txtmei1.setText(null);        
        txtmei2.setText(null);        
        entrega = (null);
        txtout.setText(null);       
        txtsenha.setText(null);
        txtmodel.setText(null);        
        txtdef.setText(null);
        txtvalor.setText("R$");
        txtD.setText(null);
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtN = new javax.swing.JTextField();
        txtD = new javax.swing.JTextField();
        txtorc = new javax.swing.JRadioButton();
        txtord = new javax.swing.JRadioButton();
        txtend = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtbairro = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtn2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtcep = new javax.swing.JTextField();
        buscar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtnome = new javax.swing.JTextField();
        txtcpf = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtmodel = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtmei1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtcaixa = new javax.swing.JComboBox<>();
        txtmei2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtsenha = new javax.swing.JTextField();
        btnUsuCreate = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        btnUsuRead = new javax.swing.JButton();
        btnimp = new javax.swing.JButton();
        btncad = new javax.swing.JButton();
        txt1 = new javax.swing.JCheckBox();
        txt2 = new javax.swing.JCheckBox();
        jLabel19 = new javax.swing.JLabel();
        txt3 = new javax.swing.JCheckBox();
        txtfone = new javax.swing.JTextField();
        txt4 = new javax.swing.JCheckBox();
        txt5 = new javax.swing.JCheckBox();
        txt6 = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtout = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtdef = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        txtvalor = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        btnpes3 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setClosable(true);
        setForeground(new java.awt.Color(255, 255, 255));
        setIconifiable(true);
        setTitle("OS");
        setFocusTraversalPolicyProvider(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/img/file.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ordem de Serviço");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Nº OS:");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("DATA:");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtN.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        txtN.setToolTipText("Numero");

        txtD.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        txtD.setToolTipText("Data");

        buttonGroup1.add(txtorc);
        txtorc.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtorc.setText("Orçamento");
        txtorc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtorcActionPerformed(evt);
            }
        });

        buttonGroup1.add(txtord);
        txtord.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtord.setText("Ordem de Serviço");
        txtord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtordActionPerformed(evt);
            }
        });

        txtend.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtend.setToolTipText("Endereço");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Bairro:");
        jLabel8.setToolTipText("Bairro");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtbairro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtbairro.setToolTipText("Bairro");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Nº:");
        jLabel9.setToolTipText("numero da casa");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtn2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtn2.setToolTipText("Numero");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("CEP:");
        jLabel10.setToolTipText("CEP");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtcep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcep.setToolTipText("CEP");

        buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-zoom.png"))); // NOI18N
        buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Nome do cliente:");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("CPF do cliente:");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtnome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnome.setToolTipText("NOME");

        txtcpf.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcpf.setToolTipText("CPF");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Endereço:");
        jLabel7.setToolTipText("Endereço");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Modelo:");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtmodel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtmodel.setToolTipText("Modelo");
        txtmodel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmodelActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("IMEI:");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtmei1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtmei1.setToolTipText("IMEI");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("IMEI:");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Situação:");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtcaixa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcaixa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entrega OK", "Orçamento Reprovado", "Aguardando aprovação", "Aguardado peças", "Abandonado pelo Cliente", "Na bancada", "Retornou" }));

        txtmei2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtmei2.setToolTipText("IMEI");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("Senha:");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtsenha.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtsenha.setToolTipText("CPF");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmodel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmei1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmei2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtmodel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(jLabel4)
                        .addComponent(txtcaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtmei2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtmei1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)
                        .addComponent(jLabel14)
                        .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btnUsuCreate.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnUsuCreate.setText("Criar");
        btnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreateActionPerformed(evt);
            }
        });

        btnUsuUpdate.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnUsuUpdate.setText("Alterar");
        btnUsuUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUsuUpdateMouseClicked(evt);
            }
        });
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

        btnimp.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnimp.setText("Imprimir");
        btnimp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimpActionPerformed(evt);
            }
        });

        btncad.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btncad.setText("Cadastrar");
        btncad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncadActionPerformed(evt);
            }
        });

        txt1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt1.setText("Riscado");
        txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt1ActionPerformed(evt);
            }
        });

        txt2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt2.setText("Trincado");
        txt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt2ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("Telefone para contato:");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txt3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt3.setText("Sem chip");
        txt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt3ActionPerformed(evt);
            }
        });

        txtfone.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfone.setToolTipText("Fone");

        txt4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt4.setText("Desligado");
        txt4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt4ActionPerformed(evt);
            }
        });

        txt5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt5.setText("Sem bateria");
        txt5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt5ActionPerformed(evt);
            }
        });

        txt6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt6.setText("Sem cartão");
        txt6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt6ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Outros:");
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtout.setColumns(20);
        txtout.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtout.setRows(5);
        txtout.setToolTipText("Outros");
        jScrollPane1.setViewportView(txtout);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("Defeito relatado pelo cliente:");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtdef.setColumns(20);
        txtdef.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtdef.setRows(5);
        txtdef.setToolTipText("Defeito");
        jScrollPane2.setViewportView(txtdef);

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("Valor:");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtvalor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtvalor.setToolTipText("Valor");
        txtvalor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtvalorActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setText("ID:");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        id.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        id.setToolTipText("ID");

        btnpes3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-zoom.png"))); // NOI18N
        btnpes3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnpes3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpes3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUsuCreate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUsuUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUsuRead)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnimp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncad)
                .addGap(312, 312, 312))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txt2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt4))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txt1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt3)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt5)
                                    .addComponent(txt6))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1))
                            .addComponent(jLabel13)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel19)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtvalor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel20)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 154, Short.MAX_VALUE))
                                    .addComponent(txtfone)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtorc)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtord)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtcep, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(buscar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtend, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtbairro, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtN, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtD, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtcpf, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnpes3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(10, 10, 10))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(txtN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtcpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnpes3)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtbairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(txtn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtcep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtord)
                                    .addComponent(txtorc)
                                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt1)
                                    .addComponent(txt3)
                                    .addComponent(txt5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt2)
                                    .addComponent(txt4)
                                    .addComponent(txt6)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel12))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtfone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtvalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnimp)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUsuCreate)
                        .addComponent(btnUsuUpdate)
                        .addComponent(btncad)
                        .addComponent(btnUsuRead)))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(280, 280, 280))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setText("Endereço:");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(463, 463, 463)
                    .addComponent(jLabel18)
                    .addContainerGap(469, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(251, 251, 251)
                    .addComponent(jLabel18)
                    .addContainerGap(296, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtmodelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmodelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmodelActionPerformed

    private void txtvalorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtvalorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtvalorActionPerformed

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
            emitirOs();
    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
            editar_os();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsuReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuReadActionPerformed
            pesquisarOs();
            pesquisar2();
    }//GEN-LAST:event_btnUsuReadActionPerformed

    private void btnimpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimpActionPerformed
          try {
                imprimirOs();
            } catch (JRException ex) {
                Logger.getLogger(frm_OS.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btnimpActionPerformed

    private void btncadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncadActionPerformed
        add();
    }//GEN-LAST:event_btncadActionPerformed

    private void txtorcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtorcActionPerformed
        tipo = "Orçamento";
    }//GEN-LAST:event_txtorcActionPerformed

    private void txtordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtordActionPerformed
        tipo = "Ordem de Serviço";
    }//GEN-LAST:event_txtordActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        txtorc.setSelected(true);
        tipo = "Orçamento";
    }//GEN-LAST:event_formInternalFrameOpened

    private void txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt1ActionPerformed
        entrega = "Riscado";
    }//GEN-LAST:event_txt1ActionPerformed

    private void txt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt2ActionPerformed
        entrega = "Trincado";
    }//GEN-LAST:event_txt2ActionPerformed

    private void txt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt3ActionPerformed
        entrega = "Sem chip";
    }//GEN-LAST:event_txt3ActionPerformed

    private void txt4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt4ActionPerformed
        entrega = "Desligado";
    }//GEN-LAST:event_txt4ActionPerformed

    private void txt5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt5ActionPerformed
        entrega = "Sem bateria";
    }//GEN-LAST:event_txt5ActionPerformed

    private void txt6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt6ActionPerformed
        entrega = "Sem cartão";
    }//GEN-LAST:event_txt6ActionPerformed

    private void btnpes3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpes3ActionPerformed
        pesquisar();
    }//GEN-LAST:event_btnpes3ActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        ViaCEP viacep = new ViaCEP();
        
        try {
            viacep.buscar(txtcep.getText());
            txtbairro.setText(viacep.getBairro());
            txtend.setText(viacep.getLogradouro());          
        } catch (ViaCEPException ex) {
            Logger.getLogger(frm_cliente.class.getName()).log(Level. SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Não foi possível encontrar o CEP");
        }
    }//GEN-LAST:event_buscarActionPerformed

    private void btnUsuUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuUpdateMouseClicked
        editar_os();
    }//GEN-LAST:event_btnUsuUpdateMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuRead;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JButton btncad;
    private javax.swing.JButton btnimp;
    private javax.swing.JButton btnpes3;
    private javax.swing.JButton buscar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField id;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JCheckBox txt1;
    private javax.swing.JCheckBox txt2;
    private javax.swing.JCheckBox txt3;
    private javax.swing.JCheckBox txt4;
    private javax.swing.JCheckBox txt5;
    private javax.swing.JCheckBox txt6;
    private javax.swing.JTextField txtD;
    private javax.swing.JTextField txtN;
    private javax.swing.JTextField txtbairro;
    private javax.swing.JComboBox<String> txtcaixa;
    private javax.swing.JTextField txtcep;
    private javax.swing.JTextField txtcpf;
    private javax.swing.JTextArea txtdef;
    private javax.swing.JTextField txtend;
    private javax.swing.JTextField txtfone;
    private javax.swing.JTextField txtmei1;
    private javax.swing.JTextField txtmei2;
    private javax.swing.JTextField txtmodel;
    private javax.swing.JTextField txtn2;
    private javax.swing.JTextField txtnome;
    private javax.swing.JRadioButton txtorc;
    private javax.swing.JRadioButton txtord;
    private javax.swing.JTextArea txtout;
    private javax.swing.JTextField txtsenha;
    private javax.swing.JTextField txtvalor;
    // End of variables declaration//GEN-END:variables
}
