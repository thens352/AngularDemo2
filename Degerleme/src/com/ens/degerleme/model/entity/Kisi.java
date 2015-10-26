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
public class Kisi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3131929868428164098L;
	private String name;
	private String surName;
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Id
	private Integer id;
	private String adres;
	private String tcKimlikNo;
	private String telNo;
	private String kapiNo;
	private String daireNo;

	@ManyToOne
	private Sokak sokak;

	@OneToMany(mappedBy = "kisi")
	private List<IsEmri> isEmriList = new ArrayList<IsEmri>();

	@ManyToMany
	private List<Bina> binaList = new ArrayList<Bina>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getTcKimlikNo() {
		return tcKimlikNo;
	}

	public void setTcKimlikNo(String tcKimlikNo) {
		this.tcKimlikNo = tcKimlikNo;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getKapiNo() {
		return kapiNo;
	}

	public void setKapiNo(String kapiNo) {
		this.kapiNo = kapiNo;
	}

	public String getDaireNo() {
		return daireNo;
	}

	public void setDaireNo(String daireNo) {
		this.daireNo = daireNo;
	}

	public List<IsEmri> getIsEmriList() {
		return isEmriList;
	}

	public void setIsEmriList(List<IsEmri> isEmriList) {
		this.isEmriList = isEmriList;
	}

	public List<Bina> getBinaList() {
		return binaList;
	}

	public void setBinaList(List<Bina> binaList) {
		this.binaList = binaList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Sokak getSokak() {
		return sokak;
	}

	public void setSokak(Sokak sokak) {
		this.sokak = sokak;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surName == null) ? 0 : surName.hashCode());
		result = prime * result
				+ ((tcKimlikNo == null) ? 0 : tcKimlikNo.hashCode());
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
		Kisi other = (Kisi) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surName == null) {
			if (other.surName != null)
				return false;
		} else if (!surName.equals(other.surName))
			return false;
		if (tcKimlikNo == null) {
			if (other.tcKimlikNo != null)
				return false;
		} else if (!tcKimlikNo.equals(other.tcKimlikNo))
			return false;
		return true;
	}

}
