package com.yak.json;

import java.util.ArrayList;
import java.util.List;

public class HerdResponse {

	private List<LabyakResponse> herd;

	public List<LabyakResponse> getHerd() {
		return herd;
	}

	public void setHerd(List<LabyakResponse> herd) {
		this.herd = herd;
	}
	
	public void addLabYak(LabyakResponse labyak) {
		if(herd == null)
			herd = new ArrayList<LabyakResponse>();
		herd.add(labyak);
	}
}
