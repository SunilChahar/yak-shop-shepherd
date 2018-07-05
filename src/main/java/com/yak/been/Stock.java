package com.yak.been;

import java.util.ArrayList;
import java.util.List;

import com.yak.response.Product;

public class Stock {
	
	private List<Product> productList;

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	public void addProduct(Product product) {
		if(this.productList == null)
			this.productList = new ArrayList<Product>();
		this.productList.add(product);
	}
	
	public String toString() {
		StringBuilder report = new StringBuilder();
		for (Product product : productList) {
			report.append("\t");
			report.append(product.toString());
			report.append("\n");
		}
		
		return report.toString();
	}
	
}
