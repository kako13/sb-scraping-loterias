package com.kaue.sbscrapingloterias.reader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestApiItemReaderConfig {

    @Value("${spring.loteria.quantidade.sorteios}")
    private String quantidadeSorteios;
    @Bean
    public MegaSenaItemReader megaSenaItemReader(Environment environment,
                                                      RestTemplate restTemplate) {
        String apiUrl = environment.getRequiredProperty("spring.loteria.federal.megasena.api.url");
        return new MegaSenaItemReader(apiUrl, quantidadeSorteios, restTemplate);
    }
    @Bean
    public LotoFacilItemReader lotoFacilItemReader(Environment environment,
                                                   RestTemplate restTemplate) {
        String apiUrl = environment.getRequiredProperty("spring.loteria.federal.lofacil.api.url");
        return new LotoFacilItemReader(apiUrl, quantidadeSorteios, restTemplate);
    }
}
