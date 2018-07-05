package com.yak.json.response;

public class MilkStockStatus implements StockStatus {


	public MilkStockStatus(float milk) {
		super();
		this.milk = milk;
	}
	
	private float milk;

	public float getMilk() {
		return milk;
	}

	public void setMilk(float milk) {
		this.milk = milk;
	}
	
}
