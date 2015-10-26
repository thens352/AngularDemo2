package com.kayiyazilim.budaksanisakis.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Kroki implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1431563689493639436L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String ad;

	private String dosyaYolu;

	private Date yuklemeTarihi;

	@ManyToOne
	private Proje proje;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDosyaYolu() {
		return dosyaYolu;
	}

	public void setDosyaYolu(String dosyaYolu) {
		this.dosyaYolu = dosyaYolu;
	}

	public Proje getProje() {
		return proje;
	}

	public void setProje(Proje proje) {
		this.proje = proje;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Kroki other = (Kroki) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public Date getYuklemeTarihi() {
		return yuklemeTarihi;
	}

	public void setYuklemeTarihi(Date yuklemeTarihi) {
		this.yuklemeTarihi = yuklemeTarihi;
	}
}
