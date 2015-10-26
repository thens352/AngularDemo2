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
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.ens.degerleme.model.Bosluk;

@Entity
public class Cevap implements Serializable {

	private static final long serialVersionUID = -1588999722814000024L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	private String metin;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "cevap")
	private List<CevapSecenek> cevapSecenekList = new ArrayList<CevapSecenek>();

	@ManyToOne
	private Soru soru;

	@ManyToOne
	private Rapor rapor;

	@Transient
	private List<CevapSecenek> ekranCevapSecenekList = new ArrayList<CevapSecenek>();

	@Transient
	private CevapSecenek ekranCevapSecenek = new CevapSecenek();

	@Transient
	private List<Bosluk> boslukList = new ArrayList<Bosluk>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMetin() {
		return metin;
	}

	public void setMetin(String metin) {
		this.metin = metin;
	}

	public List<CevapSecenek> getCevapSecenekList() {
		return cevapSecenekList;
	}

	public void setCevapSecenekList(List<CevapSecenek> cevapSecenekList) {
		this.cevapSecenekList = cevapSecenekList;
	}

	public Soru getSoru() {
		return soru;
	}

	public void setSoru(Soru soru) {
		this.soru = soru;
	}

	public Rapor getRapor() {
		return rapor;
	}

	public void setRapor(Rapor rapor) {
		this.rapor = rapor;
	}

	public List<CevapSecenek> getEkranCevapSecenekList() {
		return ekranCevapSecenekList;
	}

	public void setEkranCevapSecenekList(
			List<CevapSecenek> ekranCevapSecenekList) {
		this.ekranCevapSecenekList = ekranCevapSecenekList;
	}

	public List<Bosluk> getBoslukList() {
		return boslukList;
	}

	public void setBoslukList(List<Bosluk> boslukList) {
		this.boslukList = boslukList;
	}

	public CevapSecenek getEkranCevapSecenek() {
		return ekranCevapSecenek;
	}

	public void setEkranCevapSecenek(CevapSecenek ekranCevapSecenek) {
		this.ekranCevapSecenek = ekranCevapSecenek;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((boslukList == null) ? 0 : boslukList.hashCode());
		result = prime
				* result
				+ ((cevapSecenekList == null) ? 0 : cevapSecenekList.hashCode());
		result = prime
				* result
				+ ((ekranCevapSecenek == null) ? 0 : ekranCevapSecenek
						.hashCode());
		result = prime
				* result
				+ ((ekranCevapSecenekList == null) ? 0 : ekranCevapSecenekList
						.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((metin == null) ? 0 : metin.hashCode());
		result = prime * result + ((rapor == null) ? 0 : rapor.hashCode());
		result = prime * result + ((soru == null) ? 0 : soru.hashCode());
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
		Cevap other = (Cevap) obj;
		if (boslukList == null) {
			if (other.boslukList != null)
				return false;
		} else if (!boslukList.equals(other.boslukList))
			return false;
		if (cevapSecenekList == null) {
			if (other.cevapSecenekList != null)
				return false;
		} else if (!cevapSecenekList.equals(other.cevapSecenekList))
			return false;
		if (ekranCevapSecenek == null) {
			if (other.ekranCevapSecenek != null)
				return false;
		} else if (!ekranCevapSecenek.equals(other.ekranCevapSecenek))
			return false;
		if (ekranCevapSecenekList == null) {
			if (other.ekranCevapSecenekList != null)
				return false;
		} else if (!ekranCevapSecenekList.equals(other.ekranCevapSecenekList))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (metin == null) {
			if (other.metin != null)
				return false;
		} else if (!metin.equals(other.metin))
			return false;
		if (rapor == null) {
			if (other.rapor != null)
				return false;
		} else if (!rapor.equals(other.rapor))
			return false;
		if (soru == null) {
			if (other.soru != null)
				return false;
		} else if (!soru.equals(other.soru))
			return false;
		return true;
	}

}
