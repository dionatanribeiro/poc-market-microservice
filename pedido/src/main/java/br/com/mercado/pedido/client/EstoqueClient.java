package br.com.mercado.pedido.client;

import br.com.mercado.pedido.dto.ProdutoApiDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "mercado-gateway")
@RibbonClient(name = "mercado-estoque")
@RequestMapping("/api/mercado-estoque")
public interface EstoqueClient {

    @GetMapping("/produtos/{id}")
    public ProdutoApiDto buscarProdutoPorId(@PathVariable("id") Long id);

    @GetMapping("/produtos")
    public List<ProdutoApiDto> buscarTodosProduto();

}
