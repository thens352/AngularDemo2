package com.ens.degerleme.dao.searchobject;

import java.util.Date;

public class RaporSearch {

	private String firmaAdi;
	private String kisiAdi;
	private String kisiSoyadi;
	private String sigortaAdi;
	private Integer raporNo;
	private Date raporTarihi;
	private Date isEmriTarihi;

	public String getFirmaAdi() {
		return firmaAdi;
	}

	public void setFirmaAdi(String firmaAdi) {
		this.firmaAdi = firmaAdi;
	}

	public String getKisiAdi() {
		return kisiAdi;
	}

	public void setKisiAdi(String kisiAdi) {
		this.kisiAdi = kisiAdi;
	}

	public String getKisiSoyadi() {
		return kisiSoyadi;
	}

	public void setKisiSoyadi(String kisiSoyadi) {
		this.kisiSoyadi = kisiSoyadi;
	}

	public String getSigortaAdi() {
		return sigortaAdi;
	}

	public void setSigortaAdi(String sigortaAdi) {
		this.sigortaAdi = sigortaAdi;
	}

	public Integer getRaporNo() {
		return raporNo;
	}

	public void setRaporNo(Integer raporNo) {
		this.raporNo = raporNo;
	}

	public Date getRaporTarihi() {
		return raporTarihi;
	}

	public void setRaporTarihi(Date raporTarihi) {
		this.raporTarihi = raporTarihi;
	}

	public Date getIsEmriTarihi() {
		return isEmriTarihi;
	}

	public void setIsEmriTarihi(Date isEmriTarihi) {
		this.isEmriTarihi = isEmriTarihi;
	}

}
