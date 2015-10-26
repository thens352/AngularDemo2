package com.kayiyazilim.budaksanisakis.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.sun.istack.internal.Nullable;

@Entity
public class StokHareket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1431563689493639436L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private Date degisimTarihi;

	@ManyToOne
	private Malzeme malzeme;

	private double gelenMiktar;

	private double gidenMiktar;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "stokHareket", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<YakitHareket> yakitHareketList = new ArrayList<YakitHareket>();

	@ManyToOne
	@Nullable
	private Proje proje;

	@ManyToOne
	@Nullable
	private Fatura fatura;

	public Proje getProje() {
		return proje;
	}

	public void setProje(Proje proje) {
		this.proje = proje;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDegisimTarihi() {
		return degisimTarihi;
	}

	public void setDegisimTarihi(Date degisimTarihi) {
		this.degisimTarihi = degisimTarihi;
	}

	public Malzeme getMalzeme() {
		return malzeme;
	}

	public void setMalzeme(Malzeme malzeme) {
		this.malzeme = malzeme;
	}

	public double getGelenMiktar() {
		return gelenMiktar;
	}

	public void setGelenMiktar(double gelenMiktar) {
		this.gelenMiktar = gelenMiktar;
	}

	public double getGidenMiktar() {
		return gidenMiktar;
	}

	public void setGidenMiktar(double gidenMiktar) {
		this.gidenMiktar = gidenMiktar;
	}

	public List<YakitHareket> getYakitHareketList() {
		return yakitHareketList;
	}

	public void setYakitHareketList(List<YakitHareket> yakitHareketList) {
		this.yakitHareketList = yakitHareketList;
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
		StokHareket other = (StokHareket) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}
}
