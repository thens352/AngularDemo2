package com.ens.degerleme.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.ens.degerleme.model.Bosluk;

@Entity
public class CevapSecenek implements Serializable {

	private static final long serialVersionUID = -1588999722814000024L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@ManyToOne
	private Cevap cevap;

	@Transient
	private boolean secildi;

	@Transient
	private List<Bosluk> boslukList = new ArrayList<Bosluk>();

	@ManyToOne
	private Secenek secenek;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Secenek getSecenek() {
		return secenek;
	}

	public void setSecenek(Secenek secenek) {
		this.secenek = secenek;
	}

	public Cevap getCevap() {
		return cevap;
	}

	public void setCevap(Cevap cevap) {
		this.cevap = cevap;
	}

	public List<Bosluk> getBoslukList() {
		return boslukList;
	}

	public void setBoslukList(List<Bosluk> boslukList) {
		this.boslukList = boslukList;
	}

	public boolean isSecildi() {
		return secildi;
	}

	public void setSecildi(boolean secildi) {
		this.secildi = secildi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((boslukList == null) ? 0 : boslukList.hashCode());
		result = prime * result + ((cevap == null) ? 0 : cevap.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((secenek == null) ? 0 : secenek.hashCode());
		result = prime * result + (secildi ? 1231 : 1237);
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
		CevapSecenek other = (CevapSecenek) obj;
		if (boslukList == null) {
			if (other.boslukList != null)
				return false;
		} else if (!boslukList.equals(other.boslukList))
			return false;
		if (cevap == null) {
			if (other.cevap != null)
				return false;
		} else if (!cevap.equals(other.cevap))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (secenek == null) {
			if (other.secenek != null)
				return false;
		} else if (!secenek.equals(other.secenek))
			return false;
		if (secildi != other.secildi)
			return false;
		return true;
	}

}
