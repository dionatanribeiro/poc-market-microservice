package br.com.mercado.itemservice.service;

import br.com.mercado.itemservice.model.Usuario;

public interface UsuarioService {

    Usuario getUsuarioByUsername(String username);

}
