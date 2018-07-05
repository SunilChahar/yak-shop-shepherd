package com.yak.been;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Herd {
	
	private List<Labyak> labyak;

	public Herd(){}
	
	public Herd(List<Labyak> labyak) {
		this.labyak = labyak;
	}
	

	public List<Labyak> getLabyak() {
		return labyak;
	}

	@XmlElement
	public void setLabyak(List<Labyak> labyak) {
		this.labyak = labyak;
	}
	
	@Override
	public String toString() {
		StringBuilder report = new StringBuilder();
		for (Labyak labyak : labyak) {
			report.append("\t");
			report.append(labyak.toString());
			report.append("\n");
		}
		return report.toString();
	}

}
