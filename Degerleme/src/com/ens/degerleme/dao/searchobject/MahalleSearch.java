package com.ens.degerleme.dao.searchobject;

public class MahalleSearch {

	private Integer id;
	private String adi;
	private Integer ilceId;
	private String ilceAdi;
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

	public Integer getIlceId() {
		return ilceId;
	}

	public void setIlceId(Integer ilceId) {
		this.ilceId = ilceId;
	}

	public String getIlceAdi() {
		return ilceAdi;
	}

	public void setIlceAdi(String ilceAdi) {
		this.ilceAdi = ilceAdi;
	}

	public boolean isVeya() {
		return veya;
	}

	public void setVeya(boolean veya) {
		this.veya = veya;
	}

}
