package br.com.mercado.itemservice.client;

import br.com.mercado.itemservice.model.Usuario;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient("userservice")
@FeignClient(name = "mercado-gateway")
@RequestMapping("/api/userservice/v1/usuarios")
public interface UsuarioFeignClient {

    @GetMapping("/{username}")
    Usuario buscarUsuarioPorUsername(@PathVariable("username") String username);

}
