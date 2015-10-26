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
public class Vale implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1272502844621312172L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(unique = true)
	private String kullaniciAdi;

	@Column
	private String ad;

	@Column
	private String soyad;

	@Column
	private String sifre;

	@Column
	private boolean doluMu;

	@Column
	private boolean gorevliMi;

	@ManyToOne
	private Isletme isletme;

	@OneToMany(mappedBy = "alanVale", orphanRemoval = true)
	private List<Hareket> alanValeHareketList = new ArrayList<Hareket>();

	@OneToMany(mappedBy = "verenVale", orphanRemoval = true)
	private List<Hareket> verenValeHareketList = new ArrayList<Hareket>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		Vale other = (Vale) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public boolean isDoluMu() {
		return doluMu;
	}

	public void setDoluMu(boolean doluMu) {
		this.doluMu = doluMu;
	}

	public boolean isGorevliMi() {
		return gorevliMi;
	}

	public void setGorevliMi(boolean gorevliMi) {
		this.gorevliMi = gorevliMi;
	}

	public Isletme getIsletme() {
		return isletme;
	}

	public void setIsletme(Isletme isletme) {
		this.isletme = isletme;
	}

	public List<Hareket> getAlanValeHareketList() {
		return alanValeHareketList;
	}

	public void setAlanValeHareketList(List<Hareket> alanValeHareketList) {
		this.alanValeHareketList = alanValeHareketList;
	}

	public List<Hareket> getVerenValeHareketList() {
		return verenValeHareketList;
	}

	public void setVerenValeHareketList(List<Hareket> verenValeHareketList) {
		this.verenValeHareketList = verenValeHareketList;
	}
}
