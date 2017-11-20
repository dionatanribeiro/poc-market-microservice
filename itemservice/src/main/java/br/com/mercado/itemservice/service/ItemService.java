package br.com.mercado.itemservice.service;

import br.com.mercado.itemservice.model.Item;
import br.com.mercado.itemservice.model.Usuario;

import java.io.IOException;
import java.util.List;

public interface ItemService {

    Item adicionarItemPorUsuario(Item item, String username);

    List<Item> getAllItems();

    List<Item> getItemsByUsername(String username);

    Item getItemById(Long idItem);

    Item updateItem(Item item) throws IOException;

    void deleteItemById(Long idItem);

    Usuario getUsuarioByUsername(String username);
}
