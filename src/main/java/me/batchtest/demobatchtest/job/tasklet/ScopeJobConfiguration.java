package me.batchtest.demobatchtest.job.tasklet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.batchtest.demobatchtest.job.tasklet.SimpleJobTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ScopeJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job scopeJob(){
        return jobBuilderFactory.get("scopeJob")
                .start(scopeStep1(null))
                .build();
    }

//    @Bean
//    @JobScope
    public Step scopeStep1(@Value("#{jobParameters[requestDate]}") String requestDate){

        return stepBuilderFactory.get("scopeStep1")
                .tasklet(tasklet1)
                .build();
    }

    private final SimpleJobTasklet tasklet1;

/*    @Bean
    @JobScope
    public Step scopeStep2(){
        return stepBuilderFactory.get("scopeStep2")
                .tasklet(scopeStep2Tasklet(null))
                .build();
    }
    *//*(stepContribution, chunkContext) -> {
                        log.info("This is scopeStep1");
                        log.info("Request Date is " + requestDate);
                        return RepeatStatus.FINISHED;
                    }*//*
    @Bean
    @StepScope
    public Tasklet scopeStep2Tasklet(@Value("#{jobParameters[version]}") String version){
        return (stepContribution, chunkContext) -> {
            log.info("This is scopeStep2");
            log.info("version is " + version);
            return RepeatStatus.FINISHED;
        };
    }*/


}
