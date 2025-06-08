/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import Service.ClienteService;
import Service.ProdutoService;
import Service.VendaService;
import dto.VendaDto;
import exceptions.ApiRequestException;
import exceptions.BusinessException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.ItemVenda;
import model.Produto;
import model.Venda;

/**
 *
 * @author Marlene Juliana
 */
public class ViewLancarVenda extends javax.swing.JFrame {

    private Venda venda;
    private ItemVenda itemVenda = null;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;
    
    public ViewLancarVenda() {
        initComponents();
        venda = new Venda();
        carregarTabela();
    }
    
    private void carregarTabela() {
        try {
            tableModel = new DefaultTableModel(
                    new Object[][]{},
                    new String[]{"id", "Produto", "Qtd", "Valor Un.", "Valor Tot."}
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            tbProdutos.setModel(tableModel);
            
            tbProdutos.getColumnModel()
                    .getColumn(0)
                    .setPreferredWidth(1);
            
            tbProdutos.getColumnModel()
                    .getColumn(1)
                    .setPreferredWidth(300);
            
            tbProdutos.getColumnModel()
                    .getColumn(2)
                    .setPreferredWidth(1);
            
            tbProdutos.getColumnModel()
                    .getColumn(3)
                    .setPreferredWidth(20);
            
            tbProdutos.getColumnModel()
                    .getColumn(4)
                    .setPreferredWidth(20);
            
        } catch (Exception e) {
            Logger.getLogger(this.getName())
                    .log(Level.SEVERE, null, e);
        }
    }
    
    private void atualizaGrid() {
        try {
            //Limpa a tabela
            tableModel.setRowCount(0);
            
            for (ItemVenda itemvenda : venda.getItensVenda()) {
                tableModel.addRow(new Object[]{
                    itemVenda.getProduto().getId(),
                    itemVenda.getProduto().getDescricao(),
                    itemVenda.getQuantidade(),
                    "R$ " + itemVenda.getValorUnitario(),
                    "R$ " + itemVenda.getValorTotal()                
                });
            }
            
            venda.calcValorTotal();
            tfValorTotal.setText("R$ " + venda.getTotal());
            
        } catch (Exception e) {
            Logger.getLogger(this.getName())
                    .log(Level.SEVERE, null, e);
        }
    } 
    
    private void limparCamposProduto(){
        tfQuantidade.setText("");
        tfSelecionarProduto.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SelecioneCliente = new javax.swing.JLabel();
        SelecioneProduto = new javax.swing.JLabel();
        Quantidade = new javax.swing.JLabel();
        tfQuantidade = new javax.swing.JTextField();
        txtTelaVendas = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbProdutos = new javax.swing.JTable();
        btnAdicionarItem = new javax.swing.JButton();
        Telefone = new javax.swing.JLabel();
        tfTelefone = new javax.swing.JTextField();
        ValorTotal = new javax.swing.JLabel();
        tfValorTotal = new javax.swing.JTextField();
        Observacao = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taObservacao = new javax.swing.JTextArea();
        btnFinalizarVenda = new javax.swing.JButton();
        tfSelecionarCliente = new javax.swing.JTextField();
        tfSelecionarProduto = new javax.swing.JTextField();
        Email = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btnRemoverItem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        SelecioneCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SelecioneCliente.setText("Cliente");

        SelecioneProduto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SelecioneProduto.setText("Produto");

        Quantidade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Quantidade.setText("Qtd");

        tfQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfQuantidadeActionPerformed(evt);
            }
        });

        txtTelaVendas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtTelaVendas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTelaVendas.setText("Lançar Venda");

        tbProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Produtos", "Quantidade", "Valor Unitario", "Valor total"
            }
        ));
        jScrollPane2.setViewportView(tbProdutos);
        if (tbProdutos.getColumnModel().getColumnCount() > 0) {
            tbProdutos.getColumnModel().getColumn(0).setPreferredWidth(300);
            tbProdutos.getColumnModel().getColumn(1).setPreferredWidth(5);
            tbProdutos.getColumnModel().getColumn(2).setPreferredWidth(20);
            tbProdutos.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        btnAdicionarItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdicionarItem.setText("Adicionar Item  ");
        btnAdicionarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarItemActionPerformed(evt);
            }
        });

        Telefone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Telefone.setText("Telefone");

        tfTelefone.setEditable(false);

        ValorTotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ValorTotal.setText("Valor Total");

        tfValorTotal.setEditable(false);
        tfValorTotal.setText("R$ 0.00");
        tfValorTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfValorTotalActionPerformed(evt);
            }
        });

        Observacao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Observacao.setText("Observação");

        taObservacao.setColumns(20);
        taObservacao.setRows(5);
        jScrollPane3.setViewportView(taObservacao);

        btnFinalizarVenda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFinalizarVenda.setText("Finalizar Venda");
        btnFinalizarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarVendaActionPerformed(evt);
            }
        });

        tfSelecionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSelecionarClienteActionPerformed(evt);
            }
        });
        tfSelecionarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfSelecionarClienteKeyPressed(evt);
            }
        });

        tfSelecionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSelecionarProdutoActionPerformed(evt);
            }
        });
        tfSelecionarProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfSelecionarProdutoKeyPressed(evt);
            }
        });

        Email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Email.setText("E-mail");

        tfEmail.setEditable(false);
        tfEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEmailActionPerformed(evt);
            }
        });

        btnRemoverItem.setText("Remover Item");
        btnRemoverItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRemoverItem))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(txtTelaVendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnFinalizarVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                    .addComponent(tfValorTotal)
                                    .addComponent(ValorTotal)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SelecioneCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfSelecionarCliente))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Telefone)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfEmail))
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Observacao, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(SelecioneProduto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tfSelecionarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(Quantidade)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnAdicionarItem)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(txtTelaVendas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SelecioneCliente)
                    .addComponent(tfSelecionarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Telefone)
                    .addComponent(tfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Email)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SelecioneProduto)
                        .addComponent(tfSelecionarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAdicionarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoverItem)
                .addGap(7, 7, 7)
                .addComponent(Observacao)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ValorTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFinalizarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfQuantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfQuantidadeActionPerformed

    private void btnAdicionarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarItemActionPerformed
        try {
            if(itemVenda == null)
                throw new BusinessException("Você não selecionou um produto.");

            if(tfQuantidade.getText() == null || tfQuantidade.getText().isBlank())
                throw new BusinessException("Você não preencheu o campo de quantidade.");
            
            Integer quantidade = Integer.valueOf(tfQuantidade.getText());
            
            if(quantidade <= 0)
                throw new BusinessException("A quantidade tem que ser maior que zero.");
            
            itemVenda.setQuantidade(quantidade);
            itemVenda.calcValorTotal();
            
            venda.getItensVenda().add(itemVenda);
            atualizaGrid();
            itemVenda = null;
            limparCamposProduto();
                
        }catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, 
                        ex.getMessage(), 
                        "Atenção.", 
                        JOptionPane.WARNING_MESSAGE);
            
        }catch (Exception ex) {
            Logger.getLogger(this.getClass().getName())
                    .log(Level.SEVERE, null, ex);
            
            JOptionPane.showMessageDialog(this, 
                        "Ocorre um erro.", 
                        "Erro", 
                        JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnAdicionarItemActionPerformed

    private void tfValorTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfValorTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfValorTotalActionPerformed

    private void btnFinalizarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarVendaActionPerformed
        try {
        if (venda.getCliente() == null)
            throw new BusinessException("Você não selecionou um cliente.");
        if (venda.getItensVenda().isEmpty())
            throw new BusinessException("Adicione ao menos um item.");
        
        venda.setObservacao(taObservacao.getText());
        venda.setData(Date.valueOf(LocalDate.now()));
        venda.calcValorTotal(); // Garante que total é calculado

        VendaDto vendaDto = new VendaDto();
        vendaDto.setObservacao(venda.getObservacao());
        vendaDto.setData(venda.getData());
        vendaDto.setTotal(venda.getTotal());
        vendaDto.setClienteId(venda.getCliente().getId());
        vendaDto.setItensVenda(venda.getItensVenda());

        Venda vendaCriada = VendaService.insert(vendaDto);
        
        

        venda = new Venda();
        atualizaGrid();
        limparCamposProduto();
        taObservacao.setText("");
        tfSelecionarCliente.setText("");
        tfTelefone.setText("");
        tfEmail.setText("");

        JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    } catch (BusinessException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Atenção.", JOptionPane.WARNING_MESSAGE);
    } catch (Exception ex) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Ocorre um erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnFinalizarVendaActionPerformed

    private void tfSelecionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSelecionarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSelecionarClienteActionPerformed

    private void tfSelecionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSelecionarProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSelecionarProdutoActionPerformed

    private void tfEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEmailActionPerformed

    private void tfSelecionarClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSelecionarClienteKeyPressed
        // Código de busca de clientes
        
        if(evt.getKeyCode() == evt.VK_ENTER){
            try{
                List<Cliente> clientes = ClienteService
                        .findAll(tfSelecionarCliente.getText());
                
                ViewListarClientes viewListarClientes = new ViewListarClientes(clientes, this);
                viewListarClientes.setLocationRelativeTo(this);
                viewListarClientes.setVisible(true);
            
            }catch (ApiRequestException ex) {
                JOptionPane.showMessageDialog(this, 
                        ex.getMessage(),
                        "Atenção!",
                        JOptionPane.WARNING_MESSAGE);
                
            }catch (Exception ex) {
                Logger.getLogger(this.getClass().getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tfSelecionarClienteKeyPressed

    private void tfSelecionarProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSelecionarProdutoKeyPressed
        // Código de busca de clientes
        
        if(evt.getKeyCode() == evt.VK_ENTER){
            try{
                List<Produto> produtos = ProdutoService
                        .findAll(tfSelecionarProduto.getText());
                
                ViewListarProdutos viewListarProdutos = new ViewListarProdutos(produtos, this);
                viewListarProdutos.setLocationRelativeTo(this);
                viewListarProdutos.setVisible(true);
            
            }catch (ApiRequestException ex) {
                JOptionPane.showMessageDialog(this, 
                        ex.getMessage(),
                        "Atenção!",
                        JOptionPane.WARNING_MESSAGE);
                
            }catch (Exception ex) {
                Logger.getLogger(this.getClass().getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tfSelecionarProdutoKeyPressed

    private void btnRemoverItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverItemActionPerformed
        linhaSelecionada = tbProdutos.getSelectedRow();
        
        if(linhaSelecionada >= 0){
            venda.getItensVenda().remove(linhaSelecionada);
            
            atualizaGrid();
            
            JOptionPane.showMessageDialog(this,
                "Item removido com sucesso.",
                "Remoção",
                JOptionPane.INFORMATION_MESSAGE);
            
            linhaSelecionada = -1;
            
        }else {
            JOptionPane.showMessageDialog(this, 
                    "Selecione um item da tabela.", 
                    "Atenção", 
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnRemoverItemActionPerformed
    
    public void preencherDadosCliente(Cliente cliente){
        venda.setCliente(cliente);
        
        tfSelecionarCliente.setText(cliente.getId() + " - " + cliente.getNome());
        tfTelefone.setText(cliente.getTelefone());
        tfEmail.setText(cliente.getEmail());  
    }
    
    public void preencherDadosProdutos(Produto produto){
          tfSelecionarProduto.setText(produto.getId() + " - " + produto.getDescricao());
    itemVenda = new ItemVenda();
    itemVenda.setProduto(produto);
    itemVenda.setProduto(produto);
    itemVenda.setValorUnitario(produto.getValor());
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Email;
    private javax.swing.JLabel Observacao;
    private javax.swing.JLabel Quantidade;
    private javax.swing.JLabel SelecioneCliente;
    private javax.swing.JLabel SelecioneProduto;
    private javax.swing.JLabel Telefone;
    private javax.swing.JLabel ValorTotal;
    private javax.swing.JButton btnAdicionarItem;
    private javax.swing.JButton btnFinalizarVenda;
    private javax.swing.JButton btnRemoverItem;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea taObservacao;
    private javax.swing.JTable tbProdutos;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfQuantidade;
    private javax.swing.JTextField tfSelecionarCliente;
    private javax.swing.JTextField tfSelecionarProduto;
    private javax.swing.JTextField tfTelefone;
    private javax.swing.JTextField tfValorTotal;
    private javax.swing.JLabel txtTelaVendas;
    // End of variables declaration//GEN-END:variables

    

    
}
