package br.com.mercado.userservice.service;

import br.com.mercado.userservice.model.Usuario;

public interface UsuarioService {

    Usuario criarUsuario(Usuario usuario);

    Usuario getUsuarioByUsername(String username);

}
