package br.com.mercado.pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class PedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidoApplication.class, args);
	}

//	@Bean
//	public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ClientContext context, OAuth2ProtectedResourceDetails details) {
//		return new OAuth2RestTemplate(details, context);
//	}

}
