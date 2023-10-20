package com.kaue.sbscrapingloterias.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class RestApiReaderStepConfig {

    public static final int CHUNK_SIZE = 1;
    public static final int SKIP_LIMIT = 300;

    @Bean
    public Step megaSenaStep(JobRepository jobRepository,
                                  PlatformTransactionManager transactionManager,
                                  @Qualifier("megaSenaItemReader") ItemReader megaSenaItemReader,
                                  @Qualifier("megaSenaItemWriter") ItemWriter megaSenaItemWriter
    ) {
        return new StepBuilder("megaSenaStep", jobRepository)
                .chunk(CHUNK_SIZE, transactionManager)
                .reader(megaSenaItemReader)
                .writer(megaSenaItemWriter)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(SKIP_LIMIT)
                .build();
    }
    @Bean
    public Step lotoFacilStep(JobRepository jobRepository,
                                  PlatformTransactionManager transactionManager,
                                  @Qualifier("lotoFacilItemReader") ItemReader lotoFacilItemReader,
                                  @Qualifier("lotoFacilItemlWriter") ItemWriter lotoFacilItemlWriter
    ) {
        return new StepBuilder("lotoFacilStep", jobRepository)
                .chunk(CHUNK_SIZE, transactionManager)
                .reader(lotoFacilItemReader)
                .writer(lotoFacilItemlWriter)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(SKIP_LIMIT)
                .build();
    }
}

