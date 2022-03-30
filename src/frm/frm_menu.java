/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frm;

import dao.data;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.*;
import java.text.DateFormat;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.ImageIcon;
import java.awt.*;

/**
 *
 * @author Sarah Foster
 */
public class frm_menu extends javax.swing.JFrame {

    Connection conexao;

    /**
     * Creates new form frm_menu
     */
    public frm_menu() {
        initComponents();
    }

private void rltr_cliente(){
    int confirma = JOptionPane.showConfirmDialog(null, "Deseja imprimir este relatório?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
        try {
            conexao = data.conector();
            JasperPrint print = JasperFillManager.fillReport("C:\\Users\\Sarah Foster\\Documents\\NetBeansProjects\\Loja1\\Reports\\relatorio-clientes.jasper", null, conexao);
            JasperViewer.viewReport(print, false);
        } catch (JRException e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
}}
private void rltr_servico() {
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja imprimir este relatório?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                conexao = data.conector();
                JasperPrint print = JasperFillManager.fillReport("C:\\Users\\Sarah Foster\\Documents\\NetBeansProjects\\Loja1\\Reports\\Servicos.jasper", null, conexao);
                JasperViewer.viewReport(print, false);
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jSeparator1 = new javax.swing.JSeparator();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        jPopupMenu5 = new javax.swing.JPopupMenu();
        jPopupMenu6 = new javax.swing.JPopupMenu();
        jPopupMenu7 = new javax.swing.JPopupMenu();
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        popupMenu1 = new java.awt.PopupMenu();
        menuBar2 = new java.awt.MenuBar();
        menu3 = new java.awt.Menu();
        menu4 = new java.awt.Menu();
        menuBar3 = new java.awt.MenuBar();
        menu5 = new java.awt.Menu();
        menu6 = new java.awt.Menu();
        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jMenu1 = new javax.swing.JMenu();
        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("tecas?zeroDateTimeBehavior=convertToNullPU").createEntityManager();
        osQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT o FROM Os o");
        osList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : osQuery.getResultList();
        osQuery1 = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT o FROM Os o");
        osList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : osQuery1.getResultList();
        desktop = new javax.swing.JDesktopPane();
        labelUs = new javax.swing.JLabel();
        lbdata = new javax.swing.JLabel();
        lbdata1 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        menu = new javax.swing.JMenuBar();
        menCad = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        menCadUsu = new javax.swing.JMenuItem();
        menRel = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        menSobre = new javax.swing.JMenu();
        jsair = new javax.swing.JMenu();

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        jMenu5.setText("jMenu5");

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        popupMenu1.setLabel("popupMenu1");

        menu3.setLabel("File");
        menuBar2.add(menu3);

        menu4.setLabel("Edit");
        menuBar2.add(menu4);

        menu5.setLabel("File");
        menuBar3.add(menu5);

        menu6.setLabel("Edit");
        menuBar3.add(menu6);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela Inicial");
        setFocusable(false);
        setIconImage(new ImageIcon(getClass().getResource("/img/menu.png")).
            getImage());
        setName("Menu"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        desktop.setBackground(new java.awt.Color(0, 204, 204));
        desktop.setFocusable(false);

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1008, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );

        labelUs.setBackground(new java.awt.Color(255, 255, 255));
        labelUs.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        labelUs.setText("Usuário:");
        labelUs.setToolTipText("Usuário");

        lbdata.setBackground(new java.awt.Color(255, 255, 255));
        lbdata.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbdata.setText("Data");
        lbdata.setToolTipText("Usuário");

        lbdata1.setBackground(new java.awt.Color(255, 255, 255));
        lbdata1.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbdata1.setText("Data:");
        lbdata1.setToolTipText("Usuário");

        lblUsuario.setBackground(new java.awt.Color(255, 255, 255));
        lblUsuario.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lblUsuario.setText("Usuário");
        lblUsuario.setToolTipText("Usuário");

        menCad.setText("Cadastro");
        menCad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menCad.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadActionPerformed(evt);
            }
        });

        jMenuItem1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuItem1.setText("Clientes");
        jMenuItem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menCad.add(jMenuItem1);

        jMenuItem3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuItem3.setText("Registros");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menCad.add(jMenuItem3);

        jMenuItem2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuItem2.setText("OS");
        jMenuItem2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menCad.add(jMenuItem2);

        menCadUsu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        menCadUsu.setText("Usuário");
        menCadUsu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menCadUsu.setEnabled(false);
        menCadUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadUsuActionPerformed(evt);
            }
        });
        menCad.add(menCadUsu);

        menu.add(menCad);

        menRel.setText("Relatório");
        menRel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menRel.setEnabled(false);
        menRel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menRel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRelActionPerformed(evt);
            }
        });

        jMenuItem4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuItem4.setText("Serviços");
        jMenuItem4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menRel.add(jMenuItem4);

        jMenuItem8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuItem8.setText("Relatório de Clentes");
        jMenuItem8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        menRel.add(jMenuItem8);

        menu.add(menRel);

        menSobre.setText("Sobre");
        menSobre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menSobre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menSobre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menSobreMouseClicked(evt);
            }
        });
        menSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menSobreActionPerformed(evt);
            }
        });
        menu.add(menSobre);

        jsair.setText("Sair");
        jsair.setContentAreaFilled(false);
        jsair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jsair.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jsair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jsairMouseClicked(evt);
            }
        });
        jsair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsairActionPerformed(evt);
            }
        });
        menu.add(jsair);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(labelUs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbdata1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbdata)
                .addGap(111, 111, 111))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUs)
                    .addComponent(lbdata)
                    .addComponent(lbdata1)
                    .addComponent(lblUsuario))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("Tela Principal");
        getAccessibleContext().setAccessibleDescription("Tela Principal");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menCadUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadUsuActionPerformed
        frm_cad usuario = new frm_cad();
        usuario.setVisible(true);
        desktop.add(usuario);
    }//GEN-LAST:event_menCadUsuActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
       java.util.Date data = new java.util.Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        lbdata.setText(formatador.format(data));
       
    }//GEN-LAST:event_formWindowActivated

    private void jsairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsairActionPerformed

    }//GEN-LAST:event_jsairActionPerformed

    private void jsairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jsairMouseClicked
        int sair = JOptionPane.showConfirmDialog(null, "Deseja sair do sistema?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jsairMouseClicked

    private void menSobreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menSobreMouseClicked
        frm_about sobre = new frm_about();
        sobre.setVisible(true);
        desktop.add(sobre);        
    }//GEN-LAST:event_menSobreMouseClicked

    private void menSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menSobreActionPerformed
        frm_sobre sobre = new frm_sobre();
        sobre.setVisible(true);
        desktop.add(sobre);
    }//GEN-LAST:event_menSobreActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        frm_cliente cliente = new frm_cliente();
        cliente.setVisible(true);
        desktop.add(cliente);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        frm_OS OS = new frm_OS();
        OS.setVisible(true);
        desktop.add(OS);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void menCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadActionPerformed

    }//GEN-LAST:event_menCadActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        frm_tabela tabela = new frm_tabela();
        tabela.setVisible(true);
        desktop.add(tabela);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        rltr_cliente();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void menRelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRelActionPerformed
        
    }//GEN-LAST:event_menRelActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        rltr_servico();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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
            java.util.logging.Logger.getLogger(frm_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktop;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private javax.swing.JPopupMenu jPopupMenu5;
    private javax.swing.JPopupMenu jPopupMenu6;
    private javax.swing.JPopupMenu jPopupMenu7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenu jsair;
    public static javax.swing.JLabel labelUs;
    private javax.swing.JLabel lbdata;
    private javax.swing.JLabel lbdata1;
    public static javax.swing.JLabel lblUsuario;
    public static javax.swing.JMenu menCad;
    public static javax.swing.JMenuItem menCadUsu;
    public static javax.swing.JMenu menRel;
    private javax.swing.JMenu menSobre;
    private javax.swing.JMenuBar menu;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.Menu menu3;
    private java.awt.Menu menu4;
    private java.awt.Menu menu5;
    private java.awt.Menu menu6;
    private java.awt.MenuBar menuBar1;
    private java.awt.MenuBar menuBar2;
    private java.awt.MenuBar menuBar3;
    private java.util.List<frm.Os> osList;
    private java.util.List<frm.Os> osList1;
    private javax.persistence.Query osQuery;
    private javax.persistence.Query osQuery1;
    private java.awt.PopupMenu popupMenu1;
    // End of variables declaration//GEN-END:variables
}
