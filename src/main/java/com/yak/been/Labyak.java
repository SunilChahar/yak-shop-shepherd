package com.yak.been;

import javax.xml.bind.annotation.XmlAttribute;

public class Labyak {

	private String name;
	
	private float age;
	
	private float agelastshaved;
	
	private String sex;
	
	public Labyak() {
	}
	
	public Labyak(String name, float age, String sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute
	public float getAge() {
		return age;
	}

	public void setAge(float age) {
		this.age = age;
	}
	
	public float getAgelastshaved() {
		return agelastshaved;
	}

	public void setAgelastshaved(float agelastshaved) {
		this.agelastshaved = agelastshaved;
	}

	@XmlAttribute
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Override
	public String toString() {
		StringBuilder report = new StringBuilder();
		report.append(name);
		report.append(" ");
		report.append(age);
		report.append(" ");
		report.append("years old");
		
		return report.toString();
	}
	
}
