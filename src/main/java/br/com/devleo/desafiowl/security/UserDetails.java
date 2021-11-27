package br.com.devleo.desafiowl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.devleo.desafiowl.models.User;
import br.com.devleo.desafiowl.repositories.UserRepository;

@Repository
public class UserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }
        return user;
    }
}
