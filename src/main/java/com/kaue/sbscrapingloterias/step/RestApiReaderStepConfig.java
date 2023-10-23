package com.kaue.sbscrapingloterias.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
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

    public static final int CHUNK_SIZE = 5;

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
                .listener(StepExecutionListener.class)
                .writer(lotoFacilItemlWriter)
                .build();
    }

//    @AfterRead
//    public void pegarLeve() throws InterruptedException {
//        wait(3000L);
//    }
}

