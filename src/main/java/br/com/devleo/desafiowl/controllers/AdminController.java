package br.com.devleo.desafiowl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.devleo.desafiowl.models.Item;
import br.com.devleo.desafiowl.models.User;
import br.com.devleo.desafiowl.repositories.ItemRepository;
import br.com.devleo.desafiowl.repositories.UserRepository;

@Controller
public class AdminController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/admin")
    public ModelAndView listCoffes() {
        ModelAndView mv = new ModelAndView("admin");
        Iterable<Item> items = itemRepository.findAll();
        Iterable<User> users = userRepository.findAll();
        mv.addObject("items", items);
        mv.addObject("users", users);
        return mv;
    }
}
