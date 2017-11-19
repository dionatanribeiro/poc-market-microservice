package br.com.mercado.estoque.service;

import br.com.mercado.estoque.model.Produto;
import br.com.mercado.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService implements CommandLineRunner {

    @Autowired
    private ProdutoRepository repository;

    public Produto buscarPorId(Long id) {
        return repository.findOne(id);
    }

    @Override
    public void run(String... strings) throws Exception {
        Produto produto = new Produto();
        produto.setNome("Arroz");
        produto.setQuantidade(5);
        produto.setValor(BigDecimal.valueOf(30));
        repository.save(produto);

        Produto produto2 = new Produto();
        produto2.setNome("Batata");
        produto2.setQuantidade(10);
        produto2.setValor(BigDecimal.valueOf(23));
        repository.save(produto2);
    }

    public List<Produto> buscarTodos() {
        return repository.findAll();
    }
}
