package com.dextrafoodtest.util;

import com.dextrafoodtest.model.Burger;
import com.dextrafoodtest.model.Ingrediente;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sérgio on 19/04/2017.
 * Classe utilizada somente para ter uma base de objetos em memória
 */
@Service
public class DataBase {

    private Burger burger01;

    private Burger burger02;

    private Burger burger03;

    private Burger burger04;

    private Ingrediente ingrediente01;

    private Ingrediente ingrediente02;

    private Ingrediente ingrediente03;

    private Ingrediente ingrediente04;

    private Ingrediente ingrediente05;

    public ArrayList<Burger> listaBurgersCardapio(){
        ArrayList burgers = new ArrayList();
        Double preco = 0.0;

        burger01 = new Burger();
        burger01.setId(01);
        burger01.setNome("X-Bacon");
        List<Ingrediente> ingredientesBurger01 = new ArrayList<Ingrediente>();
        ingredientesBurger01.add(getIngrediente02());
        ingredientesBurger01.add(getIngrediente03());
        ingredientesBurger01.add(getIngrediente05());
        for(Ingrediente ingrediente: ingredientesBurger01){
            preco = preco + ingrediente.getPreco();
        }
        burger01.setPreco(preco);
        burger01.setIngredientes(ingredientesBurger01);

        burger02 = new Burger();
        burger02.setId(02);
        burger02.setNome("X-Burger");
        List<Ingrediente> ingredientesBurger02 = new ArrayList<Ingrediente>();
        ingredientesBurger02.add(getIngrediente03());
        ingredientesBurger02.add(getIngrediente05());
        preco = 0.0;
        for(Ingrediente ingrediente: ingredientesBurger02){
            preco = preco + ingrediente.getPreco();
        }
        burger02.setPreco(preco);
        burger02.setIngredientes(ingredientesBurger02);

        burger03 = new Burger();
        burger03.setId(03);
        burger03.setNome("X-Egg");
        List<Ingrediente> ingredientesBurger03 = new ArrayList<Ingrediente>();
        ingredientesBurger03.add(getIngrediente03());
        ingredientesBurger03.add(getIngrediente04());
        ingredientesBurger03.add(getIngrediente05());
        preco = 0.0;
        for(Ingrediente ingrediente: ingredientesBurger03){
            preco = preco + ingrediente.getPreco();
        }
        burger03.setPreco(preco);
        burger03.setIngredientes(ingredientesBurger03);

        burger04 = new Burger();
        burger04.setId(04);
        burger04.setNome("X-Egg Bacon");
        List<Ingrediente> ingredientesBurger04 = new ArrayList<Ingrediente>();
        ingredientesBurger04.add(getIngrediente02());
        ingredientesBurger04.add(getIngrediente03());
        ingredientesBurger04.add(getIngrediente04());
        ingredientesBurger04.add(getIngrediente05());
        preco = 0.0;
        for(Ingrediente ingrediente: ingredientesBurger04){
            preco = preco + ingrediente.getPreco();
        }
        burger04.setPreco(preco);
        burger04.setIngredientes(ingredientesBurger04);

        burgers.add(burger01);
        burgers.add(burger02);
        burgers.add(burger03);
        burgers.add(burger04);

        return burgers;
    }

    public ArrayList<Ingrediente> listaIngredientesCardapio(){

        ArrayList ingredientes = new ArrayList();
        ingrediente01 = new Ingrediente(1,"Alface", 0.40);
        ingrediente02 = new Ingrediente(2,"Bacon", 2.00);
        ingrediente03 = new Ingrediente(3,"Hamburger de Carne", 3.00);
        ingrediente04 = new Ingrediente(4,"Ovo", 0.80);
        ingrediente05 = new Ingrediente(5,"Queijo", 1.50);

        ingredientes.add(ingrediente01);
        ingredientes.add(ingrediente02);
        ingredientes.add(ingrediente03);
        ingredientes.add(ingrediente04);
        ingredientes.add(ingrediente05);

        return ingredientes;
    }

    public Burger buscaBurger(Integer idBurger){
        if(idBurger.equals(1)){
            return burger01;
        }else if (idBurger.equals(2)){
            return burger02;
        }else if (idBurger.equals(3)){
            return burger03;
        }else {
            return burger04;
        }
    }


    public List<Ingrediente> buscaIngredientes(Integer[] idEQtdIngredientes) {
        Ingrediente ingrediente;
        List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

        for(int i=0; i<idEQtdIngredientes.length; i++){
            if(idEQtdIngredientes[i].equals(1)){
                ingrediente01.setQtd(idEQtdIngredientes[i+1]);
                ingredientes.add(ingrediente01);
            }
            else if(idEQtdIngredientes[i].equals(2)){
                ingrediente02.setQtd(idEQtdIngredientes[i+1]);
                ingredientes.add(ingrediente02);
            }
            else if(idEQtdIngredientes[i].equals(3)){
                ingrediente03.setQtd(idEQtdIngredientes[i+1]);
                ingredientes.add(ingrediente03);
            }
            else if(idEQtdIngredientes[i].equals(4)){
                ingrediente04.setQtd(idEQtdIngredientes[i+1]);
                ingredientes.add(ingrediente04);
            }
            else if(idEQtdIngredientes[i].equals(5)){
                ingrediente05.setQtd(idEQtdIngredientes[i+1]);
                ingredientes.add(ingrediente05);
            }
            i = i + 1;
        }
        return ingredientes;
    }


    public Burger getBurger01() {
        return burger01;
    }

    public void setBurger01(Burger burger01) {
        this.burger01 = burger01;
    }

    public Burger getBurger02() {
        return burger02;
    }

    public void setBurger02(Burger burger02) {
        this.burger02 = burger02;
    }

    public Burger getBurger03() {
        return burger03;
    }

    public void setBurger03(Burger burger03) {
        this.burger03 = burger03;
    }

    public Burger getBurger04() {
        return burger04;
    }

    public void setBurger04(Burger burger04) {
        this.burger04 = burger04;
    }

    public Ingrediente getIngrediente01() {
        return ingrediente01;
    }

    public void setIngrediente01(Ingrediente ingrediente01) {
        this.ingrediente01 = ingrediente01;
    }

    public Ingrediente getIngrediente02() {
        return ingrediente02;
    }

    public void setIngrediente02(Ingrediente ingrediente02) {
        this.ingrediente02 = ingrediente02;
    }

    public Ingrediente getIngrediente03() {
        return ingrediente03;
    }

    public void setIngrediente03(Ingrediente ingrediente03) {
        this.ingrediente03 = ingrediente03;
    }

    public Ingrediente getIngrediente04() {
        return ingrediente04;
    }

    public void setIngrediente04(Ingrediente ingrediente04) {
        this.ingrediente04 = ingrediente04;
    }

    public Ingrediente getIngrediente05() {
        return ingrediente05;
    }

    public void setIngrediente05(Ingrediente ingrediente05) {
        this.ingrediente05 = ingrediente05;
    }

}
