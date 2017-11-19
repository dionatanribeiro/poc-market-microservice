package br.com.mercado.pedido.dto;

import java.math.BigDecimal;

public class ProdutoApiDto {

    private Long id;

    private String nome;

    private Integer quantidade;

    private BigDecimal valor;

    private BigDecimal valorTotal;

    public ProdutoApiDto() {
    }

    public ProdutoApiDto(Long id, String nome, Integer quantidade, BigDecimal valor, BigDecimal valorTotal) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.valorTotal = valorTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
