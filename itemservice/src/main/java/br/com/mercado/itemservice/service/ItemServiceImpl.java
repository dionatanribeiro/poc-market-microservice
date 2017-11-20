package br.com.mercado.itemservice.service;

import br.com.mercado.itemservice.client.UsuarioFeignClient;
import br.com.mercado.itemservice.model.Item;
import br.com.mercado.itemservice.model.Usuario;
import br.com.mercado.itemservice.repository.ItemRepository;
import br.com.mercado.itemservice.util.UserContextHolder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioFeignClient usuarioFeignClient;

    @Override
    public Item adicionarItemPorUsuario(Item item, String username) {
        Item localItem = itemRepository.findByName(item.getName());

        if (localItem != null) {
            logger.info("Item {} já cadastrado. Nada será feito", item.getName());
        } else {
            Date hoje = new Date();
            item.setAddDate(hoje);

            Usuario usuario = usuarioService.getUsuarioByUsername(username);
            item.setUsuario(usuario);
            Item newItem = itemRepository.save(item);

            return newItem;
        }
        return null;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> getItemsByUsername(String username) {
        Usuario usuario = usuarioService.getUsuarioByUsername(username);

        return itemRepository.findByUsuario(usuario);
    }

    @Override
    public Item getItemById(Long idItem) {
        return itemRepository.findOne(idItem);
    }

    @Override
    public Item updateItem(Item item) throws IOException {
        Item localItem = getItemById(item.getId());
        if (localItem == null) {
            throw new IOException("Item não encontrado");
        } else {
            localItem.setName(item.getName());
            localItem.setDescription(item.getDescription());
            localItem.setItemCondition(item.getItemCondition());
            localItem.setStatus(item.getStatus());

            return itemRepository.save(item);
        }
    }

    @Override
    public void deleteItemById(Long idItem) {
        itemRepository.delete(idItem);
    }

    @Override
//    @HystrixCommand(
//            fallbackMethod = "buildFallbackUsuario",
//            threadPoolKey = "itemByUsuarioThreadPool",
//            threadPoolProperties = {
//                    @HystrixProperty(name="coreSize", value="30"),
//                    // (requests per second at peak when the service is healthy * 99th percentile lantency in seconds) +
//                    // small amount of extra threads for overhead
//                    @HystrixProperty(name="maxQueueSize", value="10")
//            }
//    commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000")
//    }
//    )
    public Usuario getUsuarioByUsername(String username) {
//        return usuarioService.getUsuarioByUsername(username);

//        utilizado para teste de erro aleatorio
//        randomRunLong();

        logger.debug("ItemService.getUsuarioByUsername Correlation id: {}", UserContextHolder.getContext().getCorrelationId());

        return usuarioFeignClient.buscarUsuarioPorUsername(username);
    }

    private void randomRunLong() {
        Random random = new Random();
        int randomNum = random.nextInt((3-1)+1)+1;
        if (randomNum == 3) sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Usuario buildFallbackUsuario(String username) {
        Usuario usuario = new Usuario();
        usuario.setUsername("temp " + username);
        usuario.setId(1234L);
        return usuario;
    }

}
