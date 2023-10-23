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
    private int quantidadeSorteios;
    private Integer sorteio;
    private Integer ultimoSorteio;

    public MegaSenaItemReader(String apiUrl, String quantidadeSorteios, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
        this.quantidadeSorteios = Integer.parseInt(quantidadeSorteios);
    }

    @Override
    public MegaSenaDTO read() throws Exception {

        if (sorteio != null && ultimoSorteio.equals(sorteio))
            return null;


        MegaSenaDTO sorteioAtual = consultarRecurso();
        if (sorteio == null && ultimoSorteio == null) {
            ultimoSorteio = sorteioAtual.getNumeroConcursoAnterior() - quantidadeSorteios;
        }
        sorteio = sorteioAtual.getNumeroConcursoAnterior();
        return sorteioAtual;
    }

    private MegaSenaDTO consultarRecurso() throws Exception {
        Optional<MegaSenaDTO> singleTownResource = Optional.ofNullable(getSingleTownResource(sorteio));
        while (singleTownResource.isEmpty()) {
            LOG.warn("Realizando nova tentativa de consulta do sorteio '%s' da MegaSena".formatted(sorteio));
            singleTownResource = Optional.ofNullable(getSingleTownResource(sorteio));
        }
        MegaSenaDTO sorteioAtual = null;
        try {
            sorteioAtual = singleTownResource.get();
        } catch (Exception e) {
            throw new Exception(e);
        }
        return sorteioAtual;
    }

    private MegaSenaDTO getSingleTownResource(Integer sorteio) {
        String apiComSorteio = apiUrl + (sorteio == null
                                        ? ""
                                        : sorteio);
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
