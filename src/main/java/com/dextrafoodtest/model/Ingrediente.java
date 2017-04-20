package com.dextrafoodtest.model;

/**
 * Created by Sérgio on 19/04/2017.
 */
public class Ingrediente {

    private Integer id;

    private String nome;

    private Double preco;

    public Ingrediente(Integer id, String nomeIngrediente, Double preco){
        this.setId(id);
        this.setNome(nomeIngrediente);
        this.setPreco(preco);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                '}';
    }
}
