package com.kayiyazilim.ekentsayim.model.search;

import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

public class ParametreSearch {

	private Ekran ekran;
	
	private Alan alan;
	
	private String deger;

	public Ekran getEkran() {
		return ekran;
	}

	public void setEkran(Ekran ekran) {
		this.ekran = ekran;
	}

	public Alan getAlan() {
		return alan;
	}

	public void setAlan(Alan alan) {
		this.alan = alan;
	}

	public String getDeger() {
		return deger;
	}

	public void setDeger(String deger) {
		this.deger = deger;
	}
	
}
