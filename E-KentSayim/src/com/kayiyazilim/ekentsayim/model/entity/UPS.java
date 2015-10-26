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
public class UPS implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4687433520616515021L;
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

	private String uretici;

	private String guc;

	private Date sonAkuDegisimTarihi;

	private String lokasyon_Ofis_SubeAdi;

	private String gpsKoordinati;

	private Date garantiBaslamaTarihi;

	private Date garantiBitisTarihi;

	private String hizmetVerenTeknikServis;

	private String bulunduguOda;

	@Enumerated(EnumType.STRING)
	private Durum durum;

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

	public String getUretici() {
		return uretici;
	}

	public void setUretici(String uretici) {
		this.uretici = uretici;
	}

	public String getGuc() {
		return guc;
	}

	public void setGuc(String guc) {
		this.guc = guc;
	}

	public Date getSonAkuDegisimTarihi() {
		return sonAkuDegisimTarihi;
	}

	public void setSonAkuDegisimTarihi(Date sonAkuDegisimTarihi) {
		this.sonAkuDegisimTarihi = sonAkuDegisimTarihi;
	}

	public String getLokasyon_Ofis_SubeAdi() {
		return lokasyon_Ofis_SubeAdi;
	}

	public void setLokasyon_Ofis_SubeAdi(String lokasyon_Ofis_SubeAdi) {
		this.lokasyon_Ofis_SubeAdi = lokasyon_Ofis_SubeAdi;
	}

	public String getGpsKoordinati() {
		return gpsKoordinati;
	}

	public void setGpsKoordinati(String gpsKoordinati) {
		this.gpsKoordinati = gpsKoordinati;
	}

	public Date getGarantiBaslamaTarihi() {
		return garantiBaslamaTarihi;
	}

	public void setGarantiBaslamaTarihi(Date garantiBaslamaTarihi) {
		this.garantiBaslamaTarihi = garantiBaslamaTarihi;
	}

	public Date getGarantiBitisTarihi() {
		return garantiBitisTarihi;
	}

	public void setGarantiBitisTarihi(Date garantiBitisTarihi) {
		this.garantiBitisTarihi = garantiBitisTarihi;
	}

	public String getHizmetVerenTeknikServis() {
		return hizmetVerenTeknikServis;
	}

	public void setHizmetVerenTeknikServis(String hizmetVerenTeknikServis) {
		this.hizmetVerenTeknikServis = hizmetVerenTeknikServis;
	}

	public String getBulunduguOda() {
		return bulunduguOda;
	}

	public void setBulunduguOda(String bulunduguOda) {
		this.bulunduguOda = bulunduguOda;
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
		UPS other = (UPS) obj;
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
