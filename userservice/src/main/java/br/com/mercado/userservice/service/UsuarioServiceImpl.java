package br.com.mercado.userservice.service;

import br.com.mercado.userservice.model.Role;
import br.com.mercado.userservice.model.Usuario;
import br.com.mercado.userservice.model.UsuarioRole;
import br.com.mercado.userservice.repository.UsuarioRepository;
import br.com.mercado.userservice.util.SecurityUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SecurityUtility securityUtility;

    @Override
    public Usuario criarUsuario(Usuario usuario) {
        Usuario localUser = usuarioRepository.findByUsername(usuario.getUsername());
        if (localUser != null) {
            logger.info("Usuario com nome {} já existe. Nada será feito.", usuario.getUsername());
        } else {
            Set<UsuarioRole> userRoles = new HashSet<>();
            Role localRole = new Role();
            localRole.setId(1L);
            userRoles.add(new UsuarioRole(usuario, localRole));
            usuario.getUsuarioRoles().addAll(userRoles);

            Date today = new Date();
            usuario.setJoinDate(today);

            String encryptedPassword = securityUtility.passwordEncoder().encode(usuario.getPassword());
            usuario.setPassword(encryptedPassword);
            localUser = usuarioRepository.save(usuario);
        }

        return null;
    }

    @Override
    public Usuario getUsuarioByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
