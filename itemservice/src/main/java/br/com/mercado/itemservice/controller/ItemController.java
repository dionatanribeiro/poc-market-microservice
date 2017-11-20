package br.com.mercado.itemservice.controller;

import br.com.mercado.itemservice.model.Item;
import br.com.mercado.itemservice.model.Usuario;
import br.com.mercado.itemservice.service.ItemService;
import br.com.mercado.itemservice.service.UsuarioService;
import br.com.mercado.itemservice.util.UserContext;
import br.com.mercado.itemservice.util.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/itens")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios/{username}")
    public Item criarItemPorUsuario(@PathVariable String username, @RequestBody Item item) {
        return itemService.adicionarItemPorUsuario(item, username);
    }

    @GetMapping("/usuarios/{username}")
    public List<Item> buscarTodosItensPorUsuario(@PathVariable String username) {
        return itemService.getItemsByUsername(username);
    }

    @GetMapping
    public List<Item> buscarTodosItens() {
        return itemService.getAllItems();
    }

    @GetMapping("/{idItem}")
    public Item buscarItemPorId(@PathVariable Long idItem) {
        return itemService.getItemById(idItem);
    }

    @PutMapping("/{idItem}")
    public Item atualizarItemPorId(@PathVariable Long idItem, @RequestBody Item item) throws IOException {
        item.setId(idItem);
        return itemService.updateItem(item);
    }

    @DeleteMapping("/{idItem}")
    public void excluirItemPorId(@PathVariable Long idItem) {
        itemService.deleteItemById(idItem);
    }

    @GetMapping("/usuarios/buscar/{username}")
    public Usuario buscarUsuario(@PathVariable String username) {
        logger.debug("ItemServiceController Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        return itemService.getUsuarioByUsername(username);
    }

}
