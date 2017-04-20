package com.dextrafoodtest.model;

import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 * Created by Sérgio on 19/04/2017.
 */
public class Burger {

    private static final String SEPARADOR_VIRGULA = ", ";
    private static final String SEPARADOR_E = " e ";

    private Integer id;

    private String nome;

    private Double preco;

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    private List<Ingrediente> ingredientes;

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

    public String mostraIngredientes() {

        String retorno = "";
        for(Ingrediente ingrediente: ingredientes){
            if(ingrediente.equals(ingredientes.get(ingredientes.size() -2))){
                retorno += ingrediente.getNome() + SEPARADOR_E ;
            }else if(ingrediente.equals(ingredientes.get(ingredientes.size() -1))){
                retorno += ingrediente.getNome();
            }else{
                retorno += ingrediente.getNome() + SEPARADOR_VIRGULA;
            }
        }
        return retorno;
    }

    @Override
    public String toString() {
        return "Burger{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", ingredientes=" + ingredientes +
                '}';
    }

}
