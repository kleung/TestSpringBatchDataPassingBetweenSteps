package com.test.TestSpringBatch2.step2.itemReader;

import java.util.List;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.TestSpringBatch2.model.TestData;

public class Step2ItemReader implements ItemReader<TestData>, ChunkListener, StepExecutionListener {

	protected StepExecution stepExecution;
	
	protected List<TestData> dataList;
	
	public Step2ItemReader() {
		super();
	}
	
	public void setStepExecution(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}

	@Override
	public TestData read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		Object dataListIndexObj = this.stepExecution.getExecutionContext().get("currentIndex");
		Integer dataListIndex = null;
		
		if(dataListIndexObj != null) {
			dataListIndex = (Integer)dataListIndexObj;
		}
		
		TestData result  = null;
		
		if(dataListIndex.intValue() < dataList.size())
			result = this.dataList.get(dataListIndex.intValue());
		
		dataListIndex = Integer.valueOf(dataListIndex.intValue() + 1);
		this.stepExecution.getExecutionContext().put("currentIndex", dataListIndex);
		
		return result;
	}

	@Override
	public void beforeChunk(ChunkContext context) {
		this.stepExecution = context.getStepContext().getStepExecution();
		
	}

	@Override
	public void afterChunk(ChunkContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterChunkError(ChunkContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
		this.dataList = (List<TestData>)executionContext.get("dataList");
		
		stepExecution.getExecutionContext().put("currentIndex", Integer.valueOf(0));
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}

}
