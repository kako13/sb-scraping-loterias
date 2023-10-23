package com.kaue.sbscrapingloterias.reader;

import com.kaue.sbscrapingloterias.model.lotofacil.LotoFacilDTO;
import org.jetbrains.annotations.NotNull;
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
    private Integer quantidadeSorteios;
    private Integer sorteio;
    private Integer ultimoSorteio;

    public LotoFacilItemReader(String apiUrl, String quantidadeSorteios, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
        this.quantidadeSorteios = Integer.parseInt(quantidadeSorteios);
    }

    @Override
    public LotoFacilDTO read() throws Exception {

        if (sorteio != null && ultimoSorteio.equals(sorteio))
            return null;


        LotoFacilDTO sorteioAtual = consultarRecurso();
        if (sorteio == null && ultimoSorteio == null) {
            ultimoSorteio = sorteioAtual.getNumeroConcursoAnterior() - quantidadeSorteios;
        }
        sorteio = sorteioAtual.getNumeroConcursoAnterior();
        return sorteioAtual;
    }

    @NotNull
    private LotoFacilDTO consultarRecurso() throws Exception {
        Optional<LotoFacilDTO> singleTownResource = Optional.ofNullable(getSingleTownResource(sorteio));
        while (singleTownResource.isEmpty()) {
            LOG.warn("Realizando nova tentativa de consulta do sorteio '%s' da LotoFácil".formatted(sorteio));
            singleTownResource = Optional.ofNullable(getSingleTownResource(sorteio));
        }
        LotoFacilDTO sorteioAtual = null;
        try {
            sorteioAtual = singleTownResource.get();
        } catch (Exception e) {
            throw new Exception(e);
        }
        return sorteioAtual;
    }

    private static LotoFacilDTO getSingleTownResource(Integer sorteio) {
        String apiComSorteio = apiUrl + (sorteio == null
                ? ""
                : sorteio);
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
