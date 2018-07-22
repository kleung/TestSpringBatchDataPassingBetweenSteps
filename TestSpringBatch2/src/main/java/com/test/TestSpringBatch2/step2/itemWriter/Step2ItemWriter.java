package com.test.TestSpringBatch2.step2.itemWriter;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.test.TestSpringBatch2.model.TestData;

public class Step2ItemWriter implements ItemWriter<TestData> {
	
	public Step2ItemWriter() {
		super();
	}

	@Override
	public void write(List<? extends TestData> items) throws Exception {
		for(TestData item : items) {
			StringBuilder messageBuilder = new StringBuilder("{");
			
			messageBuilder.append("\"testStr\":\"");
			messageBuilder.append(item.getTestStr());
			messageBuilder.append("\",\"testInt\":");
			
			messageBuilder.append(item.getTestInt());
			messageBuilder.append(",\"testDate\":");
			messageBuilder.append(item.getTestDate());
			
			messageBuilder.append("}");
			
			System.out.println(messageBuilder.toString());
		}
		
	}

}
