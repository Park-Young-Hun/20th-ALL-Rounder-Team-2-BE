package kr.co.knowledgerally.batch.core.job;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
public class KnowllyJobLauncher {
    @Getter
    private final Job job;
    private final JobLauncher jobLauncher;

    public void execute() {
        try {
            jobLauncher.run(
                    job,
                    new JobParametersBuilder()
                            .addString("datetime", LocalDateTime.now().toString())
                            .toJobParameters()  // job parameter 설정
            );
        } catch (Exception e) {
            log.warn("batch failed : ", e);
        }
    }
}
