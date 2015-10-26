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
public class NcSwitch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1371012940911659272L;

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

	private String gpsKoordinat;

	private String adresi;

	private String lokasyon_Ofis_SubeAdi;

	private String hangiOdada;

	private int portSayisi;

	private int anaEkipman;

	@Enumerated(EnumType.STRING)
	private Durum durum;

	public Date getGununTarihi() {
		return gununTarihi;
	}

	public void setGununTarihi(Date gununTarihi) {
		this.gununTarihi = gununTarihi;
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

	public String getGpsKoordinat() {
		return gpsKoordinat;
	}

	public void setGpsKoordinat(String gpsKoordinat) {
		this.gpsKoordinat = gpsKoordinat;
	}

	public String getAdresi() {
		return adresi;
	}

	public void setAdresi(String adresi) {
		this.adresi = adresi;
	}

	public String getLokasyon_Ofis_SubeAdi() {
		return lokasyon_Ofis_SubeAdi;
	}

	public void setLokasyon_Ofis_SubeAdi(String lokasyon_Ofis_SubeAdi) {
		this.lokasyon_Ofis_SubeAdi = lokasyon_Ofis_SubeAdi;
	}

	public String getHangiOdada() {
		return hangiOdada;
	}

	public void setHangiOdada(String hangiOdada) {
		this.hangiOdada = hangiOdada;
	}

	public int getPortSayisi() {
		return portSayisi;
	}

	public void setPortSayisi(int portSayisi) {
		this.portSayisi = portSayisi;
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
		NcSwitch other = (NcSwitch) obj;
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

	public int getBarkod() {
		return barkod;
	}

	public void setBarkod(int barkod) {
		this.barkod = barkod;
	}
}
