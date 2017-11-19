package br.com.mercado.pedido.controller;

import br.com.mercado.pedido.client.EstoqueClient;
import br.com.mercado.pedido.dto.ProdutoApiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private EstoqueClient client;

    @GetMapping("/{idProduto}/{quantidade}")
    public ProdutoApiDto imprimirPedido(@PathVariable Long idProduto, @PathVariable Integer quantidade) {
        ProdutoApiDto produtoApiDto = client.buscarProdutoPorId(idProduto);
        BigDecimal valorTotal = produtoApiDto.getValor().multiply(BigDecimal.valueOf(quantidade.longValue()));
        produtoApiDto.setValorTotal(valorTotal);
        produtoApiDto.setQuantidade(produtoApiDto.getQuantidade() - quantidade);
        return produtoApiDto;
    }

    @GetMapping
    public String helloPedidos() {
        return "hello dos pedidos!";
    }

}
