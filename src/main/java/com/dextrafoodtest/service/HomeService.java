package com.dextrafoodtest.service;

import com.dextrafoodtest.model.Burger;
import com.dextrafoodtest.model.Ingrediente;
import com.dextrafoodtest.util.DataBase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sérgio on 19/04/2017.
 */
@Service
public class HomeService {

    DataBase dataBase = new DataBase();

    public ArrayList<Burger> buscaBurgers(){
        return dataBase.listaBurgersCardapio();
    }

    public ArrayList<Ingrediente> buscaIngredientes(){
        return dataBase.listaIngredientesCardapio();
    }

    public Double calculaPedido(Burger burger, List<Ingrediente> listaIngredientes){
        String teste = "teste";
        return null;
    }

}
