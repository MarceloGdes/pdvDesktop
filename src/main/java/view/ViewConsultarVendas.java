/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.VendaDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import model.Venda;

/**
 *
 * @author Marcelo
 */
public class ViewConsultarVendas extends javax.swing.JFrame {

    
    private VendaDAO vendaDAO;
    private List<Venda> vendas;
    private DefaultTableModel tableModel;
    
    public ViewConsultarVendas() {
        initComponents();
        configurarCamposData();
        
        vendaDAO = new VendaDAO();
        
        carregarTabela();
    }
    //codigo para o formato das datas dd/mm/yyyy
    private void configurarCamposData() {
    try {
        //mascara do formato padrão
        MaskFormatter mascaraData = new MaskFormatter("##/##/####");
        mascaraData.setPlaceholderCharacter('_'); 
        mascaraData.install(jFormattedTextField1);
        
        MaskFormatter mascaraData2 = new MaskFormatter("##/##/####");
        mascaraData2.setPlaceholderCharacter('_');
        mascaraData2.install(jFormattedTextField2);
        
    }catch (ParseException ex) {
        System.err.println("Erro ao adicionar data: " + ex.getMessage());
        }
     }
    
    private void carregarTabela() {
        try {
            tableModel = new DefaultTableModel(
                    new Object[][]{},
                    new String[]{"Id", "Nome Cliente", "Data", "Valor Total"}) {

                //Adicionado para não deixar alterar as células da tabela        
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;                    
                }
            };
            
            tbVendas.setModel(tableModel);
            
            tbVendas.getColumnModel()
                    .getColumn(0)
                    .setPreferredWidth(1);
            
            tbVendas.getColumnModel()
                    .getColumn(1)
                    .setPreferredWidth(300);
            
            tbVendas.getColumnModel()
                    .getColumn(2)
                    .setPreferredWidth(50);
            
            tbVendas.getColumnModel()
                    .getColumn(3)
                    .setPreferredWidth(50);
        } catch (Exception e) {
            Logger.getLogger(this.getName())
                    .log(Level.SEVERE, null, e);
        }
    }
    
    private void atualizaGrid(LocalDate dataIncial, LocalDate dataFinal) {
        try {
            //Utilizado os atributos atTime e atStartOfDay por causa que a coluna na tabela se trata de um timestamp.
            //Sem esses atributos a conulta não retornaria itens no mesmo dia, com horas informadas (BETWEEN '2025-06-08 00:00:00' AND '2025-06-08 00:00:00')
            String sql = "SELECT * FROM public.\"venda\" "
                    + "WHERE \"data\" BETWEEN '" + dataIncial.atStartOfDay()
                    + "' AND '" + dataFinal.atTime(23, 59, 59) + "'";
            
            List<Venda> vendas = vendaDAO.retornarLista(sql);

            //Limpar a tabela
            tableModel.setRowCount(0);

            //Formatando a data do banco para o atributo DATE
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            
            for (Venda venda : vendas) {
                tableModel.addRow(new Object[]{
                    venda.getId(),
                    venda.getCliente().getNome(),
                    formatter.format(venda.getData()),
                    "R$ " + venda.getTotal()
                });
            }
        } catch (Exception e) {
            Logger.getLogger(this.getName())
                    .log(Level.SEVERE, null, e);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbVendas = new javax.swing.JTable();
        btnGerarRelatorioDetalhado = new javax.swing.JButton();
        btnGerarRelatorioVenda = new javax.swing.JButton();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        btnFiltrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tbVendas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbVendas);
        if (tbVendas.getColumnModel().getColumnCount() > 0) {
            tbVendas.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbVendas.getColumnModel().getColumn(1).setPreferredWidth(300);
            tbVendas.getColumnModel().getColumn(2).setPreferredWidth(50);
            tbVendas.getColumnModel().getColumn(3).setPreferredWidth(50);
        }

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
        jLabel1.setText("Dt. Lançamento Inicial");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Dt lançamento Final");

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFiltrar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFiltrar)
                    .addComponent(jLabel2)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        LocalDate dataInicial = LocalDate.parse(dataInicialStr, formatter);
        LocalDate dataFinal = LocalDate.parse(dataFinalStr, formatter);
        
        atualizaGrid(dataInicial, dataFinal);
        
     } catch (java.time.format.DateTimeParseException e) {
        JOptionPane.showMessageDialog(this, "Formato de data inválido. Use DD/MM/AAAA.", "Erro de Data", 
                JOptionPane.ERROR_MESSAGE);
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            LocalDate dataInicial = LocalDate.parse(dataInicialStr, formatter);
            LocalDate dataFinal = LocalDate.parse(dataFinalStr, formatter);
  
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
        int linhaSelecionada = tbVendas.getSelectedRow();
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
            Object idObj = tbVendas.getValueAt(linhaSelecionada, 0);
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

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnGerarRelatorioDetalhado;
    private javax.swing.JButton btnGerarRelatorioVenda;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbVendas;
    // End of variables declaration//GEN-END:variables

    
}
