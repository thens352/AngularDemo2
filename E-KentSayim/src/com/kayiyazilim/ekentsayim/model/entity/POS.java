package com.kayiyazilim.ekentsayim.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.kayiyazilim.ekentsayim.model.type.Durum;

@Entity
public class POS implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2092756951013139820L;
	@Id
	private int barkod;

	@ManyToOne
	private Kullanici kullanici;
	@Transient
	private Integer kullaniciID;

	@ManyToOne
	private Bolge bolge;
	@Transient
	private Integer bolgeID;

	private Date gununTarihi;

	@Column(unique = true)
	private String cihazSeriNo;

	private String marka;

	private String model;

	private String gpsKoordinati;

	private String adres;

	private String bulunduguBayiTicariAdi;

	private String bulunduguBayiVergiNo;

	private String bulunduguBayiVergiDairesi;

	private boolean padCihaziVarMi;

	private String padCihazSeriNo;

	private int anaEkipman;

	@Enumerated(EnumType.STRING)
	private Durum durum;

	public String getBulunduguBayiVergiDairesi() {
		return bulunduguBayiVergiDairesi;
	}

	public void setBulunduguBayiVergiDairesi(String bulunduguBayiVergiDairesi) {
		this.bulunduguBayiVergiDairesi = bulunduguBayiVergiDairesi;
	}

	public int getAnaEkipman() {
		return anaEkipman;
	}

	public void setAnaEkipman(int anaEkipman) {
		this.anaEkipman = anaEkipman;
	}

	public Integer getBolgeID() {
		return bolgeID;
	}

	public void setBolgeID(Integer bolgeID) {
		this.bolgeID = bolgeID;
	}

	public Date getGununTarihi() {
		return gununTarihi;
	}

	public void setGununTarihi(Date gununTarihi) {
		this.gununTarihi = gununTarihi;
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

	public String getGpsKoordinati() {
		return gpsKoordinati;
	}

	public void setGpsKoordinati(String gpsKoordinati) {
		this.gpsKoordinati = gpsKoordinati;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getBulunduguBayiTicariAdi() {
		return bulunduguBayiTicariAdi;
	}

	public void setBulunduguBayiTicariAdi(String bulunduguBayiTicariAdi) {
		this.bulunduguBayiTicariAdi = bulunduguBayiTicariAdi;
	}

	public String getBulunduguBayiVergiNo() {
		return bulunduguBayiVergiNo;
	}

	public void setBulunduguBayiVergiNo(String bulunduguBayiVergiNo) {
		this.bulunduguBayiVergiNo = bulunduguBayiVergiNo;
	}

	public boolean isPadCihaziVarMi() {
		return padCihaziVarMi;
	}

	public void setPadCihaziVarMi(boolean padCihaziVarMi) {
		this.padCihaziVarMi = padCihaziVarMi;
	}

	public String getPadCihazSeriNo() {
		return padCihazSeriNo;
	}

	public void setPadCihazSeriNo(String padCihazSeriNo) {
		this.padCihazSeriNo = padCihazSeriNo;
	}

	public Durum getDurum() {
		return durum;
	}

	public void setDurum(Durum durum) {
		this.durum = durum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + barkod;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		POS other = (POS) obj;
		if (barkod != other.barkod)
			return false;
		return true;
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

	public Integer getKullaniciID() {
		return kullaniciID;
	}

	public void setKullaniciID(Integer kullaniciID) {
		this.kullaniciID = kullaniciID;
	}

}
