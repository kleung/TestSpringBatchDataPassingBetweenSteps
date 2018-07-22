package com.test.TestSpringBatch2.model;

import java.io.Serializable;

import java.util.Date;

public class TestData implements Serializable {

	private static final long serialVersionUID = -8874950487844896440L;

	protected String testStr;
	
	protected Integer testInt;
	
	protected Date testDate;
	
	public TestData() {
		super();
	}

	public String getTestStr() {
		return testStr;
	}

	public void setTestStr(String testStr) {
		this.testStr = testStr;
	}

	public Integer getTestInt() {
		return testInt;
	}

	public void setTestInt(Integer testInt) {
		this.testInt = testInt;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	
}
