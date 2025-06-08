/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import model.ItemVenda;
/**
 *
 * @author rgmac
 */
public class VendaDto {
    private Integer id;
    private String observacao;
    private Date data;
    private BigDecimal total;
    private Integer clienteId;
    private List<ItemVenda> itensVenda;

    public VendaDto() {
    }

    public VendaDto(Integer id, String observacao, Date data, BigDecimal total, Integer clienteId, List<ItemVenda> itensVenda) {
        this.id = id;
        this.observacao = observacao;
        this.data = data;
        this.total = total;
        this.clienteId = clienteId;
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

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    
    
    
}
