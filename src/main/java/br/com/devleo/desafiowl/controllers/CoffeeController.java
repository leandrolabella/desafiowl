package br.com.devleo.desafiowl.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.devleo.desafiowl.models.Coffee;
import br.com.devleo.desafiowl.models.Item;
import br.com.devleo.desafiowl.repositories.CoffeeRepository;
import br.com.devleo.desafiowl.repositories.ItemRepository;

@Controller
public class CoffeeController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping("/")
    public ModelAndView listCoffes() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        String finalDate = new SimpleDateFormat("yyyy-MM-dd").format(dt);
        ModelAndView mv = new ModelAndView("index");
        Iterable<Coffee> coffees = coffeeRepository.findAllByDate(dt);
        mv.addObject("coffees", coffees);
        return mv;
    }

    @RequestMapping("/new")
    public ModelAndView listItems() {
        ModelAndView mv = new ModelAndView("participar");
        Iterable<Item> items = itemRepository.findAll();
        mv.addObject("items", items);
        return mv;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String participar(Coffee coffee) {
        try{
            Item item = itemRepository.getById(Long.parseLong(coffee.getItemId()));
            if (item == null){
                return "";
            }
            if (coffeeRepository.containsItem(coffee.getCoffeeDate(), coffee.getItemId())){
                return "participar";
            }
        } catch (NumberFormatException e){
            return "participar";
        }
        return "participar";
    }
}
