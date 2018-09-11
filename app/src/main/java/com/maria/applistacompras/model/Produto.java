package com.maria.applistacompras.model;

public class Produto {

    private int id;
    private String nome;
    private double quantidade;
    private Categoria categoria;

    public Produto() {
    }

    public Produto(int id, String nome, double preco, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return nome;

    }
}
