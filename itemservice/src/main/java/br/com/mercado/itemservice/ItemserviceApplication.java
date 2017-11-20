package br.com.mercado.itemservice;

import br.com.mercado.itemservice.model.Item;
import br.com.mercado.itemservice.model.Usuario;
import br.com.mercado.itemservice.service.ItemService;
import br.com.mercado.itemservice.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import java.util.Date;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ItemserviceApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ItemService itemService;

	public static void main(String[] args) {
		SpringApplication.run(ItemserviceApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Usuario dionatangr = usuarioService.getUsuarioByUsername("dionatangr");
		Item item1 = new Item();
		item1.setName("item1");
		item1.setDescription("item description");
		item1.setItemCondition("New");
		item1.setStatus("active");
		item1.setAddDate(new Date());
		item1.setUsuario(dionatangr);

		Item item2 = new Item();
		item2.setName("item2");
		item2.setDescription("item description");
		item2.setItemCondition("New");
		item2.setStatus("active");
		item2.setAddDate(new Date());
		item2.setUsuario(dionatangr);

		itemService.adicionarItemPorUsuario(item1, dionatangr.getUsername());
		itemService.adicionarItemPorUsuario(item2, dionatangr.getUsername());
	}
}
