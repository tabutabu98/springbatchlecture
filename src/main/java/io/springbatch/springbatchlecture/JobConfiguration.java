package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JobConfiguration {

    public static final String JOB_NAME = "job";
    public static final String BEAN_PREFIX = JOB_NAME + "_";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean(JOB_NAME)
    public Job job() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(step1())
                .next(step2())
                .build();
    }

    @Bean(BEAN_PREFIX + "Step1")
    public Step step1() {
        return stepBuilderFactory.get(BEAN_PREFIX + "Step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        log.info("=======================================================================");
                        log.info("step1 was executed");
                        log.info("=======================================================================");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean(BEAN_PREFIX + "Step2")
    public Step step2() {
        return stepBuilderFactory.get(BEAN_PREFIX + "Step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        log.info("=======================================================================");
                        log.info("step2 was executed");
                        log.info("=======================================================================");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
