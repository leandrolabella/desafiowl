package br.com.devleo.desafiowl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.devleo.desafiowl.models.Item;
import br.com.devleo.desafiowl.models.User;
import br.com.devleo.desafiowl.repositories.CoffeeRepository;
import br.com.devleo.desafiowl.repositories.ItemRepository;
import br.com.devleo.desafiowl.repositories.UserRepository;

@Controller
public class AdminController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoffeeRepository coffeeRepository;

    @RequestMapping("/admin")
    public ModelAndView listValues() {
        ModelAndView mv = new ModelAndView("admin");
        Iterable<Item> items = itemRepository.findAll();
        Iterable<User> users = userRepository.findAll();
        mv.addObject("items", items);
        mv.addObject("users", users);
        return mv;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String newItem(Item item) {
        itemRepository.save(item);
        return "redirect:/admin";
    }

    @RequestMapping("/remove")
    public String delItem(long id) {
        Item item = itemRepository.getById(id);
        coffeeRepository.deleteAllid(item);
        itemRepository.delete(item);
        return "redirect:/admin";
    }

    @RequestMapping("/removeAll")
    public String delAllItems() {
        coffeeRepository.deleteAll();
        itemRepository.deleteAll();
        return "redirect:/admin";
    }
}
