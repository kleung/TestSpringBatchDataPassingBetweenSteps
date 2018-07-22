package com.test.TestSpringBatch2.step1.itemWriter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemWriter;

import com.test.TestSpringBatch2.model.TestData;

public class Step1ItemWriter implements ItemWriter<TestData>, ChunkListener, StepExecutionListener {
	
	protected StepExecution stepExecution;
	
	public Step1ItemWriter() {
		super();
	}
	
	public void setStepExecution(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}

	@Override
	public void write(List<? extends TestData> items) throws Exception {
		this.stepExecution.getExecutionContext().put("chunk", items);
		System.out.println("wrote " + items.size() + " items to context.");
	}

	@Override
	public void beforeChunk(ChunkContext context) {
		this.stepExecution = context.getStepContext().getStepExecution();
		
	}

	@Override
	public void afterChunk(ChunkContext context) {
		List<TestData> testDataStepStorage = (List<TestData>)this.stepExecution.getExecutionContext().get("dataList");
		
		if(testDataStepStorage == null) {
			testDataStepStorage = new ArrayList<TestData>();
			this.stepExecution.getExecutionContext().put("dataList", testDataStepStorage);
		}
		
		List<TestData> chunk = (List<TestData>)this.stepExecution.getExecutionContext().get("chunk");
		
		if(chunk != null) {
			testDataStepStorage.addAll(chunk);
			this.stepExecution.getExecutionContext().remove("chunk");
		}
	}

	@Override
	public void afterChunkError(ChunkContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		JobExecution job = stepExecution.getJobExecution();
		List<TestData> testDataStepStorage = (List<TestData>)stepExecution.getExecutionContext().get("dataList");
		
		job.getExecutionContext().put("dataList", testDataStepStorage);
		
		return ExitStatus.COMPLETED;
	}

}
