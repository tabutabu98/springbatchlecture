package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JobParameterConfiguration {

    public static final String JOB_NAME = "jobParameterJob";
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

                        JobParameters jobParameters1 = contribution.getStepExecution().getJobExecution().getJobParameters();
                        log.info("name = " + jobParameters1.getString("name"));
                        log.info("seq = " + jobParameters1.getLong("seq"));
                        log.info("date = " + jobParameters1.getDate("date"));
                        log.info("age = " + jobParameters1.getDouble("age"));

                        // 동일한 값을 얻을 수 있음. 값만 확인 하는 방법
                        Map<String, Object> jobParameters2 = chunkContext.getStepContext().getJobParameters();

                        log.info("=============================================================");
                        log.info("step1 has executed");
                        log.info("=============================================================");
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
                        log.info("=============================================================");
                        log.info("step2 has executed");
                        log.info("=============================================================");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
