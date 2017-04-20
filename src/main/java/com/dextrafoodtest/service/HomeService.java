package com.dextrafoodtest.service;

import com.dextrafoodtest.model.Burger;
import com.dextrafoodtest.model.Ingrediente;
import com.dextrafoodtest.util.DataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sérgio on 19/04/2017.
 * Service - contém os métodos public que são chamados através do HomeController
 */
@Service
public class HomeService {

    @Autowired
    DataBase dataBase;

    public ArrayList<Burger> buscaBurgers(){
        return dataBase.listaBurgersCardapio();
    }

    public ArrayList<Ingrediente> buscaIngredientes(){
        return dataBase.listaIngredientesCardapio();
    }

    public Double calculaPedido(Integer idBurger, Integer[] idEQtdIngredientes){
        Double valorTotalBurger = 0.0;
        Burger burger = dataBase.buscaBurger(idBurger);
        List<Ingrediente> ingredientes = dataBase.buscaIngredientes(idEQtdIngredientes);
        valorTotalBurger = calculaValorSemDesconto(burger, ingredientes);
        valorTotalBurger = descontoPromocaoLight(burger, ingredientes, valorTotalBurger);
        valorTotalBurger = valorTotalBurger - descontoPorcoesCarne(ingredientes);
        valorTotalBurger = valorTotalBurger - descontoPorcoesQueijo(ingredientes);
        return valorTotalBurger;
    }

    public Double calculaSomenteBurger(Integer idBurger){
        Burger burger = dataBase.buscaBurger(idBurger);
        return burger.getPreco();
    }

    private Double calculaValorSemDesconto(Burger burger, List<Ingrediente> ingredientes){
        Double valorIngredientes = 0.0;
        for(Ingrediente ingrediente: ingredientes){
            valorIngredientes = valorIngredientes + (ingrediente.getPreco() * ingrediente.getQtd());
        }
        return valorIngredientes + burger.getPreco();
    }

    private Double descontoPromocaoLight(Burger burger, List<Ingrediente> ingredientes, Double valorTotalBurger){
        if(ingredientes.contains(dataBase.getIngrediente01())
                && (!burger.getIngredientes().contains(dataBase.getIngrediente02())
                && !ingredientes.contains(dataBase.getIngrediente02()))){
            valorTotalBurger = valorTotalBurger * 0.9;
        }
        return valorTotalBurger;
    }

    private Double descontoPorcoesCarne(List<Ingrediente> ingredientes){
        int qtdPorcoesCarne = 1;
        Double valordesconto = 0.0;
        if(ingredientes.contains(dataBase.getIngrediente03())){
            for(Ingrediente ingrediente : ingredientes){
                if(ingrediente.getId().equals(dataBase.getIngrediente03().getId())){
                    qtdPorcoesCarne = qtdPorcoesCarne + ingrediente.getQtd();
                    break;
                }
            }
        }

        if(qtdPorcoesCarne > 2){
            int qtdDesconto = qtdPorcoesCarne / 3;
            valordesconto = dataBase.getIngrediente03().getPreco() * qtdDesconto;
        }

        return valordesconto;
    }

    private Double descontoPorcoesQueijo(List<Ingrediente> ingredientes){
        int qtdPorcoesQueijo = 1;
        Double valordesconto = 0.0;
        if(ingredientes.contains(dataBase.getIngrediente05())){
            for(Ingrediente ingrediente : ingredientes){
                if(ingrediente.getId().equals(dataBase.getIngrediente05().getId())){
                    qtdPorcoesQueijo = qtdPorcoesQueijo + ingrediente.getQtd();
                    break;
                }
            }
        }

        if(qtdPorcoesQueijo > 2){
            int qtdDesconto = qtdPorcoesQueijo / 3;
            valordesconto = dataBase.getIngrediente05().getPreco() * qtdDesconto;
        }

        return valordesconto;
    }

}
