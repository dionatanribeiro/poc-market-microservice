package br.com.mercado.userservice.controller;

import br.com.mercado.userservice.model.Usuario;
import br.com.mercado.userservice.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{username}")
    public Usuario getUserByUsername(@PathVariable String username) {
        return usuarioService.getUsuarioByUsername(username);
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.criarUsuario(usuario);
    }

}
