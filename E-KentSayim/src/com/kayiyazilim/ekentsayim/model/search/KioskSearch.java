package com.kayiyazilim.ekentsayim.model.search;

import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.type.Durum;

public class KioskSearch {

	private Bolge bolge;

	private Kullanici kullanici;

	private int barkod;

	private String cihazSeriNo;

	private String marka;

	private String model;

	private String durak_IstasyonAdi;

	private Durum durum;

	public Kullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

	public Bolge getBolge() {
		return bolge;
	}

	public void setBolge(Bolge bolge) {
		this.bolge = bolge;
	}

	public int getBarkod() {
		return barkod;
	}

	public void setBarkod(int barkod) {
		this.barkod = barkod;
	}

	public String getCihazSeriNo() {
		return cihazSeriNo;
	}

	public void setCihazSeriNo(String cihazSeriNo) {
		this.cihazSeriNo = cihazSeriNo;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDurak_IstasyonAdi() {
		return durak_IstasyonAdi;
	}

	public void setDurak_IstasyonAdi(String durak_IstasyonAdi) {
		this.durak_IstasyonAdi = durak_IstasyonAdi;
	}

	public Durum getDurum() {
		return durum;
	}

	public void setDurum(Durum durum) {
		this.durum = durum;
	}

}
