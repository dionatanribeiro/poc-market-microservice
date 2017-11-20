package br.com.mercado.itemservice.repository;

import br.com.mercado.itemservice.model.Item;
import br.com.mercado.itemservice.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByName(String name);

    List<Item> findByUsuario(Usuario usuario);

}
