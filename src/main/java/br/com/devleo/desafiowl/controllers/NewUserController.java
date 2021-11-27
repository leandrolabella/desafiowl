package br.com.devleo.desafiowl.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String user(@Valid User user, BindingResult result, RedirectAttributes attributes) {
        String cpf = user.getCpf().replace(".", "").replace("-", "");
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setAdmin(false);
        if (result.hasErrors()) {
            attributes.addFlashAttribute("msg", "" + result.getAllErrors());
            return "redirect:/cadastrar";
        }
        if (userRepository.findUserByEmail(user.getEmail()) != null
                || userRepository.findUserByCpf(cpf) != null) {
            attributes.addFlashAttribute("msg", "Já existe um usuário cadastrado com estes dados.");
            return "redirect:/cadastrar";
        }
        userRepository.save(user);
        return "redirect:/";
    }
}
