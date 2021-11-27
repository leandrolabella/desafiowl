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

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Coffee c WHERE c.coffeeDate = :currentDate AND c.itemId = :itemId")
    public boolean containsItem(@Param("currentDate") Date currentDate, @Param("itemId") String itemId);

}
