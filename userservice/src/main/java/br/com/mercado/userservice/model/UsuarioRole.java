package br.com.mercado.userservice.model;

import javax.persistence.*;

@Entity
public class UsuarioRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long usuarioRoleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    public UsuarioRole() {
    }

    public UsuarioRole(Usuario usuario, Role role) {
        this.usuario = usuario;
        this.role = role;
    }

    public Long getUsuarioRoleId() {
        return usuarioRoleId;
    }

    public void setUsuarioRoleId(Long usuarioRoleId) {
        this.usuarioRoleId = usuarioRoleId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
