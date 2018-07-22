package com.test.TestSpringBatch2.step1.itemProcessor;

import org.springframework.batch.item.ItemProcessor;

import com.test.TestSpringBatch2.model.TestData;

public class Step1ItemProcessor implements ItemProcessor<TestData, TestData> {
	
	public Step1ItemProcessor() {
		super();
	}

	@Override
	public TestData process(TestData item) throws Exception {
		item.setTestInt(Integer.valueOf(1));
		return item;
	}

}
