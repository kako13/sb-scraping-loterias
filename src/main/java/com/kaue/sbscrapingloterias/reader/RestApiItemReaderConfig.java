package com.kaue.sbscrapingloterias.reader;

import com.kaue.sbscrapingloterias.model.megasena.MegaSenaDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestApiItemReaderConfig {

    @Value("${spring.loteria.federal.megasena.ultimo-sorteio}")
    Integer ultimoSorteioMegaSena;
    @Value("${spring.loteria.federal.lofacil.ultimo-sorteio}")
    Integer ultimoSorteioLotoFacil;

    @Bean
    public MegaSenaItemReader megaSenaItemReader(Environment environment,
                                                      RestTemplate restTemplate) {
        System.out.println(ultimoSorteioMegaSena);
        String apiUrl = environment.getRequiredProperty("spring.loteria.federal.megasena.api.url");
        return new MegaSenaItemReader(apiUrl, ultimoSorteioMegaSena, restTemplate);
    }
    @Bean
    public LotoFacilItemReader lotoFacilItemReader(Environment environment,
                                                   RestTemplate restTemplate) {
        System.out.println(ultimoSorteioLotoFacil);
        String apiUrl = environment.getRequiredProperty("spring.loteria.federal.lofacil.api.url");
        return new LotoFacilItemReader(apiUrl, ultimoSorteioLotoFacil, restTemplate);
    }
}
