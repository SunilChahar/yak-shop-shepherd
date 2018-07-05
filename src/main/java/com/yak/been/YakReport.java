package com.yak.been;

public class YakReport {

	private Stock stock;
	
	private Herd herd;
	
	public YakReport() {
	}
	
	public YakReport(Stock stock, Herd herd) {
		this.stock = stock;
		this.herd = herd;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Herd getHerd() {
		return herd;
	}

	public void setHerd(Herd herd) {
		this.herd = herd;
	}
	
	@Override
	public String toString() {
		StringBuilder report = new StringBuilder();
		report.append("In Stock:");
		report.append("\n");
		report.append(stock.toString());
		report.append("\n");
		report.append("Herd:");
		report.append("\n");
		report.append(herd.toString());
		
		return report.toString();
	}
}
