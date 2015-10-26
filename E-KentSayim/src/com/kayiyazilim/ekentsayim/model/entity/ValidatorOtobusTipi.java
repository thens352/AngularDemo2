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
public class ValidatorOtobusTipi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2739374890488450633L;

	@Id
	private Integer barkod;

	@ManyToOne
	private Bolge bolge;
	@Transient
	private Integer bolgeID;

	@ManyToOne
	private Kullanici kullanici;
	@Transient
	private Integer kullaniciID;

	private Date gununTarihi;

	@Column(unique = true)
	private String cihazSeriNo;

	private String marka;

	private String model;

	private String ozellik;

	private String altTipi;

	private String kullanimSekli;

	private String otobusKoseNumarasi;

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

	public String getOzellik() {
		return ozellik;
	}

	public void setOzellik(String ozellik) {
		this.ozellik = ozellik;
	}

	public String getAltTipi() {
		return altTipi;
	}

	public void setAltTipi(String altTipi) {
		this.altTipi = altTipi;
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
		ValidatorOtobusTipi other = (ValidatorOtobusTipi) obj;
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

	public String getKullanimSekli() {
		return kullanimSekli;
	}

	public void setKullanimSekli(String kullanimSekli) {
		this.kullanimSekli = kullanimSekli;
	}

	public Integer getKullaniciID() {
		return kullaniciID;
	}

	public void setKullaniciID(Integer kullaniciID) {
		this.kullaniciID = kullaniciID;
	}

}
