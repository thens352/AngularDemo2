package com.ens.degerleme.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Bina implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adres == null) ? 0 : adres.hashCode());
		result = prime * result + ((daireNo == null) ? 0 : daireNo.hashCode());
		result = prime * result
				+ ((firmaList == null) ? 0 : firmaList.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((isEmriList == null) ? 0 : isEmriList.hashCode());
		result = prime * result + ((kapiNo == null) ? 0 : kapiNo.hashCode());
		result = prime * result
				+ ((kisiList == null) ? 0 : kisiList.hashCode());
		result = prime * result + ((sokak == null) ? 0 : sokak.hashCode());
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
		Bina other = (Bina) obj;
		if (adres == null) {
			if (other.adres != null)
				return false;
		} else if (!adres.equals(other.adres))
			return false;
		if (daireNo == null) {
			if (other.daireNo != null)
				return false;
		} else if (!daireNo.equals(other.daireNo))
			return false;
		if (firmaList == null) {
			if (other.firmaList != null)
				return false;
		} else if (!firmaList.equals(other.firmaList))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isEmriList == null) {
			if (other.isEmriList != null)
				return false;
		} else if (!isEmriList.equals(other.isEmriList))
			return false;
		if (kapiNo == null) {
			if (other.kapiNo != null)
				return false;
		} else if (!kapiNo.equals(other.kapiNo))
			return false;
		if (kisiList == null) {
			if (other.kisiList != null)
				return false;
		} else if (!kisiList.equals(other.kisiList))
			return false;
		if (sokak == null) {
			if (other.sokak != null)
				return false;
		} else if (!sokak.equals(other.sokak))
			return false;
		return true;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5505914366265054191L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String adres;
	private String daireNo;
	private String kapiNo;
	
	@ManyToOne
	private Sokak sokak;
	
	@ManyToMany
	private List<Firma> firmaList = new ArrayList<Firma>();
	
	@ManyToMany
	private List<Kisi> kisiList = new ArrayList<Kisi>();

	@OneToMany(mappedBy = "bina")
	private List<IsEmri> isEmriList = new ArrayList<IsEmri>();

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<IsEmri> getIsEmriList() {
		return isEmriList;
	}

	public void setIsEmriList(List<IsEmri> isEmriList) {
		this.isEmriList = isEmriList;
	}

	public List<Firma> getFirmaList() {
		return firmaList;
	}

	public void setFirmaList(List<Firma> firmaList) {
		this.firmaList = firmaList;
	}

	public List<Kisi> getKisiList() {
		return kisiList;
	}

	public void setKisiList(List<Kisi> kisiList) {
		this.kisiList = kisiList;
	}

	public Sokak getSokak() {
		return sokak;
	}

	public void setSokak(Sokak sokak) {
		this.sokak = sokak;
	}

	public String getDaireNo() {
		return daireNo;
	}

	public void setDaireNo(String daireNo) {
		this.daireNo = daireNo;
	}

	public String getKapiNo() {
		return kapiNo;
	}

	public void setKapiNo(String kapiNo) {
		this.kapiNo = kapiNo;
	}

}
