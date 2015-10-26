package com.kayiyazilim.budaksanisakis.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.kayiyazilim.budaksanisakis.model.type.MalzemeTipi;

@Entity
public class Malzeme implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1431563689493639436L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String ad;

	private double mevcutMiktar;

	private String birim;

	@Enumerated(EnumType.STRING)
	private MalzemeTipi malzemeTipi;

	@OneToMany(mappedBy = "malzeme", orphanRemoval = true)
	private List<StokHareket> stokHareketList = new ArrayList<StokHareket>();

	@OneToMany(mappedBy = "malzeme", orphanRemoval = true)
	private List<Urun> urunList = new ArrayList<Urun>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public double getMevcutMiktar() {
		return mevcutMiktar;
	}

	public void setMevcutMiktar(double mevcutMiktar) {
		this.mevcutMiktar = mevcutMiktar;
	}

	public String getBirim() {
		return birim;
	}

	public void setBirim(String birim) {
		this.birim = birim;
	}

	public MalzemeTipi getMalzemeTipi() {
		return malzemeTipi;
	}

	public void setMalzemeTipi(MalzemeTipi malzemeTipi) {
		this.malzemeTipi = malzemeTipi;
	}

	public List<StokHareket> getStokHareketList() {
		return stokHareketList;
	}

	public void setStokHareketList(List<StokHareket> stokHareketList) {
		this.stokHareketList = stokHareketList;
	}

	public List<Urun> getUrunList() {
		return urunList;
	}

	public void setUrunList(List<Urun> urunList) {
		this.urunList = urunList;
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
		Malzeme other = (Malzeme) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
