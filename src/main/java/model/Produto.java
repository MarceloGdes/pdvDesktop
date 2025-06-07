/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dto.ProdutoDto;
import java.math.BigDecimal;

/**
 *
 * @author Marcelo
 */
public class Produto {
    private Integer id;

    private String descricao;

    private BigDecimal valor;

    private String categoria;

    public Produto() {
    }

    public Produto(ProdutoDto dto){
        this.id = dto.getId();
        this.descricao = dto.getDescricao();
        this.valor = dto.getValor();
        this.categoria = dto.getCategoria();
    }

    public Produto(Integer id, String descricao, BigDecimal valor, String categoria) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    
}
