package com.yak.json.response;

public class SkinStockStatus implements StockStatus {

	
	public SkinStockStatus(int skin) {
		super();
		this.skin = skin;
	}

	public int skin;

	public int getSkin() {
		return skin;
	}

	public void setSkin(int skin) {
		this.skin = skin;
	}
	
}
