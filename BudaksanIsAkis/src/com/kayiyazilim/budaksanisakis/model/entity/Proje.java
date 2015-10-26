package com.kayiyazilim.budaksanisakis.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Proje implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5848419930373434440L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String ad;

	private Date baslamaTarihi;

	private Date bitisTarihi;

	private String adres;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "proje", orphanRemoval = true)
	private List<YakitHareket> yakitHareketList = new ArrayList<YakitHareket>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "proje", orphanRemoval = true)
	private List<StokHareket> stokHareketList = new ArrayList<StokHareket>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "proje", orphanRemoval = true)
	private List<Fatura> faturaList = new ArrayList<Fatura>();

	private double toplamMasraf;

	private double toplamKM;

	private double toplamSaat;

	private String malzemeKullanimi;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "proje", orphanRemoval = true)
	private List<Kroki> krokiList = new ArrayList<Kroki>();

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

	public Date getBaslamaTarihi() {
		return baslamaTarihi;
	}

	public void setBaslamaTarihi(Date baslamaTarihi) {
		this.baslamaTarihi = baslamaTarihi;
	}

	public Date getBitisTarihi() {
		return bitisTarihi;
	}

	public void setBitisTarihi(Date bitisTarihi) {
		this.bitisTarihi = bitisTarihi;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public List<YakitHareket> getYakitHareketList() {
		return yakitHareketList;
	}

	public void setYakitHareketList(List<YakitHareket> yakitHareketList) {
		this.yakitHareketList = yakitHareketList;
	}

	public List<StokHareket> getStokHareketList() {
		return stokHareketList;
	}

	public void setStokHareketList(List<StokHareket> stokHareketList) {
		this.stokHareketList = stokHareketList;
	}

	public List<Fatura> getFaturaList() {
		return faturaList;
	}

	public void setFaturaList(List<Fatura> faturaList) {
		this.faturaList = faturaList;
	}

	public double getToplamMasraf() {
		return toplamMasraf;
	}

	public void setToplamMasraf(double toplamMasraf) {
		this.toplamMasraf = toplamMasraf;
	}

	public double getToplamKM() {
		return toplamKM;
	}

	public void setToplamKM(double toplamKM) {
		this.toplamKM = toplamKM;
	}

	public double getToplamSaat() {
		return toplamSaat;
	}

	public void setToplamSaat(double toplamSaat) {
		this.toplamSaat = toplamSaat;
	}

	public String getMalzemeKullanimi() {
		return malzemeKullanimi;
	}

	public void setMalzemeKullanimi(String malzemeKullanimi) {
		this.malzemeKullanimi = malzemeKullanimi;
	}

	public List<Kroki> getKrokiList() {
		return krokiList;
	}

	public void setKrokiList(List<Kroki> krokiList) {
		this.krokiList = krokiList;
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
		Proje other = (Proje) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
