package com.yak.response;

import com.yak.been.Herd;

public interface Product {
	
	public ProductType getName();
	
	public String getValue();
	
	public void totalProduction(Herd herd, int days);
	
	public boolean processOrder(String order);
	
	public String availableStock();
	
}
