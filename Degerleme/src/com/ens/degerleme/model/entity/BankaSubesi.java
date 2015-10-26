package com.ens.degerleme.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class BankaSubesi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2247470242848287561L;

	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Id
	private Integer id;
	private String name;
	private String ilgiliKisi;
	private String telNo;
	private String adres;

	@ManyToOne
	private Banka banka;

	@OneToMany(mappedBy = "bankaSubesi")
	private List<IsEmri> isEmriList = new ArrayList<IsEmri>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIlgiliKisi() {
		return ilgiliKisi;
	}

	public void setIlgiliKisi(String ilgiliKisi) {
		this.ilgiliKisi = ilgiliKisi;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adres == null) ? 0 : adres.hashCode());
		result = prime * result + ((banka == null) ? 0 : banka.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((ilgiliKisi == null) ? 0 : ilgiliKisi.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((telNo == null) ? 0 : telNo.hashCode());
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
		BankaSubesi other = (BankaSubesi) obj;
		if (adres == null) {
			if (other.adres != null)
				return false;
		} else if (!adres.equals(other.adres))
			return false;
		if (banka == null) {
			if (other.banka != null)
				return false;
		} else if (!banka.equals(other.banka))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ilgiliKisi == null) {
			if (other.ilgiliKisi != null)
				return false;
		} else if (!ilgiliKisi.equals(other.ilgiliKisi))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (telNo == null) {
			if (other.telNo != null)
				return false;
		} else if (!telNo.equals(other.telNo))
			return false;
		return true;
	}

	public List<IsEmri> getIsEmriList() {
		return isEmriList;
	}

	public void setIsEmriList(List<IsEmri> isEmriList) {
		this.isEmriList = isEmriList;
	}
}
