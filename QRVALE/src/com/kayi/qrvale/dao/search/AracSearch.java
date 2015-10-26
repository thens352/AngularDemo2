package com.kayi.qrvale.dao.search;

import com.kayi.qrvale.model.entity.Isletme;
import com.kayi.qrvale.model.type.Marka;
import com.kayi.qrvale.model.type.Renk;

public class AracSearch {

	private String plaka;

	private Marka marka;
	
	private String model;

	private Renk renk;

	private Isletme isletme;

	public String getPlaka() {
		return plaka;
	}

	public void setPlaka(String plaka) {
		this.plaka = plaka;
	}

	public Marka getMarka() {
		return marka;
	}

	public void setMarka(Marka marka) {
		this.marka = marka;
	}

	public Renk getRenk() {
		return renk;
	}

	public void setRenk(Renk renk) {
		this.renk = renk;
	}

	public Isletme getIsletme() {
		return isletme;
	}

	public void setIsletme(Isletme isletme) {
		this.isletme = isletme;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
