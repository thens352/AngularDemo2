package com.kayiyazilim.ekentsayim.model.search;

import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.type.Durum;

public class ValidatorOtobusTipiSearch {

	private Bolge bolge;

	private Kullanici kullanici;

	private Integer barkod;

	private String cihazSeriNo;

	private String marka;

	private String model;

	private String otobusKoseNumarasi;

	private Durum durum;

	public Bolge getBolge() {
		return bolge;
	}

	public void setBolge(Bolge bolge) {
		this.bolge = bolge;
	}

	public Kullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

	public Integer getBarkod() {
		return barkod;
	}

	public void setBarkod(Integer barkod) {
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

	public String getOtobusKoseNumarasi() {
		return otobusKoseNumarasi;
	}

	public void setOtobusKoseNumarasi(String otobusKoseNumarasi) {
		this.otobusKoseNumarasi = otobusKoseNumarasi;
	}

	public Durum getDurum() {
		return durum;
	}

	public void setDurum(Durum durum) {
		this.durum = durum;
	}

}
