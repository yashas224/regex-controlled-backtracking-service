package pattern.finder.com.example.regexchecker;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync

public class RegexCheckerApplication implements AsyncConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(RegexCheckerApplication.class, args);
	}

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(4);
		executor.setQueueCapacity(10);
		executor.setThreadNamePrefix("regex test-");
		executor.initialize();
		return executor;
	}

}
