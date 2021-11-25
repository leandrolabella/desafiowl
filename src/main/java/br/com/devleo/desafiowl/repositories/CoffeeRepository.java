package br.com.devleo.desafiowl.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.devleo.desafiowl.models.Coffee;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

    @Query("SELECT c FROM Coffee c WHERE c.coffeeDate = :currentDate")
    public List<Coffee> findAllByDate(@Param("currentDate") Date currentDate);

}
