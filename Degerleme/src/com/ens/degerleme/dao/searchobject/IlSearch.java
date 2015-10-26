package com.ens.degerleme.dao.searchobject;

public class IlSearch {

	private Integer id;
	private String adi;
	private boolean veya = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public boolean isVeya() {
		return veya;
	}

	public void setVeya(boolean veya) {
		this.veya = veya;
	}

}
