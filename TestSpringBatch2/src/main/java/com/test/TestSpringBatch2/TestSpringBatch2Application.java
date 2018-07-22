package com.test.TestSpringBatch2;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableBatchProcessing
@ImportResource(value={"classpath:batch-job/batch-context.xml"})
public class TestSpringBatch2Application implements CommandLineRunner {

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private ApplicationContext appCtx;
	
	public static void main(String[] args) {
		SpringApplication.run(TestSpringBatch2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String jobId = "job";
		
		try {
			String dateParam = new Date().toString();
	    	JobParameters param = new JobParametersBuilder().addString("date", dateParam).toJobParameters();
	    	
	    	Job job = (Job)appCtx.getBean(jobId);
	    	JobExecution execution = jobLauncher.run(job, param);
	    	System.out.println("Exit Status : " + execution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
