package com.kayiyazilim.budaksanisakis.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.kayiyazilim.budaksanisakis.model.type.HareketTipi;
import com.sun.istack.internal.Nullable;

@Entity
public class YakitHareket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1431563689493639436L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@ManyToOne
	private Arac arac;

	@ManyToOne
	private Sofor sofor;

	private Date gidisTarihi;

	private String gidisAdresi;

	private Date donusTarihi;

	private short yakitMiktari;

	private short katedilenKM;

	@Enumerated(EnumType.STRING)
	private HareketTipi hareketTipi;
	
	@ManyToOne
	@Nullable
	private StokHareket stokHareket;
	
	@ManyToOne
	@Nullable
	private Proje proje;

	public Proje getProje() {
		return proje;
	}

	public void setProje(Proje proje) {
		this.proje = proje;
	}

	public StokHareket getStokHareket() {
		return stokHareket;
	}

	public void setStokHareket(StokHareket stokHareket) {
		this.stokHareket = stokHareket;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Arac getArac() {
		return arac;
	}

	public void setArac(Arac arac) {
		this.arac = arac;
	}

	public Sofor getSofor() {
		return sofor;
	}

	public void setSofor(Sofor sofor) {
		this.sofor = sofor;
	}

	public Date getGidisTarihi() {
		return gidisTarihi;
	}

	public void setGidisTarihi(Date gidisTarihi) {
		this.gidisTarihi = gidisTarihi;
	}

	public String getGidisAdresi() {
		return gidisAdresi;
	}

	public void setGidisAdresi(String gidisAdresi) {
		this.gidisAdresi = gidisAdresi;
	}

	public Date getDonusTarihi() {
		return donusTarihi;
	}

	public void setDonusTarihi(Date donusTarihi) {
		this.donusTarihi = donusTarihi;
	}

	public short getYakitMiktari() {
		return yakitMiktari;
	}

	public void setYakitMiktari(short yakitMiktari) {
		this.yakitMiktari = yakitMiktari;
	}

	public short getKatedilenKM() {
		return katedilenKM;
	}

	public void setKatedilenKM(short katedilenKM) {
		this.katedilenKM = katedilenKM;
	}

	public HareketTipi getHareketTipi() {
		return hareketTipi;
	}

	public void setHareketTipi(HareketTipi hareketTipi) {
		this.hareketTipi = hareketTipi;
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
		YakitHareket other = (YakitHareket) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
