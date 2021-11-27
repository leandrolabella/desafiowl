package br.com.devleo.desafiowl.controllers;

import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.devleo.desafiowl.models.Coffee;
import br.com.devleo.desafiowl.models.Item;
import br.com.devleo.desafiowl.repositories.CoffeeRepository;
import br.com.devleo.desafiowl.repositories.ItemRepository;
import br.com.devleo.desafiowl.repositories.UserRepository;

@Controller
public class CoffeeController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public ModelAndView listCoffes() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
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
    public String participar(@Valid Coffee coffee, BindingResult result, RedirectAttributes attributes) {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = securityContext.getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return "login";
        }
        Object principal = securityContext.getAuthentication().getPrincipal();
        coffee.setUser(userRepository.findUserByEmail(((UserDetails) principal).getUsername()));
        if (result.hasErrors()) {
            attributes.addFlashAttribute("msg", "" + result.getAllErrors());
            return "redirect:/new";
        }
        if (coffee.getCoffeeDate().compareTo(dt) < 0) {
            attributes.addFlashAttribute("msg", "A data do café deve ser maior que a data de hoje.");
            return "redirect:/new";
        }
        Item item = itemRepository.getById(coffee.getItem().getId());
        if (item == null) {
            attributes.addFlashAttribute("msg", "Não foi encontrado um item com este ID, tente novamente.");
            return "redirect:/new";
        }
        if (coffeeRepository.containsItem(coffee.getCoffeeDate(), item)) {
            attributes.addFlashAttribute("msg", "Outro colaborador já vai levar este produto, escolha outro.");
            return "redirect:/new";
        }
        coffeeRepository.save(coffee);
        attributes.addFlashAttribute("msg", "Sucesso!");
        return "redirect:/new";
    }
}
