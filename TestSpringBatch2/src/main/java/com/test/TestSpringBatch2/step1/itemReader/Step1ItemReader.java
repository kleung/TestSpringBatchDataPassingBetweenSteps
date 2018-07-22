package com.test.TestSpringBatch2.step1.itemReader;

import java.io.Serializable;
import java.util.Date;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.test.TestSpringBatch2.model.TestData;

public class Step1ItemReader implements ItemReader<TestData> {
	
	private static int totalCount = 10;
	private static int currentCount = 0;
	
	public Step1ItemReader() {
		super();
	}

	@Override
	public TestData read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		TestData result = null;
		if(Step1ItemReader.currentCount < Step1ItemReader.totalCount) {
			result = new TestData();
			result.setTestDate(new Date());
			Step1ItemReader.currentCount++;
		}
		return result;
	}
}
