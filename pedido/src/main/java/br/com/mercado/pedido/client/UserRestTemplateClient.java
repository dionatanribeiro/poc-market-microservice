package br.com.mercado.pedido.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserRestTemplateClient {

//    @Autowired
//    private OAuth2RestTemplate oAuth2RestTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserRestTemplateClient.class);

    public String getUser() {
//        logger.info("In item service.getUser: {}", UserContext.getCorrelationId());
        return "";
    }

}
