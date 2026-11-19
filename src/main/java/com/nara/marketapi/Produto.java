package com.nara.marketapi;

import java.math.BigDecimal;

public class Produto {

    private String nome;
    private BigDecimal preco;
    private int quantidade;
    private Long id;

    public Produto(){

    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public BigDecimal getPreco() {
        return preco;
    }
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }


}

