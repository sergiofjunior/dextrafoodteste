package com.dextrafoodtest.controller;

import com.dextrafoodtest.model.Burger;
import com.dextrafoodtest.model.Ingrediente;
import com.dextrafoodtest.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.text.DecimalFormat;



/**
 * Created by Sérgio on 19/04/2017.
 * Controller - recebe dados da view Home.jsp e retorna dados para a mesma.
 */
@Controller
public class HomeController {

    @Autowired
    HomeService homeService;

    private DecimalFormat df = new DecimalFormat("0.00");

    @ResponseBody
    @RequestMapping(value = "/calcular", method = RequestMethod.POST)
    public String calcular(@RequestParam(value="idBurger") Integer idBurger, @RequestParam(value="ingredientes[]") Integer[] idEQtdIngredientes) {
        String result = "<p class=\"text-font\">Seu lanche custa: R$ "  + df.format(homeService.calculaPedido(idBurger, idEQtdIngredientes)) + "</p>";
        return result;
    }

    @RequestMapping (value = "/")
    public ModelAndView home(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        ArrayList<Ingrediente> ingredientes = homeService.buscaIngredientes();
        ArrayList<Burger> burgers = homeService.buscaBurgers();
        mav.addObject("burgers", burgers);
        mav.addObject("ingredientes", ingredientes);
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/calculaSomenteBurger", method = RequestMethod.POST)
    public String calculaSomenteBurger(@RequestParam(value="idBurger") Integer idBurger) {
        String result = "<p class=\"text-font\">Seu lanche custa: R$ " + df.format(homeService.calculaSomenteBurger(idBurger)) + "</p>";
        return result;
    }
}
