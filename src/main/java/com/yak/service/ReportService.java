package com.yak.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yak.been.Herd;
import com.yak.been.Labyak;
import com.yak.been.Stock;
import com.yak.been.YakReport;
import com.yak.dao.HerdDAO;
import com.yak.response.Product;
import com.yak.response.ProductFactory;
import com.yak.response.ProductType;
import com.yak.response.impl.Wool;

@Service
public class ReportService {
	
	@Autowired
	private HerdDAO herdDAO;
	
	public ReportService() {
	}
	
	public YakReport createReport(String fileName, int days) {
		
		HerdDAO dao = new HerdDAO(fileName);
		Herd herd = dao.getYakList();
		Stock stock = calculateStock(herd, days);
		
		Herd newHerd = updatedHerd(herd, days);
		
		return new YakReport(stock, newHerd);
	}
	
	public Stock calculateStock(Herd herd, int days) {
	
		Stock stock = new Stock();
		
		stock.addProduct(getProduct(ProductType.MILK, herd, days));
		
		stock.addProduct(getProduct(ProductType.WOOL, herd, days));
		
		return stock;
	}
	
	public Stock calculateStock(int days) {
		//herdDAO = new HerdDAO();
		Stock stock = new Stock();
		Herd herd = herdDAO.getYakList();
		stock.addProduct(getProduct(ProductType.MILK, herd, days));
		
		stock.addProduct(getProduct(ProductType.WOOL, herd, days));
		
		return stock;
	}
	
	public Herd updateHerdAge(int days) {
		Herd herd = herdDAO.getYakList();
		Wool woolherdSave = new Wool();
		woolherdSave.updatedHerdSaved(herd, days);
		
		return herd;
	}
	
	private Product getProduct(ProductType product, Herd herd, int days) {
		Product implProduct = ProductFactory.getProduct(product);
		
		implProduct.totalProduction(herd, days);
		
		return implProduct;
	}
	
	private Herd updatedHerd(Herd herd, int days) {
		
		float newAge;
		List<Labyak> newYakList = new ArrayList<Labyak>();
		Herd newHerd = new Herd();
		for (Labyak labyak : herd.getLabyak()) {
			newAge = labyak.getAge()*100 + days;
			if(1000 > newAge) {
				labyak.setAge(newAge/100);
				newYakList.add(labyak);
			} 
		}
		
		newHerd.setLabyak(newYakList);
		
		return newHerd;
	}
	
}
