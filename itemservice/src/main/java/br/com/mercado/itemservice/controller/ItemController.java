package br.com.mercado.itemservice.controller;

import br.com.mercado.itemservice.model.Item;
import br.com.mercado.itemservice.model.Usuario;
import br.com.mercado.itemservice.service.ItemService;
import br.com.mercado.itemservice.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/itens")
public class ItemController {

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
        return itemService.getUsuarioByUsername(username);
    }

}
