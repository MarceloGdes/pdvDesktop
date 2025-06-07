/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo
 */
public class Venda {
    private Integer id;

    private String observacao;

    private Date data;

    private BigDecimal total;

    private Cliente cliente;

    private List<ItemVenda> itensVenda;

    public Venda() {
        itensVenda = new ArrayList<>();
    }

    public Venda(Integer id, String observacao, Date data, BigDecimal total, Cliente cliente,
                 List<ItemVenda> itensVenda) {
        this.id = id;
        this.observacao = observacao;
        this.data = data;
        this.total = total;
        this.cliente = cliente;
        this.itensVenda = itensVenda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }
}
