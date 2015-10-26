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

import com.kayi.qrvale.model.type.Marka;
import com.kayi.qrvale.model.type.Renk;

@Entity
public class Arac implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4434988775711275508L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(unique = true)
	private String plaka;

	@Column
	private Marka marka;

	@Column
	private String model;

	@Column
	private Renk renk;

	@Column
	private String koordinat;

	@ManyToOne
	private Isletme isletme;

	@OneToMany(mappedBy = "arac", orphanRemoval = true)
	private List<Hareket> aracHareketList = new ArrayList<Hareket>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlaka() {
		return plaka;
	}

	public void setPlaka(String plaka) {
		this.plaka = plaka;
	}

	public Marka getMarka() {
		return marka;
	}

	public void setMarka(Marka marka) {
		this.marka = marka;
	}

	public Renk getRenk() {
		return renk;
	}

	public void setRenk(Renk renk) {
		this.renk = renk;
	}

	public String getKoordinat() {
		return koordinat;
	}

	public void setKoordinat(String koordinat) {
		this.koordinat = koordinat;
	}

	public Isletme getIsletme() {
		return isletme;
	}

	public void setIsletme(Isletme isletme) {
		this.isletme = isletme;
	}

	public List<Hareket> getAracHareketList() {
		return aracHareketList;
	}

	public void setAracHareketList(List<Hareket> aracHareketList) {
		this.aracHareketList = aracHareketList;
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
		Arac other = (Arac) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
