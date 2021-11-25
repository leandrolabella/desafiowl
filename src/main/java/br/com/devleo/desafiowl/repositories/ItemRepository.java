package br.com.devleo.desafiowl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devleo.desafiowl.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
