/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Marcelo
 */
public class ViewConsultarVendas extends javax.swing.JFrame {

    /**
     * Creates new form ConsultarVendas
     */
    public ViewConsultarVendas() {
        initComponents();
        configurarCamposData();
    }
//codigo para o formato das datas dd/mm/yyyy
    private void configurarCamposData() {
    try {
    
      MaskFormatter mascaraData = new MaskFormatter("##/##/####");
      mascaraData.setPlaceholderCharacter('_'); 
        
      mascaraData.install(jFormattedTextField1);
        
        MaskFormatter mascaraData2 = new MaskFormatter("##/##/####");
        mascaraData2.setPlaceholderCharacter('_');
        mascaraData2.install(jFormattedTextField2);
        
    }   catch (ParseException ex) {
        System.err.println("Erro ao adicionar data: " + ex.getMessage());
        }
     }
  
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaCliente = new javax.swing.JTable();
        btnGerarRelatorioDetalhado = new javax.swing.JButton();
        btnGerarRelatorioVenda = new javax.swing.JButton();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        btnFiltrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TabelaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "CLIENTE", "DATA DA VENDA", "VALOR TOTAL"
            }
        ));
        jScrollPane1.setViewportView(TabelaCliente);

        btnGerarRelatorioDetalhado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGerarRelatorioDetalhado.setText("Gerar Relatório Detalhado");
        btnGerarRelatorioDetalhado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarRelatorioDetalhadoActionPerformed(evt);
            }
        });

        btnGerarRelatorioVenda.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGerarRelatorioVenda.setText("Gerar Relatório Vendas");
        btnGerarRelatorioVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarRelatorioVendaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Data de Lançamento Inicial");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Data de lançamento final");

        jFormattedTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField2ActionPerformed(evt);
            }
        });

        btnFiltrar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGerarRelatorioVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGerarRelatorioDetalhado))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextField2)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFiltrar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGerarRelatorioDetalhado)
                    .addComponent(btnGerarRelatorioVenda))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
    //obter data ao clicar no button de filtro                                             
    String dataInicialStr = jFormattedTextField1.getText();
    String dataFinalStr = jFormattedTextField2.getText();
    
    try {
        java.time.format.DateTimeFormatter formatter =
        java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        java.time.LocalDate dataInicial = 
        java.time.LocalDate.parse(dataInicialStr, formatter);
        java.time.LocalDate dataFinal =
        java.time.LocalDate.parse(dataFinalStr, formatter);
        
        System.out.println("Data Inicial: " + dataInicial);
        System.out.println("Data Final: " + dataFinal);
        
     } catch (java.time.format.DateTimeParseException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Formato de data inválido. Use dd/MM/yyyy.", "Erro de Data", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void jFormattedTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField2ActionPerformed

    private void btnGerarRelatorioVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarRelatorioVendaActionPerformed
    
        String dataInicialStr = jFormattedTextField1.getText();
        String dataFinalStr = jFormattedTextField2.getText();
        
        
        if (dataInicialStr.trim().replace("_", "").length() < 10 || 
            dataFinalStr.trim().replace("_", "").length() < 10) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha as datas inicial e final para gerar o relatório.",
                    "Datas Incompletas",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            java.time.format.DateTimeFormatter formatter =
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
            java.time.LocalDate dataInicial = 
                java.time.LocalDate.parse(dataInicialStr, formatter);
            java.time.LocalDate dataFinal =
                java.time.LocalDate.parse(dataFinalStr, formatter);
  
            if (dataInicial.isAfter(dataFinal)) {
                JOptionPane.showMessageDialog(
                        this,
                        "A data inicial não pode ser posterior à data final.",
                        "Erro de Data",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Gerar o relatório com filtro de data
            relatorios.GerarRelatorio gerador = new relatorios.GerarRelatorio();
            gerador.gerarRelatorioComFiltroData("Relatorio_geral_pdv", dataInicialStr, dataFinalStr);
            
        } catch (java.time.format.DateTimeParseException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Formato de data inválido. Use dd/MM/yyyy.",
                    "Erro de Data",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGerarRelatorioVendaActionPerformed

    private void btnGerarRelatorioDetalhadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarRelatorioDetalhadoActionPerformed
        int linhaSelecionada = TabelaCliente.getSelectedRow();
        if (linhaSelecionada == -1) {
           
            String input = JOptionPane.showInputDialog(
                    this,
                    "Digite o ID da venda para o relatório detalhado:",
                    "Relatório Detalhado",
                    JOptionPane.QUESTION_MESSAGE);
            
            if (input != null && !input.trim().isEmpty()) {
                try {
                    int idVenda = Integer.parseInt(input.trim());
                    relatorios.GerarRelatorio gerador = new relatorios.GerarRelatorio();
                    gerador.gerarRelatorioComParametro("Relatorio_detalhado_pdv", idVenda, "Id_venda_parametro");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Por favor, digite um número válido.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            // Se houver linha selecionada, usar o ID da venda selecionada
            Object idObj = TabelaCliente.getValueAt(linhaSelecionada, 0);
            if (idObj != null) {
                try {
                    int idVenda = Integer.parseInt(idObj.toString());
                    relatorios.GerarRelatorio gerador = new relatorios.GerarRelatorio();
                    gerador.gerarRelatorioComParametro("Relatorio_detalhado_pdv", idVenda, "Id_venda_parametro");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Erro ao obter o ID da venda selecionada.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnGerarRelatorioDetalhadoActionPerformed

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
            java.util.logging.Logger.getLogger(ViewConsultarVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewConsultarVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewConsultarVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewConsultarVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewConsultarVendas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelaCliente;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnGerarRelatorioDetalhado;
    private javax.swing.JButton btnGerarRelatorioVenda;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
