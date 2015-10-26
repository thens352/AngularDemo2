package com.kayiyazilim.ekentsayim.model.search;

import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.type.Durum;

public class ValidatorTurnikeTipiSearch {

	private Kullanici kullanici;

	private Bolge bolge;

	private int barkod;

	private String cihazSeriNo;

	private String marka;

	private String model;

	private String istasyon_DurakAdi;

	private String turnikeNo;

	private Durum durum;

	public String getTurnikeNo() {
		return turnikeNo;
	}

	public void setTurnikeNo(String turnikeNo) {
		this.turnikeNo = turnikeNo;
	}

	public Durum getDurum() {
		return durum;
	}

	public void setDurum(Durum durum) {
		this.durum = durum;
	}

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

	public String getIstasyon_DurakAdi() {
		return istasyon_DurakAdi;
	}

	public void setIstasyon_DurakAdi(String istasyon_DurakAdi) {
		this.istasyon_DurakAdi = istasyon_DurakAdi;
	}

}
