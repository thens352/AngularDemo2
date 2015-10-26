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
public class Fatura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5848419930373434440L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String seri;

	private int siraNo;

	private String musteriVergiDairesi;

	private int musteriVergiNo;

	private Date duzenlemeTarihi;

	private Date fiiliSevkTarihi;

	private String aliciAdi;

	private String aliciAdresi;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "fatura",cascade = CascadeType.ALL)
	private List<Urun> urunList = new ArrayList<Urun>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "fatura",  cascade = CascadeType.ALL)
	private List<StokHareket> stokHareketList = new ArrayList<StokHareket>();

	private String teslimAlan;

	private String teslimEden;

	private double toplamTutar;

	@ManyToOne
	@Nullable
	private Proje proje;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSiraNo() {
		return siraNo;
	}

	public void setSiraNo(int siraNo) {
		this.siraNo = siraNo;
	}

	public String getSeri() {
		return seri;
	}

	public void setSeri(String seri) {
		this.seri = seri;
	}

	public String getMusteriVergiDairesi() {
		return musteriVergiDairesi;
	}

	public void setMusteriVergiDairesi(String musteriVergiDairesi) {
		this.musteriVergiDairesi = musteriVergiDairesi;
	}

	public int getMusteriVergiNo() {
		return musteriVergiNo;
	}

	public void setMusteriVergiNo(int musteriVergiNo) {
		this.musteriVergiNo = musteriVergiNo;
	}

	public Date getDuzenlemeTarihi() {
		return duzenlemeTarihi;
	}

	public void setDuzenlemeTarihi(Date duzenlemeTarihi) {
		this.duzenlemeTarihi = duzenlemeTarihi;
	}

	public Date getFiiliSevkTarihi() {
		return fiiliSevkTarihi;
	}

	public void setFiiliSevkTarihi(Date fiiliSevkTarihi) {
		this.fiiliSevkTarihi = fiiliSevkTarihi;
	}

	public String getAliciAdi() {
		return aliciAdi;
	}

	public void setAliciAdi(String aliciAdi) {
		this.aliciAdi = aliciAdi;
	}

	public String getAliciAdresi() {
		return aliciAdresi;
	}

	public void setAliciAdresi(String aliciAdresi) {
		this.aliciAdresi = aliciAdresi;
	}

	public List<Urun> getUrunList() {
		return urunList;
	}

	public void setUrunList(List<Urun> urunList) {
		this.urunList = urunList;
	}

	public String getTeslimAlan() {
		return teslimAlan;
	}

	public void setTeslimAlan(String teslimAlan) {
		this.teslimAlan = teslimAlan;
	}

	public String getTeslimEden() {
		return teslimEden;
	}

	public void setTeslimEden(String teslimEden) {
		this.teslimEden = teslimEden;
	}

	public double getToplamTutar() {
		return toplamTutar;
	}

	public void setToplamTutar(double toplamTutar) {
		this.toplamTutar = toplamTutar;
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
		Fatura other = (Fatura) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public List<StokHareket> getStokHareketList() {
		return stokHareketList;
	}

	public void setStokHareketList(List<StokHareket> stokHareketList) {
		this.stokHareketList = stokHareketList;
	}
}
