package com.kaue.sbscrapingloterias.reader;

import com.kaue.sbscrapingloterias.model.lotofacil.LotoFacilDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class LotoFacilItemReader implements ItemReader<LotoFacilDTO> {

    private static final Logger LOG = LoggerFactory.getLogger(LotoFacilItemReader.class);

    private static String apiUrl;
    private static RestTemplate restTemplate;
    private Integer sorteio;

    public LotoFacilItemReader(String apiUrl, Integer sorteio, RestTemplate restTemplate) {
        this.sorteio = sorteio;
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public LotoFacilDTO read() throws Exception {

        if (sorteio == 0)
            return null;
//
//        if (sorteio == 5)
//            throw new Exception();

        Optional<LotoFacilDTO> singleTownResource = Optional.ofNullable(getSingleTownResource(sorteio));
        while (singleTownResource.isEmpty() && sorteio > 0) {
            LOG.warn("Realizando nova tentativa de consulta do sorteio '%s' da LotoFácil".formatted(sorteio));
            singleTownResource = Optional.ofNullable(getSingleTownResource(sorteio));
        }
        LotoFacilDTO sorteioAtual = singleTownResource.get();
        sorteio = sorteioAtual.getNumeroConcursoAnterior();
        return sorteioAtual;
    }

    private static LotoFacilDTO getSingleTownResource(Integer sorteio) {
        String apiComSorteio = apiUrl + sorteio;
        ResponseEntity<LotoFacilDTO> responseEntity = null;
        try {
            LOG.info("Consultando sorteio '%d' da Lotofácil em '%s'".formatted(sorteio, apiComSorteio));
            responseEntity = restTemplate.getForEntity(apiComSorteio, LotoFacilDTO.class);
        } catch (Exception e) {
            return null;
        }


        boolean successful = responseEntity.getStatusCode().is2xxSuccessful();
        if (successful)
            return responseEntity.getBody();
        return null;
    }
}
