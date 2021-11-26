package br.com.devleo.desafiowl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.devleo.desafiowl.models.User;
import br.com.devleo.desafiowl.repositories.UserRepository;

@Controller
public class NewUserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
    public String user() {
        return "userCadastro";
    }

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public String user(User user) {
        String cpf = user.getCpf().replace(".", "").replace("-", "");
        user.setAdmin(false);
        if (userRepository.findUserByEmail(user.getEmail()) != null
                || userRepository.findUserByCpf(cpf) != null) {
            return "redirect:/cadastrar";
        }
        userRepository.save(user);
        return "redirect:/";
    }
}
