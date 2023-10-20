package com.kaue.sbscrapingloterias.reader;

import com.kaue.sbscrapingloterias.model.megasena.MegaSenaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class MegaSenaItemReader implements ItemReader<MegaSenaDTO> {

    private static final Logger LOG = LoggerFactory.getLogger(MegaSenaItemReader.class);

    private static String apiUrl;
    private static RestTemplate restTemplate;
    private Integer sorteio;

    public MegaSenaItemReader(String apiUrl, Integer sorteio, RestTemplate restTemplate) {
        this.sorteio = sorteio;
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public MegaSenaDTO read() throws Exception {

        if (sorteio == 0)
            return null;
//
//        if (sorteio == 5)
//            throw new Exception();

        Optional<MegaSenaDTO> singleTownResource = Optional.ofNullable(getSingleTownResource(sorteio));
        while (singleTownResource.isEmpty()) {
            LOG.warn("Realizando nova tentativa de consulta do sorteio '%s' da MegaSena".formatted(sorteio));
            singleTownResource = Optional.ofNullable(getSingleTownResource(sorteio));
        }
        MegaSenaDTO sorteioAtual = singleTownResource.get();
        sorteio = sorteioAtual.getNumeroConcursoAnterior();
        return sorteioAtual;
    }

    private static MegaSenaDTO getSingleTownResource(Integer sorteio) {
        String apiComSorteio = apiUrl + sorteio;
        ResponseEntity<MegaSenaDTO> responseEntity = null;
        try {
            LOG.info("Consultando sorteio '%d' da Mega Sena em '%s'".formatted(sorteio, apiComSorteio));
            responseEntity = restTemplate.getForEntity(apiComSorteio, MegaSenaDTO.class);
        } catch (Exception e) {
            return null;
        }


        boolean successful = responseEntity.getStatusCode().is2xxSuccessful();
        if (successful)
            return responseEntity.getBody();
        return null;
    }
}
