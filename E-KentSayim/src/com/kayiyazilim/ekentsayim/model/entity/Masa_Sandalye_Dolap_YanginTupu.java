package com.kayiyazilim.ekentsayim.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.kayiyazilim.ekentsayim.model.type.Durum;

@Entity
public class Masa_Sandalye_Dolap_YanginTupu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5164674128566229512L;

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

	private String lokasyon_Ofis_SubeAdi;

	private String cesit;

	@Enumerated(EnumType.STRING)
	private Durum durum;

	public String getCesit() {
		return cesit;
	}

	public void setCesit(String cesit) {
		this.cesit = cesit;
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

	public String getLokasyon_Ofis_SubeAdi() {
		return lokasyon_Ofis_SubeAdi;
	}

	public void setLokasyon_Ofis_SubeAdi(String lokasyon_Ofis_SubeAdi) {
		this.lokasyon_Ofis_SubeAdi = lokasyon_Ofis_SubeAdi;
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
		Masa_Sandalye_Dolap_YanginTupu other = (Masa_Sandalye_Dolap_YanginTupu) obj;
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
