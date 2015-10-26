package com.ens.degerleme.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.ens.degerleme.model.type.SoruTipi;

@Entity
public class Soru implements Serializable {

	private static final long serialVersionUID = -1588999722814000024L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	private int siraNo;

	private String defaultCevap;

	private int parametreSayisi = 0;

	private String soruMetni;

	private String soruUyari;

	@Enumerated(EnumType.STRING)
	private SoruTipi soruTipi = SoruTipi.TEXT_SORU;
	@ManyToOne
	private Bolum bolum;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "soru", cascade = CascadeType.ALL,  orphanRemoval = true)
	@OrderBy("siraNo")
	private List<Secenek> seceneklList = new ArrayList<Secenek>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSoruMetni() {
		return soruMetni;
	}

	public void setSoruMetni(String soruMetni) {
		this.soruMetni = soruMetni;
	}

	public String getSoruUyari() {
		return soruUyari;
	}

	public void setSoruUyari(String soruUyari) {
		this.soruUyari = soruUyari;
	}

	public List<Secenek> getSeceneklList() {
		return seceneklList;
	}

	public void setSeceneklList(List<Secenek> seceneklList) {
		this.seceneklList = seceneklList;
	}

	public int getSiraNo() {
		return siraNo;
	}

	public void setSiraNo(int siraNo) {
		this.siraNo = siraNo;
	}

	public Bolum getBolum() {
		return bolum;
	}

	public void setBolum(Bolum bolum) {
		this.bolum = bolum;
	}

	public String getDefaultCevap() {
		return defaultCevap;
	}

	public void setDefaultCevap(String defaultCevap) {
		this.defaultCevap = defaultCevap;
	}

	public SoruTipi getSoruTipi() {
		return soruTipi;
	}

	public void setSoruTipi(SoruTipi soruTipi) {
		this.soruTipi = soruTipi;
	}

	public int getParametreSayisi() {
		return parametreSayisi;
	}

	public void setParametreSayisi(int parametreSayisi) {
		this.parametreSayisi = parametreSayisi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bolum == null) ? 0 : bolum.hashCode());
		result = prime * result
				+ ((defaultCevap == null) ? 0 : defaultCevap.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + parametreSayisi;
		result = prime * result
				+ ((seceneklList == null) ? 0 : seceneklList.hashCode());
		result = prime * result + siraNo;
		result = prime * result
				+ ((soruMetni == null) ? 0 : soruMetni.hashCode());
		result = prime * result
				+ ((soruTipi == null) ? 0 : soruTipi.hashCode());
		result = prime * result
				+ ((soruUyari == null) ? 0 : soruUyari.hashCode());
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
		Soru other = (Soru) obj;
		if (bolum == null) {
			if (other.bolum != null)
				return false;
		} else if (!bolum.equals(other.bolum))
			return false;
		if (defaultCevap == null) {
			if (other.defaultCevap != null)
				return false;
		} else if (!defaultCevap.equals(other.defaultCevap))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (parametreSayisi != other.parametreSayisi)
			return false;
		if (seceneklList == null) {
			if (other.seceneklList != null)
				return false;
		} else if (!seceneklList.equals(other.seceneklList))
			return false;
		if (siraNo != other.siraNo)
			return false;
		if (soruMetni == null) {
			if (other.soruMetni != null)
				return false;
		} else if (!soruMetni.equals(other.soruMetni))
			return false;
		if (soruTipi != other.soruTipi)
			return false;
		if (soruUyari == null) {
			if (other.soruUyari != null)
				return false;
		} else if (!soruUyari.equals(other.soruUyari))
			return false;
		return true;
	}

}