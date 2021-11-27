package br.com.devleo.desafiowl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.devleo.desafiowl.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM Item i WHERE i.id = :itemId")
    public boolean containsItem(@Param("itemId") Long itemId);

}
