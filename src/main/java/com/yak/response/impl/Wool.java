package com.yak.response.impl;

import org.springframework.beans.factory.annotation.Value;

import com.yak.been.Herd;
import com.yak.been.Labyak;
import com.yak.response.Product;
import com.yak.response.ProductType;

public class Wool implements Product {

	private ProductType name = ProductType.WOOL;
	
	private int value = 0;
	
	@Value("milk.message")
	private String message = "skins of wool";
	
	@Override
	public void totalProduction(Herd herd, int days) {
		for (Labyak labYak : herd.getLabyak()) {
			int ageAtStartup = (int)(labYak.getAge() * 100);
			int productiveDays = 1000 >= ageAtStartup + days ? days : 1000 - ageAtStartup;
			
			value += skinCount(ageAtStartup, productiveDays);
			
		}
	}
	
	public Herd updatedHerdSaved(Herd herd, int days) {
		int age;
		int ageFactor;// = age/100;
		int nextEligibleDay = 0;// = 8 + ageFactor + 1;
		for (Labyak labyak : herd.getLabyak()) {
			age = (int)(labyak.getAge() * 100);
			ageFactor = age/100;
			nextEligibleDay = 8 + ageFactor + 1;
			while(days > nextEligibleDay && age < 1000) {
				age += nextEligibleDay;
				ageFactor = age/100;
				nextEligibleDay += (8 + ageFactor + 1);
			}
			float lastSavedAge = age/100f;
			labyak.setAgelastshaved(lastSavedAge);
			float currentAge = labyak.getAge() + days/100f;
			labyak.setAge(currentAge);
		}
		
		return herd;
	}

	@Override
	public boolean processOrder(String order) {
		return false;
	}

	@Override
	public String availableStock() {
		return null;
	}
	
	@Override
	public ProductType getName() {
		return this.name;
	}
	
	@Override
	public String getValue() {
		return Integer.toString(value);
	}
	
	private int skinCount(int age, int days) {
		
		int skinCount = 1;
		int ageFactor = age/100;
		int nextEligibleDay = 8 + ageFactor + 1;
		while(days > nextEligibleDay  && age < 1000) {
			skinCount++;
			age += nextEligibleDay;
			days -= nextEligibleDay;
			nextEligibleDay = 8 + (int)Math.ceil(age/100);
		}
		
		return skinCount;
	}
	
	@Override
	public String toString() {
		StringBuilder report = new StringBuilder();
		report.append(value);
		report.append(" ");
		report.append(message);
		
		return report.toString();
	}

}
