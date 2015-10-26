package com.ens.degerleme.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Mahalle {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(unique = true)
	private String adi;

	@ManyToOne
	private Ilce ilce;

	@OneToMany(mappedBy = "mahalle",fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Sokak> sokakListesi = new ArrayList<Sokak>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public Ilce getIlce() {
		return ilce;
	}

	public void setIlce(Ilce ilce) {
		this.ilce = ilce;
	}

	public List<Sokak> getSokakListesi() {
		return sokakListesi;
	}

	public void setSokakListesi(List<Sokak> sokakListesi) {
		this.sokakListesi = sokakListesi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adi == null) ? 0 : adi.hashCode());
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
		Mahalle other = (Mahalle) obj;
		if (adi == null) {
			if (other.adi != null)
				return false;
		} else if (!adi.equals(other.adi))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
