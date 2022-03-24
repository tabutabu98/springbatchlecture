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
public class HelloJobConfiguration {

    public static final String JOB_NAME = "helloJob";
    public static final String BEAN_PREFIX = JOB_NAME + "_";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(helloStep1())
                .next(helloStep2())
                .build();
    }

    @Bean
    public Step helloStep1() {
        return stepBuilderFactory.get(BEAN_PREFIX + "Step1")
                .tasklet(new Tasklet() {    // Batch는 tasklet을 무한 반복 시킨다.
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

                        log.info("========================");
                        log.info(" >> Hello Spring Batch!!");
                        log.info("========================");

                        return RepeatStatus.FINISHED;   // 기능 1번
                    }
                })
                .build();
    }

    @Bean
    public Step helloStep2() {
        return stepBuilderFactory.get(BEAN_PREFIX + "Step2")
                .tasklet(new Tasklet() {    // Batch는 tasklet을 무한 반복 시킨다.
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

                        log.info("========================");
                        log.info(" >> step2 was executed");
                        log.info("========================");

                        return RepeatStatus.FINISHED;   // 기능 1번
                    }
                })
                .build();
    }
}
