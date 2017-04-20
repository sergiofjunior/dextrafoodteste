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

    /*
    Calcula valor do pedido final, aplicando todas as promoções.
     */
    public Double calculaPedido(Integer idBurger, Integer[] idEQtdIngredientes){
        Double valorTotalBurger = 0.0;
        Burger burger = dataBase.buscaBurger(idBurger);
        List<Ingrediente> ingredientes = dataBase.buscaIngredientes(idEQtdIngredientes);
        valorTotalBurger = calculaValorSemDesconto(burger, ingredientes);
        valorTotalBurger = valorTotalBurger - descontoPorcoesCarne(ingredientes);
        valorTotalBurger = descontoPromocaoLight(burger, ingredientes, valorTotalBurger);
        valorTotalBurger = valorTotalBurger - descontoPorcoesQueijo(ingredientes);
        return valorTotalBurger;
    }

    /*
    Calcula somente o valor padrão do hamburguer, com os ingredientes default.
     */
    public Double calculaSomenteBurger(Integer idBurger){
        Burger burger = dataBase.buscaBurger(idBurger);
        return burger.getPreco();
    }

    /*
    Calcula Valor total do Lanche, sem nenhum desconto e com todos ingredientes
     */
    private Double calculaValorSemDesconto(Burger burger, List<Ingrediente> ingredientes){
        Double valorIngredientes = 0.0;
        for(Ingrediente ingrediente: ingredientes){
            valorIngredientes = valorIngredientes + (ingrediente.getPreco() * ingrediente.getQtd());
        }
        return valorIngredientes + burger.getPreco();
    }

    /*
    Valida se Contain o Ingrediente 01 e se não Contem o Ingrediente 02
     */
    private Double descontoPromocaoLight(Burger burger, List<Ingrediente> ingredientes, Double valorTotalBurger){
        if(ingredientes.contains(dataBase.getIngrediente01())) {
            if(!(burger.getIngredientes().contains(dataBase.getIngrediente02()) || burger.getIngredientes().contains(dataBase.getIngrediente02()))){
                valorTotalBurger = valorTotalBurger * 0.9;
            }
        }
        return  valorTotalBurger;
    }

    /*
    Aplica os descontos da Promoção de Porções de Carne conforme quantidade
     */
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

    /*
    Aplica os descontos da Promoção de Porções de Queijo conforme quantidade
     */
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
