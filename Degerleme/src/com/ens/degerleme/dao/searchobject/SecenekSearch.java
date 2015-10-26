package com.ens.degerleme.dao.searchobject;

import com.ens.degerleme.model.entity.Soru;

public class SecenekSearch {

	private Integer id;

	private String metin;

	private Soru soru;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMetin() {
		return metin;
	}

	public void setMetin(String metin) {
		this.metin = metin;
	}

	public Soru getSoru() {
		return soru;
	}

	public void setSoru(Soru soru) {
		this.soru = soru;
	}

}
