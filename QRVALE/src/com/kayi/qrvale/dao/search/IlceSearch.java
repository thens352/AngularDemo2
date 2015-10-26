package com.kayi.qrvale.dao.search;

public class IlceSearch {

	private Integer id;
	private String adi;
	private Integer ilId;
	private String ilAdi;
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

	public Integer getIlId() {
		return ilId;
	}

	public void setIlId(Integer ilId) {
		this.ilId = ilId;
	}

	public String getIlAdi() {
		return ilAdi;
	}

	public void setIlAdi(String ilAdi) {
		this.ilAdi = ilAdi;
	}

	public boolean isVeya() {
		return veya;
	}

	public void setVeya(boolean veya) {
		this.veya = veya;
	}

}
