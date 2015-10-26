package com.kayi.qrvale.dao.search;

public class SokakSearch {

	private Integer id;
	private String adi;
	private Integer mahalleId;
	private String mahalleAdi;
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

	public Integer getMahalleId() {
		return mahalleId;
	}

	public void setMahalleId(Integer mahalleId) {
		this.mahalleId = mahalleId;
	}

	public String getMahalleAdi() {
		return mahalleAdi;
	}

	public void setMahalleAdi(String mahalleAdi) {
		this.mahalleAdi = mahalleAdi;
	}

	public boolean isVeya() {
		return veya;
	}

	public void setVeya(boolean veya) {
		this.veya = veya;
	}

}
