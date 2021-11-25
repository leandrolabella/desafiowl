package br.com.devleo.desafiowl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.devleo.desafiowl.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User findUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.cpf = :cpf")
    public User findUserByCpf(@Param("cpf") String cpf);

}
