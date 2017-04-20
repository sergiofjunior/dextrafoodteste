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

/**
 * Created by Sérgio on 19/04/2017.
 */
@Controller
public class HomeController {

    @Autowired
    HomeService homeService;

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
    @RequestMapping(value = "/calcular", method = RequestMethod.POST)
    public String calcular(@RequestBody String burger) {
        String result = "Seu lanche custará xxxxx ";
        return result;
    }

}
