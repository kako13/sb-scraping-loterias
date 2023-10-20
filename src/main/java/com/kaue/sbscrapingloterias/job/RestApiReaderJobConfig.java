package com.kaue.sbscrapingloterias.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class RestApiReaderJobConfig {

    @Bean
    public Job restApiReaderJob(JobRepository jobRepository,
                                @Qualifier("splitFlow") Flow splitFlow) {
        return new JobBuilder("restApiReaderJob", jobRepository)
                .start(splitFlow).end()
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Flow splitFlow(@Qualifier("flowMegaSena") Flow flowMegaSena, @Qualifier("flowLotoFacil") Flow flowLotoFacil) {
        return new FlowBuilder<SimpleFlow>("splitFlow")
                .split(taskExecutor())
                .add(flowMegaSena, flowLotoFacil)
                .build();
    }

    @Bean
    public Flow flowMegaSena(@Qualifier("megaSenaStep") Step megaSenaStep) {
        return new FlowBuilder<SimpleFlow>("flowMegaSena")
                .start(megaSenaStep)
                .build();
    }

    @Bean
    public Flow flowLotoFacil(@Qualifier("lotoFacilStep") Step lotoFacilStep) {
        return new FlowBuilder<SimpleFlow>("flowLotoFacil")
                .start(lotoFacilStep)
                .build();
    }

    private TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("spring_batch");
    }
}
