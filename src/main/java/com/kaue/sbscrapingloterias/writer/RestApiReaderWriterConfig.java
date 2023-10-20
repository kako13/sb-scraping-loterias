package com.kaue.sbscrapingloterias.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kaue.sbscrapingloterias.model.lotofacil.LotoFacilDTO;
import com.kaue.sbscrapingloterias.model.megasena.MegaSenaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class RestApiReaderWriterConfig {

    private static final Logger LOG = LoggerFactory.getLogger(RestApiReaderWriterConfig.class);
    @Bean
    public JsonFileItemWriter<MegaSenaDTO> megaSenaItemWriter() {
        LOG.info("Escrevendo arquivo Resultados Mega Sena.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return new JsonFileItemWriterBuilder<MegaSenaDTO>()
                .name("megaSenaItemWriter")
                .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>(objectMapper))
                .resource(new FileSystemResource("files/Resultados Mega Sena.json"))
                .build();
    }

    @Bean
    public JsonFileItemWriter<LotoFacilDTO> lotoFacilItemlWriter() {
        LOG.info("Escrevendo arquivo Resultados Lotofácil.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return new JsonFileItemWriterBuilder<LotoFacilDTO>()
                .name("LotoFacilWriter")
                .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>(objectMapper))
                .resource(new FileSystemResource("files/Resultados Lotofácil.json"))
                .build();
    }
}
