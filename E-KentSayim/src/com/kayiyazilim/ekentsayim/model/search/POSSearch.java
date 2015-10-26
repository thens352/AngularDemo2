package com.kayiyazilim.ekentsayim.model.search;

import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.type.Durum;

public class POSSearch {

	private Bolge bolge;

	private Kullanici kullanici;

	private int barkod;

	private String cihazSeriNo;

	private String marka;

	private String model;

	private boolean padCihazVar;

	private boolean padCihazYok;

	private String padCihazSeriNo;

	private String bulunduguBayiTicariAdi;

	private int anaEkipman;

	private Durum durum;

	public Bolge getBolge() {
		return bolge;
	}

	public int getAnaEkipman() {
		return anaEkipman;
	}

	public void setAnaEkipman(int anaEkipman) {
		this.anaEkipman = anaEkipman;
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

	public String getBulunduguBayiTicariAdi() {
		return bulunduguBayiTicariAdi;
	}

	public void setBulunduguBayiTicariAdi(String bulunduguBayiTicariAdi) {
		this.bulunduguBayiTicariAdi = bulunduguBayiTicariAdi;
	}

	public Durum getDurum() {
		return durum;
	}

	public void setDurum(Durum durum) {
		this.durum = durum;
	}

	public String getPadCihazSeriNo() {
		return padCihazSeriNo;
	}

	public void setPadCihazSeriNo(String padCihazSeriNo) {
		this.padCihazSeriNo = padCihazSeriNo;
	}

	public boolean isPadCihazVar() {
		return padCihazVar;
	}

	public void setPadCihazVar(boolean padCihazVar) {
		this.padCihazVar = padCihazVar;
	}

	public boolean isPadCihazYok() {
		return padCihazYok;
	}

	public void setPadCihazYok(boolean padCihazYok) {
		this.padCihazYok = padCihazYok;
	}

}
