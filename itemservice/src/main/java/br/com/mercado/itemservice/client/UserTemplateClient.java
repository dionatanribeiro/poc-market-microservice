package br.com.mercado.itemservice.client;

import br.com.mercado.itemservice.model.Usuario;
import br.com.mercado.itemservice.util.UserContext;
import br.com.mercado.itemservice.util.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserTemplateClient {

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserTemplateClient.class);

    public Usuario getUsuario(String username) {
        logger.debug("In item service.getUsuario: {}", UserContextHolder.getContext().getCorrelationId());

        ResponseEntity<Usuario> restExchange = oAuth2RestTemplate.exchange("http://localhost:8080/api/userservice/v1/usuarios/{username}"
        , HttpMethod.GET, null, Usuario.class, username);

        return restExchange.getBody();
    }

}
