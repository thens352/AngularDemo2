package com.ens.degerleme.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ens.degerleme.model.type.SecenekTipi;

@Entity
public class Secenek implements Serializable {

	private static final long serialVersionUID = -1588999722814000024L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	private String metin;

	private String aciklama;

	private String aciklamaVarmi;

	private int parametreSayisi = 0;

	private int siraNo;

	@Enumerated(EnumType.STRING)
	private SecenekTipi secenekTipi;

	@ManyToOne
	private Soru soru;

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

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public int getSiraNo() {
		return siraNo;
	}

	public void setSiraNo(int siraNo) {
		this.siraNo = siraNo;
	}

	public Soru getSoru() {
		return soru;
	}

	public void setSoru(Soru soru) {
		this.soru = soru;
	}

	public void setParametreSayisi(int parametreSayisi) {
		this.parametreSayisi = parametreSayisi;
	}

	public String getAciklamaVarmi() {
		return aciklamaVarmi;
	}

	public void setAciklamaVarmi(String aciklamaVarmi) {
		this.aciklamaVarmi = aciklamaVarmi;
	}

	public SecenekTipi getSecenekTipi() {
		return secenekTipi;
	}

	public void setSecenekTipi(SecenekTipi secenekTipi) {
		this.secenekTipi = secenekTipi;
	}

	public int getParametreSayisi() {
		return parametreSayisi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aciklama == null) ? 0 : aciklama.hashCode());
		result = prime * result
				+ ((aciklamaVarmi == null) ? 0 : aciklamaVarmi.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((metin == null) ? 0 : metin.hashCode());
		result = prime * result + parametreSayisi;
		result = prime * result
				+ ((secenekTipi == null) ? 0 : secenekTipi.hashCode());
		result = prime * result + siraNo;
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
		Secenek other = (Secenek) obj;
		if (aciklama == null) {
			if (other.aciklama != null)
				return false;
		} else if (!aciklama.equals(other.aciklama))
			return false;
		if (aciklamaVarmi == null) {
			if (other.aciklamaVarmi != null)
				return false;
		} else if (!aciklamaVarmi.equals(other.aciklamaVarmi))
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
		if (parametreSayisi != other.parametreSayisi)
			return false;
		if (secenekTipi != other.secenekTipi)
			return false;
		if (siraNo != other.siraNo)
			return false;
		if (soru == null) {
			if (other.soru != null)
				return false;
		} else if (!soru.equals(other.soru))
			return false;
		return true;
	}
}
