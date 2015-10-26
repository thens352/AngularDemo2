package com.kayi.qrvale.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Isletme implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7415439352819756161L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column
	private String isletmeAdi;

	@ManyToOne
	private Sokak sokak;

	@Column
	private String adres;

	@OneToMany(mappedBy = "isletme", orphanRemoval = true)
	private List<Admin> adminList = new ArrayList<Admin>();

	@OneToMany(mappedBy = "isletme", orphanRemoval = true)
	private List<Vale> valeList = new ArrayList<Vale>();

	@OneToMany(mappedBy = "isletme", orphanRemoval = true)
	private List<Hareket> hareketList = new ArrayList<Hareket>();

	@OneToMany(mappedBy = "isletme", orphanRemoval = true)
	private List<Arac> aracList = new ArrayList<Arac>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsletmeAdi() {
		return isletmeAdi;
	}

	public void setIsletmeAdi(String isletmeAdi) {
		this.isletmeAdi = isletmeAdi;
	}

	public Sokak getSokak() {
		return sokak;
	}

	public void setSokak(Sokak sokak) {
		this.sokak = sokak;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public List<Admin> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<Admin> adminList) {
		this.adminList = adminList;
	}

	public List<Vale> getValeList() {
		return valeList;
	}

	public void setValeList(List<Vale> valeList) {
		this.valeList = valeList;
	}

	public List<Hareket> getHareketList() {
		return hareketList;
	}

	public void setHareketList(List<Hareket> hareketList) {
		this.hareketList = hareketList;
	}

	public List<Arac> getAracList() {
		return aracList;
	}

	public void setAracList(List<Arac> aracList) {
		this.aracList = aracList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Isletme other = (Isletme) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
