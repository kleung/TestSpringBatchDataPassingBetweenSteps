package com.test.TestSpringBatch2.step2.itemProcessor;

import org.springframework.batch.item.ItemProcessor;

import com.test.TestSpringBatch2.model.TestData;

public class Step2ItemProcessor implements ItemProcessor<TestData, TestData> {
	
	public Step2ItemProcessor() {
		super();
	}

	@Override
	public TestData process(TestData item) throws Exception {
		item.setTestStr("2");
		return item;
	}

}
