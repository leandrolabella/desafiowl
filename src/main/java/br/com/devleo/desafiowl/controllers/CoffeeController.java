package br.com.devleo.desafiowl.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.devleo.desafiowl.models.Coffee;
import br.com.devleo.desafiowl.repositories.CoffeeRepository;

@Controller
public class CoffeeController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @RequestMapping("/coffee")
    public ModelAndView listCoffes() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        ModelAndView mv = new ModelAndView("index");
        Iterable<Coffee> coffees = coffeeRepository.findAll();// findAllByDate(dt);
        mv.addObject("coffees", coffees);
        return mv;
    }
}
